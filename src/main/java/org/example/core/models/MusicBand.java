package org.example.core.models;

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
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public MusicBand(MusicBandClone musicBandClone){
        this.id = musicBandClone.getId();
        this.name = musicBandClone.getName();
        this.coordinates = musicBandClone.getCoordinates();
        this.creationDate = ZonedDateTime.parse(musicBandClone.getCreationDate());
        this.numberOfParticipants = musicBandClone.getNumberOfParticipants();
        this.genre = musicBandClone.getGenre();
        this.frontMan = musicBandClone.getFrontMan();
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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }

    @Override
    public String toString() {
        return String.format("\nID: %s\nName: %s\nCoordinates: %s\nCreation date: %s\nNumber of participants: %s\nGenre: %s\nFront man: \n%s", id,name,coordinates,creationDate,numberOfParticipants, ModelsValidator.fastNullCheck(genre).toLowerCase(),frontMan);
    }
}
