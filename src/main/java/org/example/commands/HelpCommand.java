package org.example.commands;

import org.example.core.Invoker;

import java.util.Map;

/**
 * The class contains an implementation of the help command
 */
public class HelpCommand extends Command {
    private final Invoker invoker;
    public HelpCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String ... arguments) {
        Map<String, Command> commands = invoker.getListener().getCommandsManager().getCommandsCollection();
        for (Map.Entry<String, Command> entry : commands.entrySet()){
            invoker.getPrinter().print(commands.get(entry.getKey()).getCommandInfo());
        }
        return "Command \"help\" executed!";
    }

    @Override
    public String getCommandInfo() {
        return String.format("Command \"help\": This command outputs information about all the other commands.");
    }
}
