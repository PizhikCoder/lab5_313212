package org.example.core.models;


public class Location {
    private int x;

    private Float y;

    private Location(){}

    public Location(int x,  Float y,  Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        this.y = y;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("------X: %s\n------Y: %s\n------Z: %s", x,y,z);
    }


}
