package com.xdinuka.recipeshare.ingredientservice.service;

import com.xdinuka.recipeshare.ingredientservice.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> fetchAll();
    Ingredient saveOrUpdate(Ingredient ingredient);
    Optional<Ingredient> fetchById(Integer id);
}
