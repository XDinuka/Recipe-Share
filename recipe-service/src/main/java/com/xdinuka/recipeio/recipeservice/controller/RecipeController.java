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
        System.out.println("all");
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
            Boolean[] isVegan = {true};
            recipe.getIngredients().stream().forEach(i->{
                HttpHeaders httpHeaders = new HttpHeaders();
                Ingredient ingredient = new IngredientCommand(i.getIngredientId(), httpHeaders, restTemplate).execute();
                isVegan[0] = isVegan[0] & ingredient.getIsVegan();
                ingredients.add(ingredient);
            });
            recipe.setIngredients(ingredients);
            recipe.setIsVegan(isVegan[0]);
            optionalRecipe = Optional.of(recipe);
        }


        return ResponseEntity.of(optionalRecipe);
    }

    @GetMapping("/with/{ingredientID}")
    public List<Recipe> fetchWithIngredients(@PathVariable Integer ingredientID){
//        System.out.println("hello1");
        return recipeService.fetchAllWithIngredient(ingredientID);
    }

    @PostMapping("/with")
    public List<Recipe> fetchAllWithIngredients(@RequestBody List<Integer> ingredientID){
        System.out.println("hello1");
        return recipeService.fetchAllWithIngredients(ingredientID.toArray(new Integer[ingredientID.size()]));
    }

}
