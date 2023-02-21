package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;
import org.example.core.listeners.FileListener;
import org.example.core.validators.FileValidator;
import org.example.interfaces.IListener;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The class contains an implementation of the execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private static short recursionCounter = 0;
    private static Boolean recursionFlag = false;
    private static final short recursionLimit = 5;
    private static ArrayList<IListener> listenersBuffer = new ArrayList<>();
    private String filePath;
    private Invoker invoker;

    public ExecuteScriptCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... arguments) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        recursionCounter++;
        if (arguments.length == 0){
            throw new CommandParamsException("Received 0 arguments, expected 1.");
        }
        if (FileValidator.fileCheck(arguments[0])) {
            filePath = arguments[0];
            IListener listener = new FileListener(filePath, invoker);
            if (recursionCounter <= recursionLimit) {
                listenersBuffer.add(listener);
                listener.start();
                listener.stop();
            }
            else {
                for (IListener l : listenersBuffer){
                    l.stop();
                }
                recursionFlag = true;
                throw new RecursionLimitException("The recursion limit has been reached!");
            }
            if (listener.equals(listenersBuffer.get(0))){
                recursionCounter = 0;
                listenersBuffer = new ArrayList<>();
                if (recursionFlag)
                {
                    recursionFlag = false;
                    return "Something goes wrong while working with script file...";
                }
                return "Script was successfully executed!";

            }
        }
        return "Problem listener is ended his work. Check your script file.";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"execute_script <path>\": This command allows you to execute a script written to a file. Requirements: commands must be written out in a column, all arguments will be entered by the user. \nWARNING: maximum number of consecutive inputs to the script is 5. Takes a file name as an argument."
        +"\nArguments: String(not null)");
    }
}
