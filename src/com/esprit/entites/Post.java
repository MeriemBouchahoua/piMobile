/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

import java.util.Date;

/**
 *
 * @author z.maryem
 */
public class Post {
    
    private int id ; 
    private int id_user ; 
    private String description ; 
    private String publication ; 
    private String nom_utilisateur;
    private String created_at ; 
    private String updated_at ; 
    private String likes; 
    private String dislike ;

    public Post(int id, int id_user, String description, String publication, String nom_utilisateur, String created_at, String updated_at, String likes, String dislike) {
        this.id = id;
        this.id_user = id_user;
        this.description = description;
        this.publication = publication;
        this.nom_utilisateur = nom_utilisateur;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.likes = likes;
        this.dislike = dislike;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", id_user=" + id_user + ", description=" + description + ", publication=" + publication + ", nom_utilisateur=" + nom_utilisateur + ", created_at=" + created_at + ", updated_at=" + updated_at + ", likes=" + likes + ", dislike=" + dislike + '}';
    }

    public Object getCreatedAt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
