package org.example.commands;

import org.example.commands.enums.DataField;
import org.example.core.Invoker;
import org.example.core.exceptions.*;
import org.example.core.validators.CommandsDataValidator;
import java.util.Map;

/**
 * The class contains an implementation of the add_if_min command.
 */
public class AddIfMinCommand extends Command{
    private final Invoker invoker;
    private final int ID_INDEX = 0;
    private final int EXPECTED_ARGUMENTS_COUNT = 1;

    public AddIfMinCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist, ArgumentLimitsException {
        if (args.length == 0){
            throw new CommandParamsException(0,EXPECTED_ARGUMENTS_COUNT);
        }
        long id = (long)CommandsDataValidator.numbersCheck(args[ID_INDEX], invoker.getListener(),invoker.getPrinter(), Long.class, false);
        if (id<=0){
            throw new ArgumentLimitsException(0);
        }

        if (invoker.getModelsManager().getModels().size()==0) return "Collection is empty";

        if (invoker.getModelsManager().getModels().getFirst().getId() > id){
            Map<DataField, Object> data = ((AddCommand)invoker.getListener().getCommandsManager().getCommandsCollection().get("add")).collectData();
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
