package com.xdinuka.recipeio.recipeservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xdinuka.recipeio.recipeservice.model.Ingredient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IngredientCommand extends HystrixCommand<Ingredient> {

    HttpHeaders httpHeaders;
    RestTemplate restTemplate;
    Integer ingredientID;

    public IngredientCommand(Integer ingredientID, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.ingredientID = ingredientID;
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Ingredient run() throws Exception {

        ResponseEntity<Ingredient> responseEntity;
        HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
        responseEntity = restTemplate.exchange("http://ingredients/ingredients/" + ingredientID, HttpMethod.GET, httpEntity, Ingredient.class);
        return responseEntity.getBody();


    }
}
