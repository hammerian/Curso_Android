package com.example.vistadelista;

import android.graphics.Bitmap;
import android.net.Uri;

public class MyListData {

    private String value1;
    private String value2;
    private String value3;

    private int imageValue;
    private Uri foto;
    private Bitmap imagen;

    public MyListData(String vlue1, String vlue2, String vlue3, int imgValue) {
        value1 = vlue1;
        value2 = vlue2;
        value3 = vlue3;
        imageValue = imgValue;
    }

    public MyListData(String value1, String value2, String value3, Uri foto) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.foto = foto;
    }

    public MyListData(String value1, String value2, String value3, Bitmap imagen) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.imagen = imagen;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String vlue1) {
        value1 = vlue1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String vlue2) {
        value2 = vlue2;
    }

    public int getImageValue() {
        return imageValue;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public void setImageValue(int imgValue) {
        imageValue = imgValue;
    }
}
