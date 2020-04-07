/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author laoui
 */
public class User {
    
    int id;
    String nom;
    String prenom;
    String address;
    String date_naissance;
    String ville;
    String email;
    String username;
    String password;
    String carte_fid_id;
    

    public User(int id, String nom, String prenom, String address, String date_naissance,String Password, String ville,String carte_fid_id,String email,String username) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.date_naissance = date_naissance;
        this.ville = ville;
        this.carte_fid_id = carte_fid_id;
        this.email = email;
        this.username = username;
        this.password = password;
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCarte_fid_id() {
        return carte_fid_id;
    }

    public void setCarte_fid_id(String carte_fid_id) {
        this.carte_fid_id = carte_fid_id;
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
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", address=" + address + ", date_naissance=" + date_naissance + ", ville=" + ville + ", email=" + email + ", username=" + username + ", password=" + password + ", carte_fid_id=" + carte_fid_id + '}';
    }

    
}
