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
public class Comments {
    
    private int id ; 
    private int commentaire_id ; 
    private int id_user ;
    private String response ; 
    private String date ; 

    public Comments(int id, int commentaire_id, int id_user, String response, String date) {
        this.id = id;
        this.commentaire_id = commentaire_id;
        this.id_user = id_user;
        this.response = response;
        this.date = date;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentaire_id() {
        return commentaire_id;
    }

    public void setCommentaire_id(int commentaire_id) {
        this.commentaire_id = commentaire_id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comments{" + "id=" + id + ", commentaire_id=" + commentaire_id + ", id_user=" + id_user + ", response=" + response + ", date=" + date + '}';
    }
    
    
    
    
}
