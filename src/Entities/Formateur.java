/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author kinga
 */
public class Formateur {
    private int id;
    private int event;
    private String nom;
    private String prenom;
    private String spec;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        return hash;
    }

    public Formateur() {
    }

    public Formateur(int id, int event, String nom, String prenom, String spec) {
        this.id = id;
        this.event = event;
        this.nom = nom;
        this.prenom = prenom;
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "Formateur{" + "id=" + id + ", event=" + event + ", nom=" + nom + ", prenom=" + prenom + ", spec=" + spec + '}';
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
        final Formateur other = (Formateur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
    
    
}
