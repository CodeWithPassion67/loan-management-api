package com.github.copilot.loanapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with no detail message.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
        super(message, cause);
    }
}