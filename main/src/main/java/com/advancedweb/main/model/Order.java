package com.advancedweb.main.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    private String username;
    @OneToMany(mappedBy="order")
    private Set<OrderItem> orderItems;

    public Order() {
    }

    public Order(Long id, String username, Set<OrderItem> orderItems) {
        this.order_id = id;
        this.username = username;
        this.orderItems = orderItems;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
