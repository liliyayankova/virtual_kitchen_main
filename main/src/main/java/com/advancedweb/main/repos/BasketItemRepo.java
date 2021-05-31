package com.advancedweb.main.repos;

import com.advancedweb.main.model.BasketItem;
import org.springframework.data.repository.CrudRepository;

public interface BasketItemRepo extends CrudRepository<BasketItem,Long> {
}
