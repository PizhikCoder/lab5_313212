package org.example.core.exceptions;

/**
 * Discarded when arguments are received that are out of bounds.
 */
public class InvalidArgumentsLimitsException extends Exception{
    public InvalidArgumentsLimitsException(String message) {
        super(message);
    }
}
