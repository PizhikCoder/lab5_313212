package org.example.core.models;

import org.example.core.validators.ModelsValidator;

public class Person {
    private String name;
    private Float height;//Can be null, also >0
    private Country nationality;
    private Location location;

    private Person(){}

    public Person( String name, Float height,  Country nationality,  Location location) {
        this.name = name;
        this.height = height;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Float getHeight() {
        return height;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("---Name: %s\n---Height: %s\n---Nationality: %s\n---Location: \n%s", name, ModelsValidator.fastNullCheck(height), nationality.toString().toLowerCase(), location.toString());
    }
}
