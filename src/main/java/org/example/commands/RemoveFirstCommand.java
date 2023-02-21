package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;

/**
 * The class contains an implementation of the remove_first command
 */
public class RemoveFirstCommand extends Command{
    private Invoker invoker;

    public RemoveFirstCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (invoker.getModelsManager().getModels().size() == 0){
            return "Models collection is empty!";
        }
        invoker.getModelsManager().removeById(invoker.getModelsManager().getModels().getFirst().getId());
        return "Remove command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"remove_first\": This command removes the first model in the collection.");
    }
}
