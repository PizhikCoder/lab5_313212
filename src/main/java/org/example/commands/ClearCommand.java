package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;

/**
 * The class contains an implementation of the clear command
 */
public class ClearCommand extends Command{
    private Invoker invoker;
    public ClearCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        invoker.getModelsManager().removeAll();
        return "Models cleared!";
    }

    @Override
    public String getCommandInfo() {
        return "Command \"clear\": This command remove all models from collection.";
    }
}
