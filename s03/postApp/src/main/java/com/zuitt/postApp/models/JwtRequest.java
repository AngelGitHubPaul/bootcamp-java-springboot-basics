package com.zuitt.postApp.models;

// Model represents a request object

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 2330793313238647992L;

    private String username;
    private String password;

    public JwtRequest() {
    }

    // constructor
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
