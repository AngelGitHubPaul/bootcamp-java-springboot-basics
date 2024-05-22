package com.zuitt.postApp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.zuitt.postApp.config.JwtToken;
import com.zuitt.postApp.models.JwtRequest;
import com.zuitt.postApp.models.JwtResponse;
import com.zuitt.postApp.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    // Can be: @PostMapping("/authenticate")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)

    // This method handles the creation of an authentication token
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        // Call the authenticate method to verify the user's credentials
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // loads the user details using the provided username
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // Generate a JWT token for the authenticated user using the user details
        final String token = jwtToken.generateToken(userDetails);

        // Returns an HTTP response with status code 200 (OK)
        // the body of response contains a new JWT response
        return ResponseEntity.ok(new JwtResponse(token));

    }

    // the method handles user authentication
    private void authenticate(String username, String password) throws Exception {

        try {
            // Responsible for authenticating a user based on the credentials provided
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
