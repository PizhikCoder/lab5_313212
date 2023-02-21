package org.example.core.exceptions;

/**
 * Discarded if the file does not exist.
 */
public class FileDoesNotExist extends Exception{
    public FileDoesNotExist(){
        super("File does not exist!");
    }
}
