package org.example.core.models;

import org.example.core.Invoker;
import org.example.core.exceptions.FieldValueIsNotCorrectException;

import java.util.Objects;

public class Coordinates {
    private int x;
    private double y;

    private Coordinates(){}

    public Coordinates(int x, double y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        if (y<=742 || Invoker.getIsDataLoading()){
            this.y = y;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    @Override
    public String toString() {
        return String.format("\n---X: %s\n---Y: %s",x,y);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }
        if (obj == null){
            return false;
        }
        Coordinates coordinates = (Coordinates)obj;
        return coordinates.x == this.x
                && coordinates.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
