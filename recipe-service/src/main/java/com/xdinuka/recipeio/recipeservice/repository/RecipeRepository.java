package com.xdinuka.recipeio.recipeservice.repository;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
}
