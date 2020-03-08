package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> fetchAll();
    Recipe saveOrUpdate(Recipe recipe);
    Optional<Recipe> fetchById(Integer id);
}
