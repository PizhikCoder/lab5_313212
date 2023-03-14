package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionException;
import org.example.core.models.MusicBand;

/**
 * The class contains an implementation of the show command
 */
public class ShowCommand extends Command{
    private final Invoker invoker;
    public ShowCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        for (MusicBand musicBand : invoker.getModelsManager().getModels()){
            invoker.getPrinter().print(musicBand.toString());
        }
        return "Command \"show\" executed!";
    }

    @Override
    public String getCommandInfo() {
        return "Command \"show\": This command print all elements of collection.";
    }
}
