package org.example.core.exceptions;

/**
 * Discarded if the file does not exist.
 */
public class FileDoesNotExist extends Exception{
    private final static String EXCEPTION_LOG = "File does not exist!";
    public FileDoesNotExist(){
        super(EXCEPTION_LOG);
    }
}
