package com.kk00515.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recipe not found")
public class RecipeNotFoundException extends RuntimeException{
}
