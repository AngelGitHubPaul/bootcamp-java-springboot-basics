package com.zuitt.postApp.controllers;

import com.zuitt.postApp.exceptions.UserException;
import com.zuitt.postApp.models.User;
import com.zuitt.postApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


// @RestController signals that this class is a controller that handles web requests and produces HTTP responses
@RestController
// @CrossOrigin  is used to enable Cross-Origin Resource Sharing (CORS) for specific controller endpoints or globally for the entire application.
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    // Can be: @PostMapping("/users/register")
    // @RequestMapping is a widely used annotation for mapping web requests to specific handler methods in your controller classes.
    @RequestMapping(value="/users/register", method = RequestMethod.POST)

    public ResponseEntity<Object> register(@RequestBody Map<String, String> body) throws UserException {

        // Extract username from the request body
        String username = body.get("username");

        // Check if the username already exists
        if(!userService.findByUsername(username).isEmpty()) {
            throw new UserException("Username already exists.");
        } else {
            // Extract password from the request body
            String password = body.get("password");
            // hash the password from the request body
            String encodedPassword = new BCryptPasswordEncoder().encode(password);

            // Create a new User object with the provided username and encodedPassword
            User newUser = new User(username, encodedPassword);

            // save the new user to the database
            userService.createUser(newUser);

            // return a successful response with status code 201(CREATED)
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
    }

}
