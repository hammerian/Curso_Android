package com.example.sqldataview;

public class Ciudad {

    private String cityName;

    private int poblation;

    private int surface;

    public Ciudad() {
    }

    public Ciudad(int poblation, String cityName, int surface) {
        this.cityName = cityName;
        this.poblation = poblation;
        this.surface = surface;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPoblation() {
        return poblation;
    }

    public void setPoblation(int poblation) {
        this.poblation = poblation;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }
}
