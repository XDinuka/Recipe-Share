package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.hystrix.IngredientCommand;
import com.xdinuka.recipeio.recipeservice.model.Ingredient;
import com.xdinuka.recipeio.recipeservice.repository.RecipeRepository;
import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{

    final RecipeRepository recipeRepository;
    final RestTemplate restTemplate;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RestTemplate restTemplate) {
        this.recipeRepository = recipeRepository;
        this.restTemplate = restTemplate;

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
    public List<Recipe> fetchAllWithIngredients(List<Integer> ingredientIDs) {
        List<Recipe> recipes = recipeRepository.fetchRecipeIngredientsInnerJoin(ingredientIDs,Long.valueOf(ingredientIDs.size()));

        return recipes;
    }

    @Override
    public List<Ingredient> fetchIngredientDetails(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        if(ingredients!=null){
            List<Ingredient> collect = ingredients.parallelStream().map(i -> {
                HttpHeaders httpHeaders = new HttpHeaders();
                String quantity = i.getQuantity();
                Ingredient ingredient = new IngredientCommand(i.getIngredientId(), httpHeaders, restTemplate).execute();
                ingredient.setQuantity(quantity);
                ingredient.setIngredientId(i.getIngredientId());
                ingredients.add(ingredient);
                return ingredient;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

}
