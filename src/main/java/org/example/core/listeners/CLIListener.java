package org.example.core.listeners;

import org.example.core.managers.CommandsManager;
import org.example.core.Invoker;
import org.example.interfaces.IListener;
import org.example.interfaces.IPrinter;

import java.util.Scanner;

/**
 * Contains tools to listen for console input.
 */
public class CLIListener implements IListener {
    private Boolean isWorking = false;
    private CommandsManager commandsManager;
    private Scanner scanner;
    private final IPrinter printer;
    private final Invoker invoker;

    public CLIListener(Invoker invoker){
        this.invoker = invoker;
        printer = invoker.getPrinter();
    }


    /**
     * Starts listening to commands from the console using Scanner
     */
    @Override
    public void start() {
        isWorking = true;
        printer.print("CLIListener work is started.");
        scanner = new Scanner(System.in);
        commandsManager = new CommandsManager(invoker);
        while(isWorking){
            String line = scanner.nextLine();
            commandsManager.parseLine(line);
        }
    }

    /**
     * Starts listening to commands from the console.
     */
    public void stop(){
        isWorking = false;
    }

    /**
     * Returns a string from the console using Scanner.
     * @return console input.
     */
    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Boolean getWorking(){
        return isWorking;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }
}
