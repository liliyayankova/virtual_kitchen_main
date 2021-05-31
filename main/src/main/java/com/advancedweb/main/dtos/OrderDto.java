package com.advancedweb.main.dtos;

import com.advancedweb.main.model.OrderItem;

import java.util.List;


public class OrderDto {

    private Long order_id;
    private String username;
    private List<OrderItemDto> orderItems;

    public OrderDto(Long order_id, String username, List<OrderItemDto> orderItems) {
        this.order_id = order_id;
        this.username = username;
        this.orderItems = orderItems;
    }

    public OrderDto() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
