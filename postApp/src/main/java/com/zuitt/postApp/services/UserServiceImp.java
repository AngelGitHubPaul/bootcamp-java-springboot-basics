package com.zuitt.postApp.services;

import com.zuitt.postApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Service annotation is used to mark a class as a service provider in the service layer of your application
// It contains business logic and interact with repositories
@Service
public class UserServiceImp implements UserService {

    // @Autowired annotation is used for automatic dependency injection
    // Injecting an instance of UserRepository, which provides CRUD operations for User Entities
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        // .ofNullable method of Optional wraps result of "userRepository.findByUsername(username"
        // If user is found, it will wrapped in an Optional
        // If not found, result is null
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
