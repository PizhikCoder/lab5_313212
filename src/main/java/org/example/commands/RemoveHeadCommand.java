package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;

/**
 * The class contains an implementation of the remove_head command
 */
public class RemoveHeadCommand extends Command{
    private Invoker invoker;

    public RemoveHeadCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (invoker.getModelsManager().getModels().size() == 0){
            return "Models collection is empty!";
        }
        invoker.getPrinter().print(invoker.getModelsManager().getModels().getFirst().toString());
        invoker.getModelsManager().removeById(invoker.getModelsManager().getModels().getFirst().getId());
        return "Remove head command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"remove_head\": This command shows the first model in the collection and than removes it.");
    }
}
