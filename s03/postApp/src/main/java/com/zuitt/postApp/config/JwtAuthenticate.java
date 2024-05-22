package com.zuitt.postApp.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

// JwtAuthenticate contains the logic for verifying authenticity of user credentials, and throw an unauthorized message response if incorrect
public class JwtAuthenticate implements AuthenticationEntryPoint, Serializable {

    // Unique identifier for serialization
    private static final long serialVersionUID = -1072663257451601867L;

    // Method to commence the authentication process when an authentication exception occur
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Send an HTTP error response with status code 401 and message "Unauthorized"
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
