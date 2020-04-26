package com.xdinuka.recipeio.postservice.service;

import com.xdinuka.recipeio.postservice.model.Post;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post saveOrUpdate(Post post);

    Optional<Post> fetchById(Integer id, HttpHeaders httpHeaders);

    void deleteById(Integer id);

    List<Post> fetchByRecipe(Integer id, HttpHeaders httpHeaders);

    List<Post> fetchByUser(Integer i, HttpHeaders httpHeaders);

    List<Post> fetchAll(HttpHeaders httpHeaders);

//    List<Recipe> fetchAll();
//
//    List<Recipe> fetchAllWithIngredient(Integer ingredientID);
//
//    List<Recipe> fetchAllWithIngredients(List<Integer> ingredientID);
//
//    List<IngredientOld> fetchIngredientDetails(Recipe recipe);
}
