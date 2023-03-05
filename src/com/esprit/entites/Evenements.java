/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

/**
 *
 * @author b.maryem
 */
public class Evenements {
    
    private int id ; 
    private String nom_evenement ; 
    private String lieu_evenement ; 
    private String date_evenement ; 
    private String description_evenement ; 
    private int nbr_de_places ; 
    private String type ; 
    private String image ; 
    private String heure ; 
    private String created_at; 
    private String updated_at ;
    private int likes; 
    private int dislikes ; 

    public Evenements() {
    }

    public Evenements(int id, String nom_evenement, String lieu_evenement,
            String date_evenement, String description_evenement, int nbr_de_places, String type, String image, String heure, String created_at, String updated_at, int likes, int dislikes) {
        this.id = id;
        this.nom_evenement = nom_evenement;
        this.lieu_evenement = lieu_evenement;
        this.date_evenement = date_evenement;
        this.description_evenement = description_evenement;
        this.nbr_de_places = nbr_de_places;
        this.type = type;
        this.image = image;
        this.heure = heure;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }

    public String getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    public int getNbr_de_places() {
        return nbr_de_places;
    }

    public void setNbr_de_places(int nbr_de_places) {
        this.nbr_de_places = nbr_de_places;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    
    
    
    
    
}
