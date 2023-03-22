package org.example.core.models;

import org.example.core.Invoker;
import org.example.core.exceptions.FieldValueIsNotCorrectException;
import org.example.core.validators.ModelsValidator;
import java.time.ZonedDateTime;

/**
 * Model's class-constructor.
 */
public class MusicBand {
    private long id;//Field must be unique, >0 and generated automatically
    private String name;//Can not be empty
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private int numberOfParticipants;//Must be >0
    private MusicGenre genre;//Can be null
    private Person frontMan;

    public MusicBand(long id, String name, Coordinates coordinates, int numberOfParticipants, MusicGenre genre, Person frontMan){
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(ZonedDateTime.now());
        setNumberOfParticipants(numberOfParticipants);
        setGenre(genre);
        setFrontMan(frontMan);
    }

    public MusicBand(MusicBandClone musicBandClone){
        this.id = musicBandClone.getId();
        setName(musicBandClone.getName());
        setCoordinates(musicBandClone.getCoordinates());
        setCreationDate(ZonedDateTime.parse(musicBandClone.getCreationDate()));
        setNumberOfParticipants(musicBandClone.getNumberOfParticipants());
        setGenre(musicBandClone.getGenre());
        setFrontMan(musicBandClone.getFrontMan());
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    private void setId(long id) {
        if (id>0 || Invoker.getIsDataLoading()){
            this.id = id;
        }
        else{
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setName(String name) {
        if (!name.isBlank() || Invoker.getIsDataLoading()){
            this.name = name;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null || Invoker.getIsDataLoading()){
            this.coordinates = coordinates;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate!=null || Invoker.getIsDataLoading()){
            this.creationDate = creationDate;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        if (numberOfParticipants > 0 || Invoker.getIsDataLoading()){
            this.numberOfParticipants = numberOfParticipants;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setFrontMan(Person frontMan) {
        if (frontMan != null || Invoker.getIsDataLoading()){
            this.frontMan = frontMan;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    @Override
    public String toString() {
        return String.format("\nID: %s\nName: %s\nCoordinates: %s\nCreation date: %s\nNumber of participants: %s\nGenre: %s\nFront man: \n%s", id,name,coordinates,creationDate,numberOfParticipants, ModelsValidator.fastNullCheck(genre).toLowerCase(),frontMan);
    }
}
