package com.xdinuka.recipeio.ingredientservice.controller;

import com.xdinuka.recipeio.ingredientservice.model.Ingredient;
import com.xdinuka.recipeio.ingredientservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    List<Ingredient> fetchAll(){
        return ingredientService.fetchAll();
    }

    @PostMapping
    Ingredient insertOrUpdate(@RequestBody Ingredient ingredient){
        return ingredientService.saveOrUpdate(ingredient);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> fetch(@PathVariable Integer id){
        Optional<Ingredient> ingredient = ingredientService.fetchById(id);
//        if(ingredient.isPresent())
            return ResponseEntity.of(ingredient);
//        else
//            return ResponseEntity.notFound().build();
    }


}
