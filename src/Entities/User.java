/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
    
import java.util.Objects;

/**
 *
 * @author Souhaiel
 */
public class User {
    private int id ;
    private String username ;
    private String mail ;
    private String mdp ;
    private String nom;
    private String prenom;
    private String address;
    private String ville;
    private String date_naissance;
    private String carte_fid_id;

    public User(){}

    public User(int id, String username, String mail, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.date_naissance = date_naissance;
        this.ville = ville;
        this.carte_fid_id = carte_fid_id;
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville ;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.username);
        hash = 43 * hash + Objects.hashCode(this.mail);
        hash = 43 * hash + Objects.hashCode(this.mdp);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        return true;
    }

    public String getCarte_fid_id() {
        return carte_fid_id;
    }

    public void setCarte_fid_id(String carte_fid_id) {
        this.carte_fid_id = carte_fid_id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", mail=" + mail + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom + ", address=" + address + ", ville=" + ville + ", date_naissance=" + date_naissance + ", carte_fid_id=" + carte_fid_id + '}';
    }
    
    


    
}
