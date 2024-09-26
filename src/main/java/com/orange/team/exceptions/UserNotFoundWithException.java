package com.orange.team.exceptions;

public class UserNotFoundWithException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "User not found with id: ";

    public UserNotFoundWithException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}