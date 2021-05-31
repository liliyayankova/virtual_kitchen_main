package com.kk00515.kitchen.repo;

import com.kk00515.kitchen.model.Kitchen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KitchenRepo extends CrudRepository<Kitchen,Long> {

    List<Kitchen> findByKitchenName(String kitchenName);

}
