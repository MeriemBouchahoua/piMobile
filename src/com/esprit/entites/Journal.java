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
public class Journal {
    
    
    private int id ; 
    private int IdUser;
    private int moods_id ;
   
    
      public Journal() {
    }
    
      
          public Journal(int id, int IdUser, int moods_id) {
        this.id = id;
        this.IdUser = IdUser;
        this.moods_id = moods_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getMoods_id() {
        return moods_id;
    }

    public void setMoods_id(int moods_id) {
        this.moods_id = moods_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.IdUser;
        hash = 67 * hash + this.moods_id;
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
        final Journal other = (Journal) obj;
        if (this.id != other.id) {
            return false;
        }
        return this.moods_id == other.moods_id;
    }
    
          
          
          
          
          
}
