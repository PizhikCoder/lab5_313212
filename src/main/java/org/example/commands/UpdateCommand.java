package org.example.commands;

import org.example.commands.enums.DataField;
import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionException;
import org.example.core.validators.CommandsDataValidator;

import java.util.Map;

/**
 * The class contains an implementation of the update command
 */
public class UpdateCommand extends Command{
    private final Invoker invoker;
    public UpdateCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        long id = (long)CommandsDataValidator.numbersCheck(args[0], invoker.getListener(),invoker.getPrinter(), Long.class, false);
        if (invoker.getModelsManager().findModelById(id) != null){
            Map<DataField, Object> data =  ((AddCommand)invoker.getListener().getCommandsManager().getCommandsCollection().get("add")).collectData();
            invoker.getModelsManager().updateModel(id, data);
            return "Object updating executed!";
        }
        else {
            return "Can not update.";
        }
    }

    @Override
    public String getCommandInfo() {
        return "Command \"update <id>\": This command allows user to update existing objects in collection."
                + "\nArguments: Integer(>0)";
    }
}
