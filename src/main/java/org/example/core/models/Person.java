package org.example.core.models;

import org.example.core.Invoker;
import org.example.core.exceptions.FieldValueIsNotCorrectException;
import org.example.core.validators.ModelsValidator;

public class Person {
    private String name;
    private Float height;//Can be null, also >0
    private Country nationality;
    private Location location;

    private Person(){}

    public Person( String name, Float height,  Country nationality,  Location location) {
        setName(name);
        setHeight(height);
        setNationality(nationality);
        setLocation(location);
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
        if (!name.isBlank() || Invoker.getIsDataLoading()){
            this.name = name;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setHeight(Float height) {
        if (height == null || height > 0 || Invoker.getIsDataLoading()){
            this.height = height;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setNationality(Country nationality) {
        if (nationality != null || Invoker.getIsDataLoading()){
            this.nationality = nationality;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setLocation(Location location) {
        if (location != null || Invoker.getIsDataLoading()){
            this.location = location;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    @Override
    public String toString() {
        return String.format("---Name: %s\n---Height: %s\n---Nationality: %s\n---Location: \n%s", name, ModelsValidator.fastNullCheck(height), nationality.toString().toLowerCase(), location.toString());
    }
}
