package com.xdinuka.recipeio.apigateway.service;

import com.xdinuka.recipeio.apigateway.model.User;
import com.xdinuka.recipeio.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {//,JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public Optional<User> fetchByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> fetchByID(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User user) throws SQLIntegrityConstraintViolationException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User changePassword(Integer id, String pwnew) {
        User one = userRepository.getOne(id);
        one.setPassword(passwordEncoder.encode(pwnew));
        return userRepository.saveAndFlush(one);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = fetchByUsername(s);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException("Username is not found");
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
