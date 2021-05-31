package com.kk00515.recipe.controller;

import com.kk00515.recipe.dto.IngredientDto;
import com.kk00515.recipe.dto.RecipeDto;
import com.kk00515.recipe.exceptions.IngredientNotFoundException;
import com.kk00515.recipe.exceptions.RecipeNotFoundException;
import com.kk00515.recipe.mapper.RecipeMapper;
import com.kk00515.recipe.model.Ingredient;
import com.kk00515.recipe.model.Recipe;
import com.kk00515.recipe.repo.IngredientRepo;
import com.kk00515.recipe.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RecipeController {

    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private IngredientRepo ingredientRepo;

    @GetMapping("/recipes/{id}")
    @ResponseBody
    public RecipeDto getRecipe(@PathVariable("id") Long id) {
        Optional<Recipe> recipeResponse = recipeRepo.findById(id);
        if (recipeResponse.isPresent()) {
            return RecipeMapper.convertToRecipeDto(recipeResponse.get());
        } else {
            throw new RecipeNotFoundException();
        }
    }

    @GetMapping("/recipeName/{title}")
    @ResponseBody
    public List<RecipeDto> getRecipesByTitle(@PathVariable("title") String title) {
        List<RecipeDto> recipesDtos = new ArrayList<RecipeDto>();
        List<Recipe> recipes = recipeRepo.findByTitle(title);
        if (!recipes.isEmpty()){
            for (Recipe recipe : recipes){
                recipesDtos.add(RecipeMapper.convertToRecipeDto(recipe));
            }
            return recipesDtos;
        }else{
            throw new RecipeNotFoundException();
        }

    }

    @PostMapping(path = "/newRecipe", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public RecipeDto addNewRecipe(@RequestBody RecipeDto newRecipeDto){
        Set<Ingredient> ingredientSet = new HashSet<>();
        Recipe newRecipe = new Recipe();

        for (IngredientDto ingredientDto : newRecipeDto.getIngredients()){
            Optional<Ingredient> response = ingredientRepo.findById(ingredientDto.getId());
            if (!response.isPresent()){
                throw new IngredientNotFoundException();
            }else{
                ingredientSet.add(response.get());
            }
        }

        newRecipe.setCalories(newRecipeDto.getCalories());
        newRecipe.setIngredients(ingredientSet);
        newRecipe.setTitle(newRecipeDto.getTitle());

        recipeRepo.save(newRecipe);

        System.out.println(newRecipe.getId());

        return RecipeMapper.convertToRecipeDto(newRecipe);
    }

    @GetMapping("/recipes")
    @ResponseBody
    public List<RecipeDto> getAllRecipes() {
        List<RecipeDto> lstRecipes = new ArrayList<RecipeDto>();
        Iterator<Recipe> recipes = recipeRepo.findAll().iterator();
        while (recipes.hasNext()) {
            lstRecipes.add(RecipeMapper.convertToRecipeDto(recipes.next()));
        }
        return lstRecipes;
    }

    @GetMapping("/ingredients")
    @ResponseBody
    public List<IngredientDto> getAllIngredients() {
        List<IngredientDto> lstIngredients = new ArrayList<IngredientDto>();
        Iterator<Ingredient> ingredients = ingredientRepo.findAll().iterator();
        while (ingredients.hasNext()) {
            lstIngredients.add(RecipeMapper.convertToIngredientDto(ingredients.next()));
        }
        return lstIngredients;
    }

    @GetMapping("/ingredients/{id}")
    @ResponseBody
    public IngredientDto getIngredient(@PathVariable("id") Long id) {
        Optional<Ingredient> ingredientResponse = ingredientRepo.findById(id);
        if (ingredientResponse.isPresent()) {
            return RecipeMapper.convertToIngredientDto(ingredientResponse.get());
        } else {
            throw new RecipeNotFoundException();
        }
    }





}
