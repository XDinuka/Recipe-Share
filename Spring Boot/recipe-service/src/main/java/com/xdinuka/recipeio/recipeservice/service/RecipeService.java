package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.model.Ingredient;
import com.xdinuka.recipeio.recipeservice.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> fetchAll();
    Recipe saveOrUpdate(Recipe recipe);
    Optional<Recipe> fetchById(Integer id);

    List<Recipe> fetchAllWithIngredient(Integer ingredientID);
    List<Recipe> fetchAllWithIngredients(List<Integer> ingredientID);

    List<Ingredient> fetchIngredientDetails(Recipe recipe);
}
