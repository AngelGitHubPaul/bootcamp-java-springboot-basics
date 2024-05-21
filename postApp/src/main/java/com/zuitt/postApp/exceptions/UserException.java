package com.zuitt.postApp.exceptions;

public class UserException extends Exception {
    public UserException(String message) {
        // The message is added into the Exception constructor to properly add the message as an error message in our exception
        super(message);
    }
}
