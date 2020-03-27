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
public class Panier {
    
    private int id;
    private int lineitems;
    private int total;

    public Panier(int id, int lineitems, int total) {
        this.id = id;
        this.lineitems = lineitems;
        this.total = total;
    }

    public Panier(int id, int lineitems) {
       this.id= id;
       this.lineitems= lineitems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineitems() {
        return lineitems;
    }

    public void setLineitems(int lineitems) {
        this.lineitems = lineitems;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", lineitems=" + lineitems + ", total=" + total + '}';
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
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.lineitems != other.lineitems) {
            return false;
        }
        if (this.total != other.total) {
            return false;
        }
        return true;
    }
    
    
    
}
