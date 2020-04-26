package com.xdinuka.recipeio.apigateway.service;

import com.xdinuka.recipeio.apigateway.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public interface UserService extends UserDetailsService {

//    String authenticate(String username,String password);

    Optional<User> fetchByUsername(String username);

    Optional<User> fetchByID(Integer id);

    User create(User user) throws SQLIntegrityConstraintViolationException;


    User changePassword(Integer id, String pwnew);
}
