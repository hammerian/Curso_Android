package com.example.vistadelista;

public class MyListData {

    private String value1;
    private String value2;

    private int imageValue;

    public MyListData(String vlue1, String vlue2, int imgValue) {
        value1 = vlue1;
        value2 = vlue2;
        imageValue = imgValue;
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

    public void setImageValue(int imgValue) {
        imageValue = imgValue;
    }
}
