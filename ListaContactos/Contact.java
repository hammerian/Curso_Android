package com.example.biometricthings.PruebasLoeches.ListaContactos;

import android.net.Uri;

public class Contact {

    private String nombre;
    private String numTelf;
    private Uri foto;
    private int fotoV;
    private String correo;
    //flag es 0, es del arraylist hardcodeado.
    //flag es 1, es del arraylist unido al nuevo contacto
    private int flag;

    public Contact() {
    }

    public Contact(String nombre, String numTelf, int fotoV, String correo, int flag) {
        this.nombre = nombre;
        this.numTelf = numTelf;
        this.fotoV = fotoV;
        this.correo = correo;
        this.flag = flag;
    }

    public Contact(String nombre, String numTelf, Uri foto, String correo, int flag) {
        this.nombre = nombre;
        this.numTelf = numTelf;
        this.foto = foto;
        this.correo = correo;
        this.flag = flag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    public Uri getFoto() {
        return foto;
    }


    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public int getFotoV() {
        return fotoV;
    }

    public void setFotoV(int fotoV) {
        this.fotoV = fotoV;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
