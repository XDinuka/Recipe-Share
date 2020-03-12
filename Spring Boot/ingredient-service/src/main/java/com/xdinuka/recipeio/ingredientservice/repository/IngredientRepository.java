package com.xdinuka.recipeio.ingredientservice.repository;

import com.xdinuka.recipeio.ingredientservice.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
