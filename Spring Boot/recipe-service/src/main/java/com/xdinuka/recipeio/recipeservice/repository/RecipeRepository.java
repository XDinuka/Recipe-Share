package com.xdinuka.recipeio.recipeservice.repository;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query("SELECT DISTINCT new Recipe(r.id,r.name) FROM Recipe r JOIN r.ingredients i WHERE i.ingredientId = ?1")
    List<Recipe> fetchRecipeIngredientInnerJoin(Integer ingredientID);

    @Query("SELECT DISTINCT new Recipe(r.id,r.name) FROM Recipe r JOIN r.ingredients i "
            + "WHERE i.ingredientId IN :ingredientIDs GROUP BY r.id HAVING count(i.ingredientId) = :ing_size ")
    List<Recipe> fetchRecipeIngredientsInnerJoin(@Param("ingredientIDs") List<Integer> ingredientID,@Param("ing_size") Long ing_size);

//    List<Recipe> findRecipeByIngredients(Ingredient ingredient);

}
