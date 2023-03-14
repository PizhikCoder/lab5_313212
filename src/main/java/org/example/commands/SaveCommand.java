package org.example.commands;

import org.example.core.ClonesParser;
import org.example.core.Invoker;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.RecursionException;

/**
 * The class contains an implementation of the save command
 */
public class SaveCommand extends Command {
    private final Invoker invoker;
    public SaveCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... arguments) throws RecursionException, FileAccessException {
        if (invoker.getDataSaver().save(ClonesParser.toClones(ClonesParser.dequeToArray(invoker.getModelsManager().getModels())))){
            return "Models was successfully saved";

        }
        return "Save command didn't execute.";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"save\": This command allows you to save all changes. ");
    }
}
