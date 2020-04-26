package com.xdinuka.recipeio.recipeservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.xdinuka.recipeio.recipeservice.model.StringMessage;
import com.xdinuka.recipeio.recipeservice.model.User;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

public class UserCommand extends HystrixCommand<User> {

    HttpHeaders httpHeaders;
    RestTemplate restTemplate;
    Integer userID;

    public UserCommand(Integer userID, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.userID = userID;
        this.httpHeaders = httpHeaders;
//        httpHeaders.set("Authorization", httpHeaders.get("Authorization2").get(0));
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        httpHeaders.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
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
