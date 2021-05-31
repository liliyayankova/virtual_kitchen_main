package com.advancedweb.main.repos;

import com.advancedweb.main.model.Basket;
import org.springframework.data.repository.CrudRepository;


public interface BasketRepo extends CrudRepository<Basket,Long> {

    Basket findByUsername(String username);

}
