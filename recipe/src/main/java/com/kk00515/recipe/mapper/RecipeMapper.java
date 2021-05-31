package com.kk00515.recipe.mapper;

import com.kk00515.recipe.dto.IngredientDto;
import com.kk00515.recipe.dto.RecipeDto;
import com.kk00515.recipe.model.Ingredient;
import com.kk00515.recipe.model.Recipe;

import java.util.HashSet;
import java.util.Set;

public class RecipeMapper {

    static public RecipeDto convertToRecipeDto(Recipe recipe){
        RecipeDto recipeDto = new RecipeDto();

        Set<IngredientDto> ingredients = new HashSet<IngredientDto>();
        for (Ingredient ingredient : recipe.getIngredients()){
            ingredients.add(RecipeMapper.convertToIngredientDto(ingredient));
        }
        recipeDto.setIngredients(ingredients);
        recipeDto.setCalories(recipe.getCalories());
        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setId(recipe.getId());

        return recipeDto;
    }

    static public IngredientDto convertToIngredientDto(Ingredient ingredient){
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setIngredientName(ingredient.getIngredientName());

        return ingredientDto;
    }

}
