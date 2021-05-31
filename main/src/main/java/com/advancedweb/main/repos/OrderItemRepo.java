package com.advancedweb.main.repos;

import com.advancedweb.main.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepo extends CrudRepository<OrderItem,Long> {
}
