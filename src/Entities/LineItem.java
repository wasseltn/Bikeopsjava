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
public class LineItem {
    
    private int id;
    private int quantite;

    public LineItem(int id, int quantite) {
        this.id = id;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LineItem{" + "id=" + id + ", quantite=" + quantite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final LineItem other = (LineItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        return true;
    }
    
    
}
