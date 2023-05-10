/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entites;

/**
 *
 * @author rayen
 */
public class Utilisateur {
    
    
        private int id;
    private String firstname;
    private String lastname;
    private String adresse;
    private String email;
    private String gender;
    private String password;

    public Utilisateur(String firstname,String lastname, String adresse, String email, String gender, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.adresse = adresse;
        this.gender = gender;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lasttname) {
        this.lastname = lasttname;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", adresse=" + email + ", photoDeProfil="  + '}';
    }

    public Utilisateur(int id, String firstname, String email, String lastname, String gender, String adresse) {
        this.id = id;
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
        this.gender = gender;
        this.adresse = adresse;
    }

    public Utilisateur(String firstname, String email, String lastname, String gender, String adresse) {
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
        this.gender = gender;
        this.adresse = adresse;
    }
    
    
    
}
