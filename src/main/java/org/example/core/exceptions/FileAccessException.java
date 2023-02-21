package org.example.core.exceptions;

/**
 *Thrown out on file access error.
 */
public class FileAccessException extends Exception{
    public FileAccessException(String data){
        super(data);
    }
}
