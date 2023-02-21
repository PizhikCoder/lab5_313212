package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;
import org.example.core.validators.ModelsValidator;
/**
 * The class contains an implementation of the exit command
 */
public class ExitCommand extends Command{
    private Invoker invoker;
    public ExitCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        while (true){
            invoker.getPrinter().print("Do you want to save all changes?(y/n)");
            switch (ModelsValidator.fastNullCheck(invoker.getListener().nextLine())){
                case "y":
                    invoker.invokeCommand(invoker.getListener().getParser().getCommandsCollection().get("save"));
                    System.exit(0);
                    break;
                case "yes":
                    invoker.invokeCommand(invoker.getListener().getParser().getCommandsCollection().get("save"));
                    System.exit(0);
                    break;
                case "n":
                    System.exit(0);
                    break;
                case "no":
                    System.exit(0);
                    break;
                default:
                    invoker.getPrinter().print("Invalid answer. Try again...");
                    break;
            }
        }
    }

    @Override
    public String getCommandInfo() {
        return "Command \"exit\": This command terminates the programme by prompting you to save the changes.";
    }
}
