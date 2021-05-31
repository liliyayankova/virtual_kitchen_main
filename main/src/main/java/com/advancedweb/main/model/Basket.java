package com.advancedweb.main.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basket_id;
    private String username;
    @OneToMany(mappedBy="basket")
    private Set<BasketItem> basketItems;

    public Basket() {
    }

    public Basket(Long id, String username, Set<BasketItem> basketItems) {
        this.basket_id = id;
        this.username = username;
        this.basketItems = basketItems;
    }

    public Long getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(Long basket_id) {
        this.basket_id = basket_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(Set<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
