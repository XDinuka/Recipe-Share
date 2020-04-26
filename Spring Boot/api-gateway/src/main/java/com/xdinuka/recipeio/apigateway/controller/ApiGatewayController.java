package com.xdinuka.recipeio.apigateway.controller;

import com.xdinuka.recipeio.apigateway.model.StringMessage;
import com.xdinuka.recipeio.apigateway.model.User;
import com.xdinuka.recipeio.apigateway.service.JwtUtil;
import com.xdinuka.recipeio.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class ApiGatewayController {

    UserService userService;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;

    @Autowired
    public ApiGatewayController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/password")
    public ResponseEntity<User> changePassword(@RequestBody User user, @RequestHeader("Authorization") String auth) {



            Optional<User> optionalUser = userService.fetchByID(user.getId());
            if (optionalUser.isPresent()) {
                userService.changePassword(optionalUser.get().getId(), user.getPassword());
                return login(user);
            }


        return ResponseEntity.status(401).build();

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println(user);
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        try {
            userService.create(user);
        } catch (SQLIntegrityConstraintViolationException ex) {
            return ResponseEntity.badRequest().build();
        }
        return login(user1);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        } catch (AuthenticationException ex) {
//            return ResponseEntity.status(401).body(new StringMessage("Invalid Username or Password"));
//            ex.printStackTrace();
//            System.out.println("zero");
            return ResponseEntity.status(401).build();
        }

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        String s = jwtUtil.generateToken(userDetails);
//        System.out.println("one");
        Optional<User> optionalUser = userService.fetchByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
//            System.out.println("two");
            User user1 = optionalUser.get();
            user1.setPassword(s);
            return ResponseEntity.ok(user1);
        }
//        System.out.println("three");
        return ResponseEntity.status(401).build();

    }

    //    @GetMapping("/id/{id}")
//    public ResponseEntity<User> get(@PathVariable("id") Integer id) {
//        return ResponseEntity.of(apigateway.fetchByID(id));
//    }
//    @GetMapping("/hello")
//    public String get() {
//        return "Hello";
//    }
//
    @GetMapping("/username/{username}")
    public Boolean isUnique(@PathVariable("username") String username) {
        return !userService.fetchByUsername(username).isPresent();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> fetchByID(@PathVariable("id") Integer id) {
        Optional<User> optionalUser = userService.fetchByID(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(null);
            optionalUser = Optional.of(user);
        }
        return ResponseEntity.of(optionalUser);
    }


    @RequestMapping("/check")
    public StringMessage check(@RequestHeader HttpHeaders httpHeaders) {
//        httpHeaders.keySet().stream().forEach(s -> System.out.println(s));
        return new StringMessage("ok");
    }

}
