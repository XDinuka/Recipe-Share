package com.xdinuka.recipeio.recipeservice.controller;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import com.xdinuka.recipeio.recipeservice.model.StringMessage;
import com.xdinuka.recipeio.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class RecipeController {

    final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping
    Recipe insertOrUpdate(@RequestBody Recipe recipe) {

        recipe.setUserID(recipe.getUser().getId());

        return recipeService.saveOrUpdate(recipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> fetch(@PathVariable Integer id, @RequestHeader HttpHeaders httpHeaders) {

//        if (optionalRecipe.isPresent()) {
//
//            Recipe recipe = optionalRecipe.get();
//
//            List<IngredientOld> ingredientOlds = recipeService.fetchIngredientDetails(recipe);
//
//            recipe.setIngredientOlds(ingredientOlds);
//            optionalRecipe = Optional.of(recipe);
//        }
        return ResponseEntity.of(recipeService.fetchById(id, httpHeaders));
    }

    @PostMapping("/search/name")
    public List<Recipe> searchByName(@RequestBody StringMessage stringMessage, @RequestHeader HttpHeaders httpHeaders) {
        return recipeService.fetchAllLikeName(stringMessage.getValue(), httpHeaders);
    }

    @PostMapping("/search/name/vegan")
    public List<Recipe> searchByNameVeganOnly(@RequestBody StringMessage stringMessage, @RequestHeader HttpHeaders httpHeaders) {

        return recipeService.fetchAllLikeNameVegan(stringMessage.getValue(), httpHeaders);
    }


//
//    @GetMapping("/ingredient/{ingredientID}")
//    public List<Recipe> fetchWithIngredients(@PathVariable Integer ingredientID) {
//        return recipeService.fetchAllWithIngredient(ingredientID);
//    }
//
//    @PostMapping("/ingredient")
//    public List<Recipe> fetchAllWithIngredients(@RequestBody List<Integer> ingredientIDs) {
//        return recipeService.fetchAllWithIngredients(ingredientIDs);
//    }
//    @GetMapping
//    List<Recipe> fetchAll() {
//        List<Recipe> recipes = recipeService.fetchAll();
//        if (recipes != null) {
//            recipes.stream().forEach(recipe -> {
//                recipe.setIngredientOlds(recipeService.fetchIngredientDetails(recipe));
//            });
//        }
//        return recipes;
//    }
}
