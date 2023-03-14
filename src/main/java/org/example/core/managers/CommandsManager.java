package org.example.core.managers;

import org.example.commands.*;
import org.example.core.Invoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains tools for storing, parsing and returning command instances.
 */
public class CommandsManager {
    private Boolean isDataCollecting = false;
    private ArrayList<String> data = new ArrayList<>();
    private final Invoker invoker;
    private Map<String, Command> commandsCollection;

    public CommandsManager(Invoker invoker){
        this.invoker = invoker;
        initializeCommands();
    }

    /**
     * Return all allowed commands.
     * @return Map object with Command objects.
     */
    public Map<String, Command> getCommandsCollection() {
        return commandsCollection;
    }

    /**
     * Add commands to the HashMap.
     */
    private void initializeCommands()
    {
        commandsCollection = new HashMap<>();
        commandsCollection.put("help", new HelpCommand(invoker));
        commandsCollection.put("add", new AddCommand(invoker));
        commandsCollection.put("save", new SaveCommand(invoker));
        commandsCollection.put("execute_script", new ExecuteScriptCommand(invoker));
        commandsCollection.put("show", new ShowCommand(invoker));
        commandsCollection.put("exit", new ExitCommand(invoker));
        commandsCollection.put("update", new UpdateCommand(invoker));
        commandsCollection.put("clear", new ClearCommand(invoker));
        commandsCollection.put("remove_by_id", new RemoveByIdCommand(invoker));
        commandsCollection.put("remove_first", new RemoveFirstCommand(invoker));
        commandsCollection.put("remove_head", new RemoveHeadCommand(invoker));
        commandsCollection.put("add_if_min", new AddIfMinCommand(invoker));
        commandsCollection.put("group_counting_by_coordinates", new GroupCountingByCoordinatesCommand(invoker));
        commandsCollection.put("count_greater_than_front_man", new CountGreaterThanFrontManCommand(invoker));
        commandsCollection.put("filter_less_than_front_man", new FilterLessThanFrontManCommand(invoker));
        commandsCollection.put("info", new InfoCommand(invoker));
    }

    /**
     * Separates the command from its arguments, retrieves the command object if it exists and passes it to the invoker.
     * @param line Command line
     */
    public void parseLine(String line){
        if (line.contains(" ")){
            String[] commandLine = line.split(" ",2);
            invoker.invokeCommand(getCommand(commandLine[0]), commandLine[1]);
        }
        else{
            invoker.invokeCommand(getCommand(line));
        }
    }

    /**
     * Get command from the commands HashMap
     * @param line
     * @return Command object.
     */
    private Command getCommand(String line){
        if (commandsCollection.containsKey(line)){
            return commandsCollection.get(line);
        }
        invoker.getPrinter().print("Command does not exist!\nYou can see \"help\" with full list of allowed commands.");
        return commandsCollection.get("help");
    }
}
