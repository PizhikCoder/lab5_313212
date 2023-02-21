package org.example.core.exceptions;

/**
 * Thrown out when script execution recursion depth is exceeded.
 */
public class RecursionLimitException extends Exception{
    public RecursionLimitException(String data){
        super(data);
    }
}
