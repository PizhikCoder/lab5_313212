package org.example.core.validators;

import org.example.core.exceptions.InvalidArgumentsLimitsException;
import org.example.interfaces.IListener;
import org.example.interfaces.IPrinter;

import java.lang.reflect.Method;

/**
 * Contains the validation logic for all user input.
 */
public class CommandsDataValidator {
    /**
     * Checks the string to see if it is empty or consists only of spaces.
     * @param name line to validate.
     * @param listener commands listener.
     * @param printer current output printer.
     * @param allowedNull can be null?
     * @return validated string.
     */
    public static String nameCheck(String name, IListener listener, IPrinter printer, boolean allowedNull){
        if(name.isBlank() || name == null && !allowedNull){
            printer.print("The string entered was in the wrong format. \n" +
                    "Requirements: \n" +
                    "-The string must not be empty\n" +
                    "-The string must not be null\n" +
                    "Please, try again: ");
            return nameCheck(listener.nextLine(),listener, printer, allowedNull);
        }
        else{
            return name;
        }
    }

    /**
     * Prorates all numbers depending on the expected class.
     * @param line line to validate.
     * @param listener commands listner.
     * @param printer current output printer.
     * @param clazz expected number type.
     * @param allowedNull can be null?
     * @param args arguments for reflection method
     * @return validated number in needed type.
     */
    public static Number numbersCheck(String line, IListener listener, IPrinter printer, Class clazz, boolean allowedNull, Object ... args){
        try{
            if (line.isBlank() && allowedNull){
                return null;
            }
            if (clazz == Float.class){
                if (args.length==0 || reflectionValueCheck((Class) args[0], (String) args[1], Float.parseFloat(line))){
                    return Float.parseFloat(line);
                }
                throw new InvalidArgumentsLimitsException("Check values limits.");
            }
            if (clazz == Integer.class){
                if (args.length==0 || reflectionValueCheck((Class) args[0], (String) args[1], Integer.parseInt(line))){
                    return Integer.parseInt(line);
                }
                throw new InvalidArgumentsLimitsException("Check values limits.");
            }
            if(clazz == Double.class){
                if (args.length==0 || reflectionValueCheck((Class) args[0], (String) args[1], Double.parseDouble(line))){
                    return Double.parseDouble(line);
                }
                throw new InvalidArgumentsLimitsException("Check values limits.");
            }
            if(clazz == Long.class){
                if (args.length==0 || reflectionValueCheck((Class) args[0], (String) args[1], Long.parseLong(line))){
                    return Long.parseLong(line);
                }
                throw new InvalidArgumentsLimitsException("Check values limits.");
            }
        }
        catch (Exception ex){
            printer.print("The string entered was in the wrong format. \n" +
                    "Requirements: \n" +
                    "-The string must have the format: " + clazz.getName() + "\n" +
                    "-Check values limits.\n"+
                    "-Use command \"help\" for more information."+
                    "\nPlease, try enter value again: ");
            return numbersCheck(listener.nextLine(), listener, printer, clazz,allowedNull, args);
        }
        return null;
    }

    /**
     * A method for validating specific fields in a specific way. Uses ReflectionAPI.
     * @param clazz class with method-validator.
     * @param methodName method-validator name.
     * @param value object for validating.
     * @return validation verdict.
     */
    private static boolean reflectionValueCheck(Class clazz, String methodName, Object value){
        try {
            Method[] methods = clazz.getDeclaredMethods();
            Method method = null;
            for (Method i : methods){
                if (i.getName() == methodName) {
                    method = i;
                    break;
                }
            }
            if (method!= null && (boolean) method.invoke(null, value)){
                return true;
            }
            return false;
        }
        catch (Exception ex){
            return false;
        }
    }

    /**
     * Contains the validation logic for enums.
     * @param line user input.
     * @param listener commands listener.
     * @param printer current output printer.
     * @param enumClass enum class.
     * @param allowedNull can be null?
     * @return validated enum value.
     * @param <E>
     */
    public static  <E extends Enum> E enumCheck(String line, IListener listener, IPrinter printer, Class enumClass, boolean allowedNull){
        try{
            if (line.isBlank()  && allowedNull){
                return null;
            }
            return (E)E.valueOf(enumClass,line.toUpperCase());
        }
        catch (Exception ex){
            printer.print("The value entered is not in the list, try again: ");
            return enumCheck(listener.nextLine(), listener, printer, enumClass, allowedNull);
        }
    }

}
