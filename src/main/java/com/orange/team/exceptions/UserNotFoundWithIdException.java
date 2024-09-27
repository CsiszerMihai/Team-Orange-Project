package com.orange.team.exceptions;

public class UserNotFoundWithIdException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "User not found with id: ";

    public UserNotFoundWithIdException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}