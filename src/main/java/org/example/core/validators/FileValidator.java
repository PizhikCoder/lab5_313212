package org.example.core.validators;

import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;

import java.io.File;

/**
 * This class contains logic for file input validating.
 */
public class FileValidator {
    /**
     * Check file exist and file access.
     * @param path file path
     * @return is file usable?
     * @throws FileAccessException
     * @throws FileDoesNotExist
     */
    public static Boolean fileCheck(String path) throws FileAccessException, FileDoesNotExist {
        File file = new File(path);
        if (file.exists()){
            if (file.canRead() && file.canWrite()){
                return true;
            }
            else
            {
                throw new FileAccessException("Can not access the file...");
            }
        }
        throw new FileDoesNotExist();
    }
}
