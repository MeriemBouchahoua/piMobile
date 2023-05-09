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
public class Article {

    private int id;
    private int id_user;
    private String article;
    private String nom_utilisateur;
    private String image;
    private Date created_at;
    private Date updated_at;

    public Article(int id, int id_user, String article, String nom_utilisateur, String image, Date created_at, Date updated_at) {
        this.id = id;
        this.id_user = id_user;
        this.article = article;
        this.nom_utilisateur = nom_utilisateur;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Article() {
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}
