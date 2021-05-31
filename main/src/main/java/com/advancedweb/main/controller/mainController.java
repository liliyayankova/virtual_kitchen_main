package com.advancedweb.main.controller;

import com.advancedweb.main.dtos.*;
import com.advancedweb.main.mappers.BasketMapper;
import com.advancedweb.main.model.Basket;
import com.advancedweb.main.model.BasketItem;
import com.advancedweb.main.model.Order;
import com.advancedweb.main.model.OrderItem;
import com.advancedweb.main.repos.BasketItemRepo;
import com.advancedweb.main.repos.BasketRepo;
import com.advancedweb.main.repos.OrderItemRepo;
import com.advancedweb.main.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@SessionAttributes({"username"})
public class mainController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private BasketMapper basketMapper;

    @Autowired
    private BasketRepo basketRepo;

    @Autowired
    private BasketItemRepo basketItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${kitchenService.url}")
    private String kitchenServiceUrl;

    @Value("${recipeService.url}")
    private String recipeServiceUrl;

    @GetMapping(path="/about")
    public String getAboutPage(){
        return "about";
    }

    @GetMapping(path="/{username}")
    public String getHomePage(@PathVariable("username") String name, Model model, HttpSession session){
        session.setAttribute("username",name);

        try{
            List<KitchenDto> kitchenDtos = Arrays.asList(restTemplate.getForObject(kitchenServiceUrl+"/kitchens", KitchenDto[].class));
            model.addAttribute("kitchenDtos",kitchenDtos);
        }catch (Exception ex){
            //Show error page
        }

        return "home";
    }

    @GetMapping(path="/viewKitchen/{id}")
    public String getKitchenRecipes(@PathVariable("id") Long kitchenId, Model model){
        KitchenDto clickedKitchenDto = restTemplate.getForObject(kitchenServiceUrl+"/kitchen/id/"+kitchenId.toString(),
                KitchenDto.class);
         model.addAttribute("currentKitchen",clickedKitchenDto);

        try{
            List<RecipeDto> recipeDtoList = new ArrayList<>();
            List<KitchenRecipeDto> kitchenRecipeDtos = Arrays.asList(restTemplate.getForObject(kitchenServiceUrl+"/kitchenRecipes/kitchen/"+kitchenId.toString(), KitchenRecipeDto[].class));
            List<KitchenRecipe> kitchenRecipes = new ArrayList<>();
            for (KitchenRecipeDto kitchenRecipeDto : kitchenRecipeDtos){
                if (kitchenRecipeDto.getQuantity() > 0){
                    RecipeDto recipeDto =restTemplate.getForObject(recipeServiceUrl+"/recipes/"+kitchenRecipeDto.getRecipeId().toString(), RecipeDto.class);
                    kitchenRecipes.add(new KitchenRecipe(clickedKitchenDto,recipeDto,kitchenRecipeDto.getQuantity()));
                    recipeDtoList.add(recipeDto);
                }
            }
            model.addAttribute("kitchenRecipes",kitchenRecipes);
            model.addAttribute("recipeDtos",recipeDtoList);

        }catch (Exception ex){
            //Show error page
        }

        return "recipes";
    }

    @PostMapping(path="/addToBasket")
    public String addToBasket(@ModelAttribute("orderFormDto") OrderFormDto orderFormDto, Model model){
        Set<BasketItem> basketItems;
        String username = (String) model.getAttribute("username");

        Basket userBasket = basketRepo.findByUsername(username);
        if (userBasket == null){
            userBasket = new Basket();
            userBasket.setUsername(username);
            basketRepo.save(userBasket);
        }

        //Check if quantity is available
        boolean valid = checkQuantityValid(orderFormDto.getKitchenId().intValue(),orderFormDto.getRecipeId().intValue(),orderFormDto.getQuantity());

        if (valid){
            BasketItem basketItem = new BasketItem();
            basketItem.setBasket(userBasket);
            basketItem.setKitchenId(orderFormDto.getKitchenId().intValue());
            basketItem.setRecipeId(orderFormDto.getRecipeId().intValue());
            basketItem.setQuantity(orderFormDto.getQuantity());

            basketItemRepo.save(basketItem);
        }else{
            //throw error message
        }



        return "redirect:/viewKitchen/"+orderFormDto.getKitchenId().toString();
    }

    @GetMapping(path="/basket")
    public String getBasket(Model model){
        String username = (String) model.getAttribute("username");
        Basket basket = basketRepo.findByUsername(username);
        if (basket == null){
            basket = new Basket();
            basket.setUsername(username);
            basketRepo.save(basket);
        }


        model.addAttribute("basketItems", basketMapper.convertBasketItemsToBasketItemDtos(new ArrayList<>(basket.getBasketItems())));
        return "basket";
    }

    @PostMapping(path="/basketItem",params = "delete")
    public String deleteBasketItem(Model model, @RequestParam("basketItemId") long basketItemId){
        basketItemRepo.deleteById(basketItemId);
        return "redirect:/basket";
    }

    @PostMapping(path="/order")
    public String orderBasketItems(Model model){
        //Check quantity
        String username = (String)model.getAttribute("username");
        Order order = new Order();
        order.setUsername(username);
        Basket basket = basketRepo.findByUsername(username);
        orderRepo.save(order);
        for (BasketItem basketItem: basket.getBasketItems()){
            if (checkQuantityValid(basketItem.getKitchenId(),basketItem.getRecipeId(),basketItem.getQuantity())){

                OrderFormDto orderFormDto = new OrderFormDto(Integer.toUnsignedLong(basketItem.getKitchenId()),Integer.toUnsignedLong(basketItem.getRecipeId()),basketItem.getQuantity());
                HttpEntity<OrderFormDto> request = new HttpEntity<>(orderFormDto);
                OrderResultDto orderResultDto = restTemplate.postForObject(kitchenServiceUrl+"/order", request,OrderResultDto.class);
                if (orderResultDto.isSuccess()){
                    OrderItem orderItem = basketMapper.convertBasketItemToOrderItem(basketItem,order);
                    orderItemRepo.save(orderItem);
                    Set<OrderItem> orderItems = order.getOrderItems();
                    if (orderItems == null){
                        orderItems = new HashSet<>();
                    }
                    orderItems.add(orderItem);
                    order.setOrderItems(orderItems);
                    orderRepo.save(order);
                }
                basketItemRepo.delete(basketItem);
            } else{
                //Throw error message
            }
        }
        if (order.getOrderItems()==null || order.getOrderItems().isEmpty()){
            orderRepo.delete(order);
        }
        return "redirect:/showOrders";
    }

    @GetMapping(path="/showOrders")
    public String getAllOrders(Model model){
        String username = (String)model.getAttribute("username");
        List<Order> allOrders = orderRepo.findByUsername(username);
        model.addAttribute("orders",basketMapper.convertToOrderDtos(allOrders));

        return "allOrders";

    }

    @GetMapping(path="/order/{kitchenId}/{recipeId}")
    public String getOrderForm(@PathVariable("kitchenId") Long kitchenId,@PathVariable("recipeId") Long recipeId, Model model){
        RecipeDto recipeDto =restTemplate.getForObject(recipeServiceUrl+"/recipes/"+recipeId.toString(), RecipeDto.class);
        KitchenDto kitchenDto = restTemplate.getForObject(kitchenServiceUrl+"/kitchen/id/"+kitchenId.toString(),
                KitchenDto.class);

        model.addAttribute("recipeDto", recipeDto);
        model.addAttribute("kitchenDto",kitchenDto);
        model.addAttribute("orderFormDto",new OrderFormDto());

        return "orderForm";
    }

    @GetMapping(path="/logout")
    public RedirectView logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8000/");
        return redirectView;
    }

    public boolean checkQuantityValid(int kitchenId,int recipeId, int quantity){
        KitchenRecipeDto kitchenRecipeDto = restTemplate.
                getForObject(kitchenServiceUrl+"/kitchenRecipes/kitchen/"+String.valueOf(kitchenId)+"/recipe/"+String.valueOf(recipeId), KitchenRecipeDto.class);
        if (kitchenRecipeDto != null){
            return (kitchenRecipeDto.getQuantity() >= quantity);
        }else{
           return false;
        }
    }


}
