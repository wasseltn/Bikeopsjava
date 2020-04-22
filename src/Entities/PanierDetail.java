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
public class PanierDetail {
    String nomProduit;  
    int id;
    int prix;        
    int quantite;        
    int total;
    int panier_id;
    int produit_id;
    
        public PanierDetail(String nomProduit,int prix, int quantite, int total) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
    }

    public PanierDetail(String nomProduit,int prix, int quantite, int total, int panier_id) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
        this.panier_id = panier_id;
    }
    
    public PanierDetail(int id, String nomProduit,int prix, int quantite, int total, int panier_id) {
        this.nomProduit = nomProduit;
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
        this.panier_id = panier_id;
        this.produit_id = produit_id;
    }    

    public PanierDetail() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }
    
    

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PanierDetail{" + "nomProduit=" + nomProduit + ", id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", total=" + total + ", panier_id=" + panier_id + ", produit_id=" + produit_id + '}';
    }

    
   
}
