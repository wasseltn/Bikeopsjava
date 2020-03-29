/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author benfr
 */
public class Livraison {
      private int id;
    private Boolean etat;
    private String adress;
    private int livreurid;
    private String ville;
    private int commande_id;
    private String type;

    public Livraison() {
    }

    public Livraison(int id, Boolean etat, String adress, int livreurid, String ville, int commande_id, String type) {
        this.id = id;
        this.etat = etat;
        this.adress = adress;
        this.livreurid = livreurid;
        this.ville = ville;
        this.commande_id = commande_id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", etat=" + etat + ", adress=" + adress + ", livreurid=" + livreurid + ", ville=" + ville + ", commande_id=" + commande_id + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
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
        final Livraison other = (Livraison) obj;
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getLivreurid() {
        return livreurid;
    }

    public void setLivreurid(int livreurid) {
        this.livreurid = livreurid;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
