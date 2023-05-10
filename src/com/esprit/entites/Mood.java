/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entites;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Mood {
    
    
    private int id ; 
    private int MoodId ; 
    private int UserId ; 
    private String Mood ; 
    private String Description ; 
    private String date ; 
 

        public Mood() {
    }
    
        
        
            public Mood(int id, int MoodId, int UserId, String Mood, String Description, String date) {
        this.id = id;
        this.MoodId = MoodId;
        this.UserId = UserId; 
        this.Mood = Mood;
        this.Description = Description;
        this.date = date;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoodId() {
        return MoodId;
    }

    public void setMoodId(int MoodId) {
        this.MoodId = MoodId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getMood() {
        return Mood;
    }

    public void setMood(String Mood) {
        this.Mood = Mood;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.MoodId;
        hash = 61 * hash + this.UserId;
        hash = 61 * hash + Objects.hashCode(this.Mood);
        hash = 61 * hash + Objects.hashCode(this.Description);
        hash = 61 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mood other = (Mood) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.MoodId != other.MoodId) {
            return false;
        }
        if (this.UserId != other.UserId) {
            return false;
        }
        if (!Objects.equals(this.Mood, other.Mood)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }
            
            
    
    
    
    
}




