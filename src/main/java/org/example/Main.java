package org.example;

import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.printers.CLIPrinter;
import org.example.core.Invoker;
import org.example.core.datahandlers.YAMLHandler;
import org.example.core.validators.FileValidator;
import org.example.interfaces.IPrinter;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = args.length == 0 ? "data.yaml" : args[0];
        IPrinter printer = new CLIPrinter();
        YAMLHandler yamlHandler = new YAMLHandler(path, printer);
        Invoker invoker = new Invoker(printer, yamlHandler, yamlHandler);
        try{
            if (FileValidator.fileCheck(path)){
                invoker.loadData();
            }
        }
        catch (FileAccessException | FileDoesNotExist ex){
            printer.print(ex.getMessage());
            printer.print("Creating own data file...");
            try{
                yamlHandler.setFilePath("data.yaml");
                File file = new File("data.yaml");
                file.createNewFile();
                printer.print("data.yaml created.");
            }
            catch (IOException IOex){
                printer.print("Can not create new file!");
                System.exit(0);
            }
        }

        try{
            invoker.startListening();
        }
        catch (NoSuchElementException ex){
        }
        catch (Exception ex){
            printer.print("Fatal error!\nAll changes will be saved...");
            invoker.invokeCommand(invoker.getListener().getCommandsManager().getCommandsCollection().get("save"));
        }


    }
}