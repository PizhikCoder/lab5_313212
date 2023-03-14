package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.*;
import org.example.core.validators.CommandsDataValidator;

/**
 * The class contains an implementation of the remove_by_id command
 */
public class RemoveByIdCommand extends Command{
    private final Invoker invoker;
    private final int EXPECTED_ARGUMENTS_COUNT = 1;
    private final int ID_INDEX = 0;
    public RemoveByIdCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException(0, EXPECTED_ARGUMENTS_COUNT);
        }
        if (invoker.getModelsManager().getModels().size() == 0){
            return "Collection is empty!";
        }
        long id = (long)CommandsDataValidator.numbersCheck(args[ID_INDEX], invoker.getListener(),invoker.getPrinter(), Long.class, false);
        invoker.getModelsManager().removeById(id);
        return "Remove By Id command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"remove_by_id <id>\": This command removes the model with the specified id from the collection." +
                "\nArguments: Integer(>0)");
    }
}
