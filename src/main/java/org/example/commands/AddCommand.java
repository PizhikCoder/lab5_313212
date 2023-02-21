package org.example.commands;

import org.example.core.Invoker;
import org.example.core.models.*;
import org.example.core.validators.CommandsDataValidator;
import org.example.core.validators.ModelsValidator;
import org.example.interfaces.IPrinter;

import java.util.Arrays;
import java.util.HashMap;

/**
 * The class contains an implementation of the add command
 */
public class AddCommand extends Command {
    private Invoker invoker;

    public AddCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... arguments) {
        HashMap<String,Object> data = collectData();
        invoker.getModelsManager().addModels(invoker.getModelsManager().createModel(data));
        return "Object was successfully created!";
    }

    /**
     * The method is responsible for collecting data from the user.
     * @return Returns an object with data to create a model.
     */
    public HashMap<String, Object> collectData(){
        IPrinter printer = invoker.getPrinter();
        HashMap<String, Object> data = new HashMap<>();
        printer.print("Enter Band Name:");
        data.put("name", CommandsDataValidator.nameCheck(invoker.getListener().nextLine(), invoker.getListener(), invoker.getPrinter(),false));

        printer.print("Enter Coordinates:");
        printer.print("--Enter X(Integer):");
        int x = (int)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Integer.class,false);
        printer.print("--Enter Y(Double, <=742):");
        double y = (double)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Double.class,false, ModelsValidator.class, "coordinateYCheck");
        data.put("coordinates", new Coordinates(x,y));

        printer.print("Enter number of participants(must be > 0):");
        data.put("numberOfParticipants", CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Integer.class,false, ModelsValidator.class, "numberOfParticipantsValueCheck"));

        printer.print("Enter genre of music, please.");
        printer.print("Available genres: " + Arrays.toString(MusicGenre.values()));
        data.put("genre", (MusicGenre)CommandsDataValidator.enumCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, MusicGenre.class, true));


        String name;
        Float height;
        Country nationality;
        Number[] loactionData = new Number[3];
        invoker.getListener();
        printer.print("Enter person's params:");
        printer.print("--Enter person's name(Not null):");
        name = CommandsDataValidator.nameCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,false);
        printer.print("--Enter person's height(Float >0):");
        height = (Float)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,true, ModelsValidator.class, "personHeightValueCheck");
        printer.print("--Enter nationality.");
        printer.print("--Available nationalities: " + Arrays.toString(Country.values()));
        nationality = CommandsDataValidator.enumCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Country.class, false);
        printer.print("--Enter location params.");
        printer.print("----Enter X(Integer):");
        loactionData[0] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Integer.class,false);
        printer.print("----Enter Y(Float):");
        loactionData[1] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,false);
        printer.print("----Enter Z(Float):");
        loactionData[2] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,false);
        Location location = new Location((int)loactionData[0],  (Float)loactionData[1], (Float) loactionData[2]);
        Person person = new Person(name,height,nationality,location);
        data.put("frontMan", person);

        return data;
    }
    @Override
    public String getCommandInfo(){
        return String.format("Command \"add\": This command allows you to create a new music group model. When you enter the command, you will be prompted for fields to enter.");
    }
}
