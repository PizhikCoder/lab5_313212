package org.example.commands;

import org.example.commands.enums.DataField;
import org.example.core.Invoker;
import org.example.core.models.*;
import org.example.core.validators.CommandsDataValidator;
import org.example.interfaces.IPrinter;
import org.example.interfaces.Validator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The class contains an implementation of the add command
 */
public class AddCommand extends Command {
    private final Invoker invoker;

    private final int LOCATION_X_INDEX = 0;
    private final int LOCATION_Y_INDEX = 1;
    private final int LOCATION_Z_INDEX = 2;
    private final int LOCATION_COORDINATES_COUNT = 3;
    private final int COORDINATE_Y_LIMIT = 742;

    public AddCommand(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public String execute(String... arguments) {
        Map<DataField,Object> data = collectData();
        invoker.getModelsManager().addModels(invoker.getModelsManager().createModel(data));
        return "Object was successfully created!";
    }

    /**
     * The method is responsible for collecting data from the user.
     * @return Returns an object with data to create a model.
     */
    public Map<DataField, Object> collectData(){
        IPrinter printer = invoker.getPrinter();
        Map<DataField, Object> data = new HashMap<>();
        printer.print("Enter Band Name:");
        data.put(DataField.NAME, CommandsDataValidator.nameCheck(invoker.getListener().nextLine(), invoker.getListener(), invoker.getPrinter(),false));

        printer.print("Enter Coordinates:");
        printer.print("--Enter X(Integer):");
        int x = (int)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Integer.class,false);
        Validator<Double> yValidator = n->n<=COORDINATE_Y_LIMIT;
        printer.print("--Enter Y(Double, <=742):");
        double y = (double)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Double.class,false, yValidator);
        data.put(DataField.COORDINATES, new Coordinates(x,y));

        Validator<Integer> numberOfParticipantsValidator = n->n>0;
        printer.print("Enter number of participants(must be > 0):");
        data.put(DataField.NUMBER_OF_PARTICIPANTS, CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, Integer.class,false, numberOfParticipantsValidator));

        printer.print("Enter genre of music, please.");
        printer.print("Available genres: " + Arrays.toString(MusicGenre.values()));
        data.put(DataField.GENRE, (MusicGenre)CommandsDataValidator.enumCheck(invoker.getListener().nextLine(), invoker.getListener(), printer, MusicGenre.class, true));


        String name;
        Float height;
        Country nationality;
        Number[] locationData = new Number[LOCATION_COORDINATES_COUNT];
        printer.print("Enter person's params:");
        printer.print("--Enter person's name(Not null):");
        name = CommandsDataValidator.nameCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,false);
        printer.print("--Enter person's height(Float >0):");
        Validator<Float> heightValidator = n->n==null || n>0;
        height = (Float)CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,true, heightValidator);
        printer.print("--Enter nationality.");
        printer.print("--Available nationalities: " + Arrays.toString(Country.values()));
        nationality = CommandsDataValidator.enumCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Country.class, false);
        printer.print("--Enter location params.");
        printer.print("----Enter X(Integer):");
        locationData[LOCATION_X_INDEX] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Integer.class,false);
        printer.print("----Enter Y(Float):");
        locationData[LOCATION_Y_INDEX] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,false);
        printer.print("----Enter Z(Float):");
        locationData[LOCATION_Z_INDEX] = CommandsDataValidator.numbersCheck(invoker.getListener().nextLine(),invoker.getListener(),printer,Float.class,false);
        Location location = new Location((int)locationData[LOCATION_X_INDEX],  (Float)locationData[LOCATION_Y_INDEX], (Float) locationData[LOCATION_Z_INDEX]);
        Person person = new Person(name,height,nationality,location);
        data.put(DataField.FRONTMAN, person);

        return data;
    }
    @Override
    public String getCommandInfo(){
        return String.format("Command \"add\": This command allows you to create a new music group model. When you enter the command, you will be prompted for fields to enter.");
    }
}
