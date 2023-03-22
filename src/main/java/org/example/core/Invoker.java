package org.example.core;

import org.example.commands.Command;
import org.example.core.exceptions.*;
import org.example.core.listeners.CLIListener;
import org.example.core.managers.ModelsManager;
import org.example.core.models.MusicBand;
import org.example.core.models.MusicBandClone;
import org.example.core.validators.ModelsValidator;
import org.example.interfaces.*;

import java.util.ArrayDeque;

/**
 * Main class. Contains all the logic for linking all classes of the programme.
 */
public class Invoker {
    private static boolean isDataLoading = false;
    private IPrinter printer;
    private ModelsManager modelsManager;
    private IListener listener;
    private IDataSaver dataSaver;
    private IDataLoader dataLoader;
    public Invoker(IPrinter printer, IDataLoader dataLoader, IDataSaver dataSaver){
        printer.print("Creating invoker...");
        this.printer = printer;
        this.dataLoader = dataLoader;
        this.listener = new CLIListener(this);
        this.dataSaver = dataSaver;
        modelsManager = new ModelsManager(new ArrayDeque<MusicBand>(), this);
        printer.print("Invoker created.");
    }

    /**
     * This method loads data from file
     */
    public void loadData(){
        printer.print("Data loading started...");
        isDataLoading = true;
        ArrayDeque<MusicBand> queue = new ArrayDeque<>();
        try{
            MusicBand[] arr = ClonesParser.toOrigs(ModelsValidator.modelsCheck(dataLoader.load(MusicBandClone[].class), this));
            for(MusicBand i : arr){
                queue.add(i);
            }
        }
        catch (Exception ex){
            printer.print("Something went wrong while file was loading(or file is empty)!");

        }

        isDataLoading = false;
        modelsManager.addModels(queue);
        modelsManager.sort();
        printer.print("Data loading finished.");
    }

    /**
     * Start commands listening.
     */
    public void startListening(){
        printer.print("Starting listening...");
        listener.start();
    }

    /**
     * Invoke command logic.
     * @param command command's object.
     * @param arguments command's arguments.
     */
    public void invokeCommand(Command command, String ... arguments){
        try{
            if (command != null){
                printer.print(command.execute(arguments));
            }
        }
        catch (RecursionException | FileAccessException | CommandParamsException | FileDoesNotExist |
               ArgumentLimitsException ex){
            printer.print("Something went wrong while working with command.");
            printer.print(ex.getMessage());
        }
    }

    public static boolean getIsDataLoading(){
        return isDataLoading;
    }
    public IPrinter getPrinter() {
        return printer;
    }

    public ModelsManager getModelsManager() {
        return modelsManager;
    }

    public IListener getListener() {
        return listener;
    }

    public IDataLoader getDataLoader() {
        return dataLoader;
    }

    public IDataSaver getDataSaver() {
        return dataSaver;
    }
}
