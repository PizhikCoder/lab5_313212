package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionException;
import org.example.core.validators.ModelsValidator;
/**
 * The class contains an implementation of the exit command
 */
public class ExitCommand extends Command{
    private final Invoker invoker;
    public ExitCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... args) throws RecursionException, FileAccessException, CommandParamsException, FileDoesNotExist {
        while (true){
            invoker.getPrinter().print("Do you want to save all changes?(y/n)");
            switch (ModelsValidator.fastNullCheck(invoker.getListener().nextLine())){
                case "y", "yes":
                    invoker.invokeCommand(invoker.getListener().getCommandsManager().getCommandsCollection().get("save"));
                    break;
                case "n", "no":
                    break;
                default:
                    return "Invalid answer. Try again...";
            }
            System.exit(0);
        }
    }

    @Override
    public String getCommandInfo() {
        return "Command \"exit\": This command terminates the programme by prompting you to save the changes.";
    }
}
