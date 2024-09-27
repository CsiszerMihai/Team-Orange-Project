package com.orange.team.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Customer not found with id: ";

    public CustomerNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}
