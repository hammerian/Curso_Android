package com.example.simplemap;

public class Posicion {

    private String description;
    private String aLong;
    private String lati;

    public Posicion() {
    }

    public Posicion(String description, String aLong, String lati) {
        description = description;
        aLong = aLong;
        lati = lati;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getaLong() {
        return aLong;
    }

    public void setaLong(String aLong) {
        this.aLong = aLong;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }
}
