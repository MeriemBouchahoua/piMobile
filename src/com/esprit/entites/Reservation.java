/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

import java.util.Objects;

/**
 *
 * @author b.maryem
 */
public class Reservation {
    
    private int id ; 
    private int nombre_de_place_areserve;
    private String email ; 
    private int evenements_id ;

    public Reservation() {
    }

    public Reservation(int id, int nombre_de_place_areserve, String email, int evenements_id) {
        this.id = id;
        this.nombre_de_place_areserve = nombre_de_place_areserve;
        this.email = email;
        this.evenements_id = evenements_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre_de_place_areserve() {
        return nombre_de_place_areserve;
    }

    public void setNombre_de_place_areserve(int nombre_de_place_areserve) {
        this.nombre_de_place_areserve = nombre_de_place_areserve;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEvenements_id() {
        return evenements_id;
    }

    public void setEvenements_id(int evenements_id) {
        this.evenements_id = evenements_id;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nombre_de_place_areserve != other.nombre_de_place_areserve) {
            return false;
        }
        if (this.evenements_id != other.evenements_id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
