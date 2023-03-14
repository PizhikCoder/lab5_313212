package org.example.core.exceptions;

public class ArgumentLimitsException extends Exception{
    public ArgumentLimitsException(int downLimit) {
        super(String.format("ID value must be >%s", downLimit));
    }
    public ArgumentLimitsException(int downLimit, int upLimit) {
        super(String.format("ID value must be %s< and <%s", downLimit, upLimit));
    }
}
