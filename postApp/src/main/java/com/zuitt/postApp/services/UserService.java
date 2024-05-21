package com.zuitt.postApp.services;


import com.zuitt.postApp.models.User;

import java.util.Optional;

// Services it serves as an abstract layer that separates the implementation details from the logic code
public interface UserService {

    // Method to create a user
    void createUser (User user);

    // Method to check if a username already exists during registration
    // "Optional" return type is used to handle the possibility of null values when performing data retrieval operations
    Optional<User> findByUsername (String username);
}
