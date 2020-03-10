package com.xdinuka.recipeio.recipeservice.repository;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT new com.xdinuka.recipeio.recipeservice.model.Recipe(r.id,r.name,true,i) "
            + "FROM Recipe r INNER JOIN r.ingredients i "
            + "WHERE i.ingredientId IN (?1)")
    List<Recipe> fetchRecipeIngredientInnerJoin(Integer... ingredientIDs);

}
