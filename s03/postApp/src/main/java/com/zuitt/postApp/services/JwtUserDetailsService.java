package com.zuitt.postApp.services;

// It is service implementation that retrieves the user details using Spring Security's UserDetailsService

import com.zuitt.postApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// @Component annotation in Spring is used to indicate that the class is a component of our application
@Component
// UserDetailsService - Core interface which loads user-specific data.
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // @Override indicates that this method overrides a method from superclass or interface
    @Override
    // loadUserByUsername is a method signature for loading user details by username
    // UsernameNotFoundException - Thrown if an UserDetailsService implementation cannot locate a User by its username.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Retrieve user details from the database using the provided username
        com.zuitt.postApp.models.User user = userRepository.findByUsername(username);

        // If user in NOT found, throw UsernameNotFoundException with appropriate message
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }

        // If user is found, create a UserDetails object with username, password, and empty arraylist of authorities
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
