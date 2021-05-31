package com.kk00515.kitchen.repo;


import com.kk00515.kitchen.model.KitchenRecipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KitchenRecipeRepo extends CrudRepository<KitchenRecipe,Long>{

    List<KitchenRecipe> findByKitchenId(Long kitchenId);
    List<KitchenRecipe> findByRecipeId(Long recipeId);
    KitchenRecipe findByKitchenIdAndRecipeId(Long kitchenId, Long recipeId);

}
