package com.example.firebaseproject;

import java.io.Serializable;

public class MyUser implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String phone;

    private String imageUser;

    public MyUser() {
    }

    public MyUser(String name, String surname, String email, String phone, String imageUser) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.imageUser = imageUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }
}
