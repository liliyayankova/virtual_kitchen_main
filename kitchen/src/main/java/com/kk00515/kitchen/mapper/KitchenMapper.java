package com.kk00515.kitchen.mapper;

import com.kk00515.kitchen.dto.KitchenDto;
import com.kk00515.kitchen.dto.KitchenRecipeDto;
import com.kk00515.kitchen.model.Kitchen;
import com.kk00515.kitchen.model.KitchenRecipe;

public class KitchenMapper {

    public static KitchenDto convertToKitchenDto(Kitchen kitchen){
        KitchenDto kitchenDto = new KitchenDto();

        kitchenDto.setEmail(kitchen.getEmail());
        kitchenDto.setFirstAddress(kitchen.getFirstAddress());
        kitchenDto.setId(kitchen.getId());
        kitchenDto.setTown(kitchen.getTown());
        kitchenDto.setKitchenName(kitchen.getKitchenName());

        return kitchenDto;
    }

    public static KitchenRecipeDto convertToKitchenRecipeDto(KitchenRecipe kitchenRecipe){
        KitchenRecipeDto kitchenRecipeDto = new KitchenRecipeDto();

        kitchenRecipeDto.setKitchenId(kitchenRecipe.getKitchenId());
        kitchenRecipeDto.setRecipeId(kitchenRecipe.getRecipeId());
        kitchenRecipeDto.setQuantity(kitchenRecipe.getQuantity());

        return kitchenRecipeDto;
    }

}
