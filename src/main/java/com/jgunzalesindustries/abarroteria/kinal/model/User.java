/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.jgunzalesindustries.abarroteria.kinal.model;

import java.math.BigDecimal;

/**
 *
 * @author informatica
 */
public class User {
    private String idUser;
    private String name;
    private String lastName;
    private String mail;
    private int idRol;
    
     public User(String idUser, String name, String lastName, String mail, String passwordHash, int idRol ) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.idRol = idRol;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }



    public int getIdRol() {
        return idRol;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

}
