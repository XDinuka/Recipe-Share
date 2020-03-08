package com.xdinuka.recipeio.recipeservice.controller;

import com.xdinuka.recipeio.recipeservice.model.Recipe;
import com.xdinuka.recipeio.recipeservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return recipeService.fetchAll();
    }

    @PostMapping
    Recipe insertOrUpdate(@RequestBody Recipe recipe) {
        return recipeService.saveOrUpdate(recipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> fetch(@PathVariable Integer id) {
        Optional<Recipe> recipe = recipeService.fetchById(id);
        return ResponseEntity.of(recipe);
    }

}
