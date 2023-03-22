package org.example.core.exceptions;

/**
 *Thrown out if constructor arguments in the wrong format.
 */
public class FieldValueIsNotCorrectException extends RuntimeException{
    public FieldValueIsNotCorrectException(){
        super(String.format("Value was in the wrong format!"));
    }
}
