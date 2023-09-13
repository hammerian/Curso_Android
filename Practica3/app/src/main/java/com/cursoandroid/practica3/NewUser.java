package com.cursoandroid.practica3;

import java.io.Serializable;

public class NewUser implements Serializable {

    private String userEmail;
    private String userPassword;
    private String dni;
    private String userName;
    private String userSurname;
    private int telephone;

    public NewUser() {
    }

    public NewUser(String userEmail, String userPassword, String dni, String userName, String userSurname, int telephone) {
        this.userEmail = userEmail;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.dni = dni;
        this.userName = userName;
        this.userSurname = userSurname;
        this.telephone = telephone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

}
