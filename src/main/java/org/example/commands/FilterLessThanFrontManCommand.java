package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;
import org.example.core.models.MusicBand;
import org.example.core.validators.CommandsDataValidator;
import org.example.core.validators.ModelsValidator;

/**
 * The class contains an implementation of the filter_less_the_front_man command
 */
public class FilterLessThanFrontManCommand extends Command{
    private Invoker invoker;

    public FilterLessThanFrontManCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        if (args.length == 0){
            throw new CommandParamsException("Received 0 arguments, expected 1.");
        }

        if (invoker.getModelsManager().getModels().size()==0){
            return "Collection is empty!";
        }

        float height = (float) CommandsDataValidator.numbersCheck(args[0], invoker.getListener(),invoker.getPrinter(), Float.class, false, ModelsValidator.class, "personHeightValueCheck");
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
