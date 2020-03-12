package com.xdinuka.recipeio.recipeservice.controller;

import com.xdinuka.recipeio.recipeservice.hystrix.IngredientCommand;
import com.xdinuka.recipeio.recipeservice.model.Ingredient;
import com.xdinuka.recipeio.recipeservice.model.Recipe;
import com.xdinuka.recipeio.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    final RecipeService recipeService;
    final RestTemplate restTemplate;

    @Autowired
    public RecipeController(RecipeService recipeService, RestTemplate restTemplate) {
        this.recipeService = recipeService;
        this.restTemplate = restTemplate;
    }


    @GetMapping
    List<Recipe> fetchAll() {
        return recipeService.fetchAll();
    }

    @PostMapping
    Recipe insertOrUpdate(@RequestBody Recipe recipe) {
        recipe.getIngredients().stream().forEach(i->{
            i.setRecipe(recipe);
        });
        return recipeService.saveOrUpdate(recipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> fetch(@PathVariable Integer id) {
        Optional<Recipe> optionalRecipe = recipeService.fetchById(id);
        if(optionalRecipe.isPresent()){
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            Recipe recipe = optionalRecipe.get();
            recipe.getIngredients().stream().forEach(i->{
                HttpHeaders httpHeaders = new HttpHeaders();
                String quantity = i.getQuantity();
                Ingredient ingredient = new IngredientCommand(i.getIngredientId(), httpHeaders, restTemplate).execute();
                System.out.println(ingredient);
                ingredient.setQuantity(quantity);
                ingredients.add(ingredient);
            });
            recipe.setIngredients(ingredients);
            optionalRecipe = Optional.of(recipe);
        }
        return ResponseEntity.of(optionalRecipe);
    }

    @GetMapping("/ingredient/{ingredientID}")
    public List<Recipe> fetchWithIngredients(@PathVariable Integer ingredientID){
        return recipeService.fetchAllWithIngredient(ingredientID);
    }

    @PostMapping("/ingredient")
    public List<Recipe> fetchAllWithIngredients(@RequestBody List<Integer> ingredientIDs){
        return recipeService.fetchAllWithIngredients(ingredientIDs);
    }

}