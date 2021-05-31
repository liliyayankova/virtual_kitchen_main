package com.kk00515.kitchen.controller;

import com.kk00515.kitchen.dto.KitchenDto;
import com.kk00515.kitchen.dto.KitchenRecipeDto;
import com.kk00515.kitchen.dto.OrderFormDto;
import com.kk00515.kitchen.dto.OrderResultDto;
import com.kk00515.kitchen.exception.KitchenNotFoundException;
import com.kk00515.kitchen.mapper.KitchenMapper;
import com.kk00515.kitchen.model.Kitchen;
import com.kk00515.kitchen.model.KitchenRecipe;
import com.kk00515.kitchen.repo.KitchenRecipeRepo;
import com.kk00515.kitchen.repo.KitchenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class KitchenController {

    @Autowired
    KitchenRecipeRepo kitchenRecipeRepo;

    @Autowired
    KitchenRepo kitchenRepo;

    @GetMapping("/kitchen/id/{id}")
    @ResponseBody
    public KitchenDto getKitchenById(@PathVariable("id") Long id) {
        Optional<Kitchen> kitchenResponse = kitchenRepo.findById(id);
        if (kitchenResponse.isPresent()) {
            return KitchenMapper.convertToKitchenDto(kitchenResponse.get());
        } else {
            throw new KitchenNotFoundException();
        }
    }

    @GetMapping("/kitchens")
    @ResponseBody
    public List<KitchenDto> getAllKitchens(){
        List<KitchenDto> listKitchenDtos = new ArrayList<>();
        Iterator<Kitchen> kitchenIterator = kitchenRepo.findAll().iterator();
        while (kitchenIterator.hasNext()){
            listKitchenDtos.add(KitchenMapper.convertToKitchenDto(kitchenIterator.next()));
        }
        return listKitchenDtos;
    }

    @GetMapping("/kitchen/name/{name}")
    @ResponseBody
    public List<KitchenDto> getKitchensByName(@PathVariable("name") String name){
        List<KitchenDto> kitchenDtos = new ArrayList<KitchenDto>();
        List<Kitchen> kitchenList = kitchenRepo.findByKitchenName(name);
        if (!kitchenList.isEmpty()){
            for (Kitchen kitchen : kitchenList){
                kitchenDtos.add(KitchenMapper.convertToKitchenDto(kitchen));
            }
            return kitchenDtos;
        }else{
            throw new KitchenNotFoundException();
        }
    }

    @GetMapping("/kitchenRecipes/kitchen/{id}")
    @ResponseBody
    public List<KitchenRecipeDto> getKitchenRecipesByKitchen(@PathVariable("id") Long id){
        List<KitchenRecipeDto> kitchenRecipeDtos = new ArrayList<KitchenRecipeDto>();
        List<KitchenRecipe> kitchenRecipes = kitchenRecipeRepo.findByKitchenId(id);

        if (!kitchenRecipes.isEmpty()) {
            for (KitchenRecipe kitchenRecipe : kitchenRecipes){
                kitchenRecipeDtos.add(KitchenMapper.convertToKitchenRecipeDto(kitchenRecipe));
            }
            return kitchenRecipeDtos;
        } else {
            throw new KitchenNotFoundException();
        }
    }

    @GetMapping("/kitchenRecipes/kitchen/{kitchenId}/recipe/{recipeId}")
    @ResponseBody
    public KitchenRecipeDto getKitchenRecipeByKitchenIdAndRecipeId(@PathVariable("kitchenId") Long kitchenId,@PathVariable("recipeId") Long recipeId){
        KitchenRecipe kitchenRecipe = kitchenRecipeRepo.findByKitchenIdAndRecipeId(kitchenId,recipeId);
        return KitchenMapper.convertToKitchenRecipeDto(kitchenRecipe);
    }


    @GetMapping("/kitchenRecipes/recipe/{id}")
    @ResponseBody
    public List<KitchenRecipeDto> getKitchensRecipesByRecipe(@PathVariable("id") Long id){
        List<KitchenRecipeDto> kitchenRecipeDtos = new ArrayList<KitchenRecipeDto>();
        List<KitchenRecipe> kitchenRecipes = kitchenRecipeRepo.findByRecipeId(id);

        if (!kitchenRecipes.isEmpty()) {
            for (KitchenRecipe kitchenRecipe : kitchenRecipes){
                kitchenRecipeDtos.add(KitchenMapper.convertToKitchenRecipeDto(kitchenRecipe));
            }
            return kitchenRecipeDtos;
        } else {
            throw new KitchenNotFoundException();
        }
    }

    @PostMapping("/order")
    @ResponseBody
    public OrderResultDto placeOrder(@RequestBody OrderFormDto orderForm){
        KitchenRecipe kitchenRecipe = kitchenRecipeRepo.findByKitchenIdAndRecipeId(orderForm.getKitchenId(), orderForm.getRecipeId());
        int availableQuantity = kitchenRecipe.getQuantity();

        if (availableQuantity >= orderForm.getQuantity()){
            kitchenRecipe.setQuantity(availableQuantity- orderForm.getQuantity());
            kitchenRecipeRepo.save(kitchenRecipe);
            return new OrderResultDto(true,"");
        }else{
            return new OrderResultDto(false,"invalid quantity");
        }



    }


}
