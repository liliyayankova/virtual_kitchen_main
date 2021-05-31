package com.advancedweb.main.dtos;

public class OrderFormDto {

    private Long kitchenId;
    private Long recipeId;
    private int quantity;

    public OrderFormDto(Long kitchenId, Long recipeId, int quantity) {
        this.kitchenId = kitchenId;
        this.recipeId = recipeId;
        this.quantity = quantity;
    }

    public OrderFormDto() {
    }

    public Long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(Long kitchenId) {
        this.kitchenId = kitchenId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
