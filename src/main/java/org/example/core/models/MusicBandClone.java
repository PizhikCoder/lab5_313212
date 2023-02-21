package org.example.core.models;

public class MusicBandClone {
    private long id;//Field must be unique, >0 and generated automatically
    private String name;//Can not be empty
    private Coordinates coordinates;
    private String creationDate;
    private int numberOfParticipants;//Must be >0
    private MusicGenre genre;//Can be null
    private Person frontMan;


    public MusicBandClone(MusicBand musicBand) {
        this.id = musicBand.getId();
        this.name = musicBand.getName();
        this.coordinates = musicBand.getCoordinates();
        this.creationDate = musicBand.getCreationDate().toString();
        this.numberOfParticipants = musicBand.getNumberOfParticipants();
        this.genre = musicBand.getGenre();
        this.frontMan = musicBand.getFrontMan();
    }

    private MusicBandClone(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
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
}
