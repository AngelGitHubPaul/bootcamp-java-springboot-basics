package com.zuitt.postApp.models;

import java.io.Serializable;

// Model represents a response object
public class JwtResponse  implements Serializable {

    private static final long serialVersionUID = 712715853772547151L;

    private final String jwttoken;

    // parameterize constructor
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    // getter method
    public String getJwttoken() {
        return jwttoken;
    }
}
