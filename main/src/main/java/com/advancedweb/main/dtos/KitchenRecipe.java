package com.advancedweb.main.dtos;

public class KitchenRecipe {

    private KitchenDto kitchenDto;
    private RecipeDto recipeDto;
    private int quantity;

    public KitchenRecipe(){};

    public KitchenRecipe(KitchenDto kitchenDto, RecipeDto recipeDto, int quantity) {
        this.kitchenDto = kitchenDto;
        this.recipeDto = recipeDto;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public KitchenDto getKitchenDto() {
        return kitchenDto;
    }

    public void setKitchenDto(KitchenDto kitchenDto) {
        this.kitchenDto = kitchenDto;
    }

    public RecipeDto getRecipeDto() {
        return recipeDto;
    }

    public void setRecipeDto(RecipeDto recipeDto) {
        this.recipeDto = recipeDto;
    }
}
