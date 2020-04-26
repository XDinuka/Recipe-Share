package com.xdinuka.recipeio.recipeservice.service;

import com.xdinuka.recipeio.recipeservice.hystrix.UserCommand;
import com.xdinuka.recipeio.recipeservice.repository.RecipeRepository;
import com.xdinuka.recipeio.recipeservice.model.Recipe;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    final RecipeRepository recipeRepository;
    final RestTemplate restTemplate;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RestTemplate restTemplate) {
        this.recipeRepository = recipeRepository;
        this.restTemplate = restTemplate;

    }
//
//    @Override
//    public List<Recipe> fetchAll() {
//        return recipeRepository.findAll();
//    }

    @Override
    public Recipe saveOrUpdate(Recipe recipe) {
        recipe.setUserID(recipe.getUser().getId());
        recipe.getIngredients().parallelStream().forEach(recipeIngredient -> {
            recipeIngredient.setRecipe(recipe);
        });
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> fetchById(Integer id, HttpHeaders httpHeaders) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            recipe.setUser(new UserCommand(recipe.getUserID(), httpHeaders, restTemplate).execute());
            optionalRecipe = Optional.of(recipe);
        }

//        if(optionalRecipe.isPresent()){
//            Recipe recipe = optionalRecipe.get();
//            recipe.getRecipeIngredients().parallelStream().forEach(recipeIngredient -> {
//                String execute = new IngredientCommand(recipeIngredient.getIngredient(), httpHeaders, restTemplate).execute();
//                recipeIngredient.setName(execute);
//            });
//        }
        return optionalRecipe;
    }

    @Override
    public List<Recipe> fetchAllLikeName(String name, HttpHeaders httpHeaders) {
        List<Recipe> recipeList = recipeRepository.findByNameContains(name);
        recipeList.parallelStream().forEach(recipe -> {
            recipe.setUser(new UserCommand(recipe.getUserID(), httpHeaders, restTemplate).execute());
        });
//        recipeList.parallelStream().forEach(recipe -> {
//            recipe.getRecipeIngredients().parallelStream().forEach(recipeIngredient -> {
//                String execute = new IngredientCommand(recipeIngredient.getIngredient(), httpHeaders, restTemplate).execute();
//                recipeIngredient.setName(execute);
//            });
//        });
        return recipeList;
    }

    @Override
    public List<Recipe> fetchAllLikeNameVegan(String name, HttpHeaders httpHeaders) {
        Recipe recipe1 = new Recipe();
        recipe1.setName(name);
        recipe1.setVegan(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        List<Recipe> recipeList = recipeRepository.findAll(Example.of(recipe1, exampleMatcher));
        recipeList.parallelStream().forEach(recipe -> {
            recipe.setUser(new UserCommand(recipe.getUserID(), httpHeaders, restTemplate).execute());
        });
        return recipeList;
    }

//    @Override
//    public List<Recipe> fetchAllWithIngredient(Integer ingredientID) {
//        return recipeRepository.fetchRecipeIngredientInnerJoin(ingredientID);
//    }
//
//    @Override
//    public List<Recipe> fetchAllWithIngredients(List<Integer> ingredientIDs) {
//        List<Recipe> recipes = recipeRepository.fetchRecipeIngredientsInnerJoin(ingredientIDs, Long.valueOf(ingredientIDs.size()));
//
//        return recipes;
//    }

//    @Override
//    public List<IngredientOld> fetchIngredientDetails(Recipe recipe) {
//        List<IngredientOld> ingredientOlds = recipe.getIngredientOlds();
//        if (ingredientOlds != null) {
//            List<IngredientOld> collect = ingredientOlds.parallelStream().map(i -> {
//                HttpHeaders httpHeaders = new HttpHeaders();
//                String quantity = i.getQuantity();
//                IngredientOld ingredientOld = new IngredientCommand(i.getIngredientId(), httpHeaders, restTemplate).execute();
//                ingredientOld.setQuantity(quantity);
//                ingredientOld.setIngredientId(i.getIngredientId());
//                ingredientOlds.add(ingredientOld);
//                return ingredientOld;
//            }).collect(Collectors.toList());
//            return collect;
//        }
//        return null;
//    }

}
