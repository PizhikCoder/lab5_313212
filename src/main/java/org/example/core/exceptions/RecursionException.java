package org.example.core.exceptions;

/**
 * Thrown out when script execution recursion depth is exceeded.
 */
public class RecursionException extends Exception{
    public RecursionException(){
        super("Recursion has been detected.");
    }
}
