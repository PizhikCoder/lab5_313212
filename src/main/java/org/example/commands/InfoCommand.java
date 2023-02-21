package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;

/**
 * The class contains an implementation of the info command
 */
public class InfoCommand extends Command{
    private Invoker invoker;

    public InfoCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        return String.format("Collection info:" +
                "\n---Type: MusicBand" +
                "\n---Date of initialization: %s" +
                "\n---Elements count: %s", invoker.getModelsManager().getCreationDate(), invoker.getModelsManager().getModels().size());
    }

    @Override
    public String getCommandInfo() {
        return "Command \"info\": This command outputs information about the collection (type, initialisation date, number of items)";
    }
}
