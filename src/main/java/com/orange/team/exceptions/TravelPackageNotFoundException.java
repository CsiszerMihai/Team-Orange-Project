package com.orange.team.exceptions;

public class TravelPackageNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Travel Package not found with id: ";

    public TravelPackageNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}