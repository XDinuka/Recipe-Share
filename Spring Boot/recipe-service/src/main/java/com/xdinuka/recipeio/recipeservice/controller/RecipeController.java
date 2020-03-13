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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    List<Recipe> fetchAll() {
        List<Recipe> recipes = recipeService.fetchAll();
        if(recipes!=null){
            recipes.stream().forEach(recipe -> {
                recipe.setIngredients(recipeService.fetchIngredientDetails(recipe));
            });
        }
        return recipes;
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

            Recipe recipe = optionalRecipe.get();

            List<Ingredient> ingredients = recipeService.fetchIngredientDetails(recipe);

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
