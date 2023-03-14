package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionException;
import org.example.core.models.MusicBand;
import org.example.core.validators.CommandsDataValidator;
import org.example.interfaces.Validator;

/**
 * The class contains an implementation of the count_greater_than_front_man command
 */
public class CountGreaterThanFrontManCommand extends Command{
    private final Invoker invoker;
    private final int EXPECTED_ARGUMENTS_COUNT = 1;
    private final int HEIGHT_INDEX = 0;

    public CountGreaterThanFrontManCommand(Invoker invoker) {
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
        int count=0;
        Validator<Float> heightValidator = n->n==null || n>0;
        float height = (float)CommandsDataValidator.numbersCheck(args[HEIGHT_INDEX], invoker.getListener(),invoker.getPrinter(), Float.class, false, heightValidator);
        for(MusicBand musicBand : invoker.getModelsManager().getModels()){
            if (musicBand.getFrontMan().getHeight() != null && musicBand.getFrontMan().getHeight()>height){
                count++;
            }
        }

        invoker.getPrinter().print(String.format("Music bands with front men, that higher than %s: %s", height, count));

        return "Count Greater Than Front Man Command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"count_greater_than_front_man <height>\": This command allows you to count items whose frontMan field value is greater than the set value."
        +"\nArguments: Float (not null, >0)");
    }
}
