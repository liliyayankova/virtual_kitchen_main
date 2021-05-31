package com.kk00515.recipe.repo;

import com.kk00515.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RecipeRepo extends CrudRepository<Recipe,Long> {
    List<Recipe> findByTitle(String title);
}
