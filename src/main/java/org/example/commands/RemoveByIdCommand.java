package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.*;
import org.example.core.validators.CommandsDataValidator;

/**
 * The class contains an implementation of the remove_by_id command
 */
public class RemoveByIdCommand extends Command{
    private Invoker invoker;
    public RemoveByIdCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException("Received 0 arguments, expected 1.");
        }
        if (invoker.getModelsManager().getModels().size() == 0){
            return "Collection is empty!";
        }
        long id = (long)CommandsDataValidator.numbersCheck(args[0], invoker.getListener(),invoker.getPrinter(), Long.class, false);
        invoker.getModelsManager().removeById(id);
        return "Remove By Id command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"remove_by_id <id>\": This command removes the model with the specified id from the collection." +
                "\nArguments: Integer(>0)");
    }
}
