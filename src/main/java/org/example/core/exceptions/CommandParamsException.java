package org.example.core.exceptions;

/**
 *Thrown out if command arguments are invalid or missing.
 */
public class CommandParamsException extends Exception{
    public CommandParamsException(String data){
        super(data);
    }
}
