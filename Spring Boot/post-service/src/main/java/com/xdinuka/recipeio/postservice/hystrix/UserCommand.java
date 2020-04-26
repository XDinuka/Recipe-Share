package com.xdinuka.recipeio.postservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xdinuka.recipeio.postservice.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {

    HttpHeaders httpHeaders;
    RestTemplate restTemplate;
    Integer userID;

    public UserCommand(Integer userID, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.userID = userID;
        this.httpHeaders = httpHeaders;
//        httpHeaders.set("Authorization", httpHeaders.get("Authorization2").get(0));
        this.restTemplate = restTemplate;
    }

    @Override
    protected User run() {
        try {
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            ResponseEntity<User> responseEntity = restTemplate.exchange("http://user-service/auth/id/" + userID, HttpMethod.GET, httpEntity, User.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    @Override
    protected User getFallback() {
        User user = new User();
        user.setUsername("Unknown User");
        return user;
    }
}
