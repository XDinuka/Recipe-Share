package com.xdinuka.recipeio.postservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xdinuka.recipeio.postservice.model.Recipe;
import com.xdinuka.recipeio.postservice.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RecipeCommand extends HystrixCommand<Recipe> {

    HttpHeaders httpHeaders;
    RestTemplate restTemplate;
    Integer recipeID;

    public RecipeCommand(Integer recipeID, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.recipeID = recipeID;
        this.httpHeaders = httpHeaders;
//        httpHeaders.set("Authorization", httpHeaders.get("Authorization2").get(0));
        this.restTemplate = restTemplate;
    }

    @Override
    protected Recipe run() {
        try {
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            ResponseEntity<Recipe> responseEntity = restTemplate.exchange("http://recipe-service/" + recipeID, HttpMethod.GET, httpEntity, Recipe.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    @Override
    protected Recipe getFallback() {
        Recipe recipe = new Recipe();
        recipe.setName("Unknown Recipe");
        User user = new User();
        user.setUsername("Unknown User");
        recipe.setUser(user);
        return recipe;
    }
}
