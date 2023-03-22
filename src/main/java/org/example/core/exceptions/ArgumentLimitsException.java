package org.example.core.exceptions;

/**
 *Thrown out if arguments limits are not allowed.
 */
public class ArgumentLimitsException extends Exception{
    public ArgumentLimitsException(int downLimit) {
        super(String.format("ID value must be >%s", downLimit));
    }
    public ArgumentLimitsException(int downLimit, int upLimit) {
        super(String.format("ID value must be %s< and <%s", downLimit, upLimit));
    }
}
