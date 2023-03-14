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
 * The class contains an implementation of the filter_less_the_front_man command
 */
public class FilterLessThanFrontManCommand extends Command{
    private final Invoker invoker;
    private final int EXPECTED_ARGUMENTS_COUNT = 1;


    public FilterLessThanFrontManCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException(0, EXPECTED_ARGUMENTS_COUNT);
        }

        if (invoker.getModelsManager().getModels().size()==0){
            return "Collection is empty!";
        }
        Validator<Float> heightValidator = n->n==null || n>0;
        float height = (float) CommandsDataValidator.numbersCheck(args[0], invoker.getListener(),invoker.getPrinter(), Float.class, false, heightValidator);
        for(MusicBand musicBand : invoker.getModelsManager().getModels()){
            if (musicBand.getFrontMan().getHeight() != null && musicBand.getFrontMan().getHeight()<height){
                invoker.getPrinter().print(musicBand.toString());
            }
        }


        return "Filter Less Than Front Man Command executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"filter_less_than_front_man <height>\": This command allows you to output items whose frontMan field value is less than the set value."
        +"\nArguments: Float (not null, >0)");
    }
}
