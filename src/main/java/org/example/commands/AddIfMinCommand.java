package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;
import org.example.core.validators.CommandsDataValidator;
import org.example.core.validators.ModelsValidator;

import java.util.HashMap;

/**
 * The class contains an implementation of the add_if_min command.
 */
public class AddIfMinCommand extends Command{
    private Invoker invoker;

    public AddIfMinCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException("Received 0 arguments, expected 1.");
        }
        long id = (long)CommandsDataValidator.numbersCheck(args[0], invoker.getListener(),invoker.getPrinter(), Long.class, false);
        if (id<0){
            throw new CommandParamsException("ID value must be >0");
        }

        if (invoker.getModelsManager().getModels().size()==0) return "Collection is empty";

        if (invoker.getModelsManager().getModels().getFirst().getId() > id){
            HashMap<String, Object> data = ((AddCommand)invoker.getListener().getParser().getCommandsCollection().get("add")).collectData();
            invoker.getModelsManager().addModels(invoker.getModelsManager().createModel(data, id));
            return "Add If Min command executed!";
        }
        else return "Id is not minimal.";

    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"add_if_min <id>\": This command creates a new collection item with the specified id if it is smaller than the smallest id in the collection."
        +"\nArguments: Integer (>0)");
    }
}
