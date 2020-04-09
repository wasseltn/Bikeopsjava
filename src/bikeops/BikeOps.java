/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;



import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;
import Services.ServiceLivraison;
import Services.ServiceLivreur;
import Services.ServiceProduit;

import Services.ServicePanier;
import Services.ServiceLivreur;
import Services.ServiceLivraison;

import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class BikeOps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConnexionBD conx = ConnexionBD.getinstance();
        Categorie c = new Categorie(1, "souhaiel", "souhaiel");
        ServiceCategorie sc = new ServiceCategorie();
        sc.addCategorie(c);
        

    }
}
