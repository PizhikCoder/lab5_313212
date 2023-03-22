package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionException;
import org.example.core.listeners.FileListener;
import org.example.core.validators.FileValidator;
import org.example.interfaces.IListener;
import java.util.LinkedList;

/**
 * The class contains an implementation of the execute_script command
 */
public class ExecuteScriptCommand extends Command {
    private String filePath;
    private IListener listener;
    private static LinkedList<IListener> listenersQueue = new LinkedList<>();
    private static boolean recursionFlag = false;
    private static LinkedList<String> pathChain = new LinkedList<>();
    private final Invoker invoker;
    private final int PATH_INDEX = 0;
    private final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ExecuteScriptCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException(0, EXPECTED_ARGUMENTS_COUNT);
        }
        if (FileValidator.fileCheck(args[PATH_INDEX])){
            filePath = args[PATH_INDEX];
            if (recursionCheck(filePath)){
                listener = new FileListener(filePath, invoker);
                listenersQueue.add(listener);
                listener.start();
                pathChain.remove(filePath);
            }
            else {
                for (IListener listener : listenersQueue){
                    listener.stop();
                }
                recursionFlag = true;
                throw new RecursionException();
            }
        }
        if (recursionFlag){
            if(this.listener.equals(listenersQueue.getFirst())){
                listenersQueue = new LinkedList<>();
                recursionFlag = false;
            }
            return "";
        }
        if(listener.equals(listenersQueue.getFirst())){
            listenersQueue = new LinkedList<>();
            return "Script was successfully executed!";
        }
        return "";
    }

    private boolean recursionCheck(String filePath){
        if (pathChain.contains(filePath)){
            return false;
        }
        pathChain.add(filePath);
        return true;
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"execute_script <path>\": This command allows you to execute a script written to a file. Requirements: commands must be written out in a column, all arguments will be entered by the user. \nWARNING: maximum number of consecutive inputs to the script is 5. Takes a file name as an argument."
        +"\nArguments: String(not null)");
    }
}
