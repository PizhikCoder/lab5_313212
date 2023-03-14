package org.example.core.exceptions;

/**
 *Thrown out if command arguments are invalid or missing.
 */
public class CommandParamsException extends Exception{
    public CommandParamsException(int argumentsCount, int expectedArgumentsCount){
        super(String.format("Received %s arguments, expected %s.", argumentsCount, expectedArgumentsCount));
    }
}
