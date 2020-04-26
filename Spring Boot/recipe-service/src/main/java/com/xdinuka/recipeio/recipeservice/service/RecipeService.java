package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Recipe saveOrUpdate(Recipe recipe);

    Optional<Recipe> fetchById(Integer id, HttpHeaders httpHeaders);

    List<Recipe> fetchAllLikeName(String name, HttpHeaders httpHeaders);

    List<Recipe> fetchAllLikeNameVegan(String name, HttpHeaders httpHeaders);

//    List<Recipe> fetchAll();
//
//    List<Recipe> fetchAllWithIngredient(Integer ingredientID);
//
//    List<Recipe> fetchAllWithIngredients(List<Integer> ingredientID);
//
//    List<IngredientOld> fetchIngredientDetails(Recipe recipe);
}
