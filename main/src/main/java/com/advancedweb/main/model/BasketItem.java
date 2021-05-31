package com.advancedweb.main.model;

import javax.persistence.*;

@Entity
@Table(name="BASKET_ITEM")
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="basket_id", nullable = false)
    private Basket basket;
    private int kitchenId;
    private int recipeId;
    private int quantity;

    public BasketItem(){};

    public BasketItem(Long id, Basket basket, int kitchenId, int recipeId, int quantity) {
        this.id = id;
        this.basket = basket;
        this.kitchenId = kitchenId;
        this.recipeId = recipeId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(int kitchenId) {
        this.kitchenId = kitchenId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
