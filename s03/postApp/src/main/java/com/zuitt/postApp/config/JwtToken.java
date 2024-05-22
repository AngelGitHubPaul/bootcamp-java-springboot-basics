package com.zuitt.postApp.config;

import com.zuitt.postApp.models.User;
import com.zuitt.postApp.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// JwtToken component responsible for JWT token generation, validation, and extraction of information
@Component
public class JwtToken implements Serializable {

    // @Value annotation is applied to fields in a class to inject values into those field
    // Injecting value jwt.secret from application.properties to String secret property
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    private static final long serialVersionUID = -6308941606032412771L;

    // It calculates the value by multiplying 5 (hours) by 60 (minutes) by 60 (seconds), resulting in 18,000 seconds.
    // Token validity period: 5 hours
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // Retrieves the username from the provided token
    public String getUsernameFromToken(String token) {
        // trims any leading of trailing whitespace from the token
        token = token.trim();
        // Retrieve the subject claim from the token
        String claim = getClaimFromToken(token, Claims::getSubject);
        return claim;
    }

    // Retrieves the expiration date from the provided token
    public Date getExpirationDateFromToken(String token) {
        token = token.trim();
        // Returns the expiration date claim from the token
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Retrieves specific claim from the provide JWT
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        token = token.trim();

        // Parse the token and retrieve all claims
        final Claims claims = getAllClaimsFromToken(token);

        // function used to extract a specific claim from the Claims object
        return claimsResolver.apply(claims);
    }

    // Extracts all claims from the provided JWT
    private Claims getAllClaimsFromToken(String token) {
        token = token.trim();

        // Parse the token and retrieve all claims from its body
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    // Checks whether a JWT has expired by comparing its expiration date with the current date
    private Boolean isTokenExpired(String token) {
        token = token.trim();

        // Retrieve the expiration date from the token
        final Date expiration = getExpirationDateFromToken(token);

        // Check if the expiration date is BEFORE the current date
        return expiration.before(new Date());
    }

    // Generate a JWT token based on the provided claims and subjects
    public String generateToken(UserDetails userDetails) {

        // Creates a map to store claims, including user details
        Map<String, Object> claims = new HashMap<>();

        // Retrieve user details from the database
        User user = userRepository.findByUsername(userDetails.getUsername());

        // Add user id as a claim
        claims.put("user", user.getId());

        // Generate and return the token
        return doGenerateToken(claims, userDetails.getUsername());
    }


    // Generates a JWT based on the provided claims and user details
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        // Build token
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // Validates the JWT by checking if it corresponds the given user details
    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
