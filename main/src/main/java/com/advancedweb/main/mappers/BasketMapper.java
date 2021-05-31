package com.advancedweb.main.mappers;


import com.advancedweb.main.dtos.*;
import com.advancedweb.main.model.BasketItem;
import com.advancedweb.main.model.Order;
import com.advancedweb.main.model.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class BasketMapper {

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${kitchenService.url}")
    private String kitchenServiceUrl;

    @Value("${recipeService.url}")
    private  String recipeServiceUrl;


    public OrderDto convertToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_id(order.getOrder_id());
        orderDto.setUsername(order.getUsername());

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem orderItem:order.getOrderItems()){
            orderItemDtos.add(convertToOrderItemDto(orderItem));
        }

        orderDto.setOrderItems(orderItemDtos);

        return orderDto;
    }

    public List<OrderDto> convertToOrderDtos(List<Order> orders){
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order: orders){
            orderDtos.add(convertToOrderDto(order));
        }
        return orderDtos;
    }

    public OrderItemDto convertToOrderItemDto(OrderItem orderItem) {

        OrderItemDto orderItemDto = new OrderItemDto();

        RecipeDto recipeDto =restTemplate.getForObject(recipeServiceUrl+"/recipes/"+String.valueOf(orderItem.getRecipeId()), RecipeDto.class);
        KitchenDto kitchenDto = restTemplate.getForObject(kitchenServiceUrl+"/kitchen/id/"+String.valueOf(orderItem.getKitchenId()),
                KitchenDto.class);

        orderItemDto.setRecipeTitle(recipeDto.getTitle());
        orderItemDto.setFirstAddress(kitchenDto.getFirstAddress());
        orderItemDto.setKitchenName(kitchenDto.getKitchenName());
        orderItemDto.setTown(kitchenDto.getTown());
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setOrderId(orderItem.getOrder().getOrder_id());


        return orderItemDto;
    }


    public BasketItemDto convertToBasketItemDto(BasketItem basketItem) {

        BasketItemDto basketItemDto = new BasketItemDto();

        RecipeDto recipeDto =restTemplate.getForObject(recipeServiceUrl+"/recipes/"+String.valueOf(basketItem.getRecipeId()), RecipeDto.class);
        KitchenDto kitchenDto = restTemplate.getForObject(kitchenServiceUrl+"/kitchen/id/"+String.valueOf(basketItem.getKitchenId()),
                KitchenDto.class);

        basketItemDto.setRecipeTitle(recipeDto.getTitle());
        basketItemDto.setFirstAddress(kitchenDto.getFirstAddress());
        basketItemDto.setKitchenName(kitchenDto.getKitchenName());
        basketItemDto.setTown(kitchenDto.getTown());
        basketItemDto.setId(basketItem.getId());
        basketItemDto.setQuantity(basketItem.getQuantity());
        basketItemDto.setBasketId(basketItem.getBasket().getBasket_id());


        return basketItemDto;
    }

    public List<BasketItemDto> convertBasketItemsToBasketItemDtos(List<BasketItem> basketItemList){
        List<BasketItemDto> basketItemDtos = new ArrayList<>();
        for (BasketItem basketItem :basketItemList){
            basketItemDtos.add(convertToBasketItemDto(basketItem));
        }
        return basketItemDtos;
    }

    public OrderItem convertBasketItemToOrderItem(BasketItem basketItem,Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setQuantity(basketItem.getQuantity());
        orderItem.setKitchenId(basketItem.getKitchenId());
        orderItem.setRecipeId(basketItem.getRecipeId());
        return orderItem;
    }




}
