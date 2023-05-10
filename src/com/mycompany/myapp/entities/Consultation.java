/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mongi
 */
@Entity
@Table(name = "consultation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultation.findAll", query = "SELECT c FROM Consultation c")
    , @NamedQuery(name = "Consultation.findById", query = "SELECT c FROM Consultation c WHERE c.id = :id")
    , @NamedQuery(name = "Consultation.findByNom", query = "SELECT c FROM Consultation c WHERE c.nom = :nom")
    , @NamedQuery(name = "Consultation.findByPrenom", query = "SELECT c FROM Consultation c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Consultation.findByCause", query = "SELECT c FROM Consultation c WHERE c.cause = :cause")
    , @NamedQuery(name = "Consultation.findByDescription", query = "SELECT c FROM Consultation c WHERE c.description = :description")
    , @NamedQuery(name = "Consultation.findByDate", query = "SELECT c FROM Consultation c WHERE c.date = :date")
    , @NamedQuery(name = "Consultation.findByMedecin", query = "SELECT c FROM Consultation c WHERE c.medecin = :medecin")
    , @NamedQuery(name = "Consultation.findByCabinet", query = "SELECT c FROM Consultation c WHERE c.cabinet = :cabinet")})
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "cause")
    private String cause;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "date")
    private String date;
    @Size(max = 255)
    @Column(name = "medecin")
    private String medecin;
    @Size(max = 255)
    @Column(name = "cabinet")
    private String cabinet;

    public Consultation() {
    }

    public Consultation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.myapp.entities.Consultation[ id=" + id + " ]";
    }
    
}
