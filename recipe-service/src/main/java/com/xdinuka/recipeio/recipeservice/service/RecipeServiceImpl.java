package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.model.Ingredient;
import com.xdinuka.recipeio.recipeservice.repository.RecipeRepository;
import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> fetchAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe saveOrUpdate(Recipe ingredient) {
        return recipeRepository.save(ingredient);
    }

    @Override
    public Optional<Recipe> fetchById(Integer id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> fetchAllWithIngredient(Integer ingredientID) {
        return recipeRepository.fetchRecipeIngredientInnerJoin(ingredientID);
    }

    @Override
    public List<Recipe> fetchAllWithIngredients(Integer... ingredientID) {
//        System.out.println("hello2 "+ingredientID);
//        Ingredient ingredient = new Ingredient();
//        ingredient.setIngredientId(ingredientID);


        List<Recipe> recipes = recipeRepository.fetchRecipeIngredientsInnerJoin(Arrays.asList(ingredientID),Long.valueOf(ingredientID.length));
//        System.out.println(recipes.size());
        return recipes;
    }

}
