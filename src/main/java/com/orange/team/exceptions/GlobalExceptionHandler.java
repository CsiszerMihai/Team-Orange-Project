package com.orange.team.exceptions;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(UserNotFoundWithIdException.class)
    public ResponseEntity<String> userNotFoundWithIdException(UserNotFoundWithIdException userNotFoundWithIdException) {
        return new ResponseEntity<>(objectToString(Map.of("message", userNotFoundWithIdException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(TravelPackageNotFoundException.class)
    public ResponseEntity<String> travelPackageNotFoundException(TravelPackageNotFoundException travelPackageNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", travelPackageNotFoundException.getMessage())), NOT_FOUND);
    }

    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing response to string");
            return "Internal error";
        }
    }
}