package com.advancedweb.main.repos;

import com.advancedweb.main.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<Order,Long> {

    List<Order> findByUsername(String username);

}
