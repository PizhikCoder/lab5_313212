package org.example.core.validators;

import org.example.core.Invoker;
import org.example.core.models.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Contains full logic of model fields validating.
 */
public class ModelsValidator {
    private static ArrayList<Long> usedIDs;

    /**
     * Checks the array of models.
     * @param musicBands
     * @param invoker
     * @return
     */
    public static MusicBandClone[] modelsCheck(MusicBandClone[] musicBands, Invoker invoker){
        usedIDs = invoker.getModelsManager().getUsedIDs();
        ArrayList<MusicBandClone> validatedClones = new ArrayList<>();
        for (MusicBandClone musicBand : musicBands){
            if (!idValueCheck(musicBand.getId())){
                musicBand.setId(generateNewId(usedIDs));
            }
            usedIDs.add(musicBand.getId());
            if (!modelCheck(musicBand, invoker)){
                invoker.getPrinter().print("Something's wrong with model ID: " + musicBand.getId());
                invoker.getPrinter().print("The model will automatically be deleted after the \"save\" command is executed. But you can check the values in the data.yaml file yourself to avoid losses and then simply reload the programme.");
            }
            else {
                validatedClones.add(musicBand);
            }
        }
        MusicBandClone[] toReturn = new MusicBandClone[validatedClones.size()];
        return validatedClones.toArray(toReturn);
    }

    /**
     * Checks the only one model.
     * @param musicBand
     * @param invoker
     * @return
     */
    public static Boolean modelCheck(MusicBandClone musicBand, Invoker invoker){


        return nameValueCheck(musicBand.getName())
                && coordinatesValueCheck(musicBand.getCoordinates())
                && numberOfParticipantsValueCheck(musicBand.getNumberOfParticipants())
                && frontManValueCheck(musicBand.getFrontMan())
                && dateCheck(musicBand.getCreationDate());
    }

    /**
     * Checks id validity.
     * @param id input id.
     * @return validating result.
     */
    public static boolean idValueCheck(long id){
        if (id <= 0 || usedIDs.contains(id)){
            return false;
        }
        return true;
    }

    /**
     * Generate new unique id.
     * @param usedIDs
     * @return
     */
    private static long generateNewId(ArrayList<Long> usedIDs){
        Random rnd = new Random();
        long id = rnd.nextLong(Long.MAX_VALUE);
        while(usedIDs.contains(id)){
            id = rnd.nextLong();
        }
        return id;
    }
    /**
     * Checks time string validity.
     * @param dateTime input time string.
     * @return validating result.
     */
    public static boolean dateCheck(String  dateTime){
        try {
            ZonedDateTime.parse(dateTime);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    /**
     * Checks coordinates validity.
     * @param coordinates
     * @return validating result.
     */
    public static boolean coordinatesValueCheck(Coordinates coordinates){
        if (!coordinateYCheck(coordinates.getY())){
            return false;
        }
        return true;
    }
    /**
     * Checks person validity.
     * @param frontMan
     * @return validating result.
     */
    public static boolean frontManValueCheck(Person frontMan){
        if (nameValueCheck(frontMan.getName()) && personHeightValueCheck(frontMan.getHeight()) && nationalityValueCheck(frontMan.getNationality())){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Checks coordinate Y validity.
     * @param Y
     * @return validating result.
     */

    public static boolean coordinateYCheck(double Y){
        if (Y>742){
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Checks name validity.
     * @param name
     * @return validating result.
     */

    public static boolean nameValueCheck(String name){
        if (name == null || name.isBlank()){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Checks numberOfParticipants validity.
     * @param numberOfParticipants
     * @return validating result.
     */
    public static boolean numberOfParticipantsValueCheck(int numberOfParticipants){
        if (numberOfParticipants<=0){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Checks person's height validity.
     * @param height
     * @return validating result.
     */
    public static boolean personHeightValueCheck(Float height){
        if (height != null && height<=0){
            return false;
        }
        else {
            return true;
        }
    }
    /**
     * Checks nationality validity.
     * @param country
     * @return validating result.
     */

    public static boolean nationalityValueCheck(Country country){
        if (!Arrays.stream(country.values()).toList().contains(country)){
            return false;
        }
        else {
            return true;
        }
    }


    /**
     * Checks a field on the null value.
     * @param element
     * @return
     * @param <T>
     */
    public static <T> String fastNullCheck(T element){
        if (element == null){
            return "";
        }
        else {
            return element.toString();
        }
    }
}
