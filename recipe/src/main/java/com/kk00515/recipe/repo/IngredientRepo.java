package com.kk00515.recipe.repo;

import com.kk00515.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepo extends CrudRepository<Ingredient,Long> {
}
