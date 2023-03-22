package org.example.core.models;


import org.example.core.Invoker;
import org.example.core.exceptions.FieldValueIsNotCorrectException;

public class Location {
    private int x;

    private Float y;

    private Location(){}

    public Location(int x,  Float y,  Float z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    private Float z;

    public int getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Float y) {
        if (y!=null || Invoker.getIsDataLoading()){
            this.y = y;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    public void setZ(Float z) {
        if (z!=null || Invoker.getIsDataLoading()){
            this.z = z;
        }
        else {
            throw new FieldValueIsNotCorrectException();
        }
    }

    @Override
    public String toString() {
        return String.format("------X: %s\n------Y: %s\n------Z: %s", x,y,z);
    }


}
