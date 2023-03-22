package org.example.core.listeners;

import org.example.core.managers.CommandsManager;
import org.example.core.Invoker;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.validators.FileValidator;
import org.example.interfaces.IListener;

import java.io.*;
/**
 * Contains tools to listen from file.
 */
public class FileListener implements IListener {
    private String filePath;
    private Boolean isWorking;
    private BufferedReader reader;
    private final Invoker invoker;
    private CommandsManager parser;

    public FileListener(String filePath, Invoker invoker){
        this.filePath = filePath;
        this.invoker = invoker;
    }

    /**
     * Starts listening to commands from the file using BufferedReader
     */
    @Override
    public void start() {
        isWorking = true;
        createReader();
        parser = new CommandsManager(invoker);
        try{
            String line;
            while (isWorking){
                line = reader.readLine();
                if (line == null){
                    isWorking = false;
                    break;
                }
                parser.parseLine(line);
            }
        }
        catch (IOException ex){
            invoker.getPrinter().print("Something went wrong with script file...");
        }
    }
    /**
     * Stops listening to commands from the file.
     */
    public void stop(){
        isWorking = false;
    }
    /**
     * Returns a string from the file using BufferedReader.
     * @return console input.
     */
    @Override
    public String nextLine() {
        try {
            return reader.readLine();
        }
        catch (Exception ex){
            invoker.getPrinter().print("Exception!!!\nError while reading from file.");
            return "";
        }
    }

    /**
     * Creates new BufferedReader for listening input from file.
     */
    private void createReader(){
        try{
            if (FileValidator.fileCheck(filePath)){
                reader = new BufferedReader(new FileReader(filePath));
            }
        }
        catch (FileAccessException | FileNotFoundException | FileDoesNotExist ex){
            invoker.getPrinter().print(ex.getMessage());
        }
    }

    @Override
    public Boolean getWorking() {
        return isWorking;
    }

    @Override
    public CommandsManager getCommandsManager() {
        return parser;
    }
}
