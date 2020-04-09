/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;


import Entities.Commande;

import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;

import Services.ServiceLivreur;
import Services.ServiceLivraison;
import Entities.Livreur;
import Entities.Livraison;
import Services.ServiceProduit;

import Entities.Panier;
import Services.ServiceCommande;
import Services.ServiceCoupon;
import java.sql.Date;
import java.sql.SQLException;

import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class BikeOps {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws SQLException {
       ConnexionBD conx = ConnexionBD.getinstance();
        Produit p = new Produit(1, 100, 100, "souhaiel"  , "souhaiel");
        ServiceProduit L = new ServiceProduit();
        L.supprimerProduit(2);
       //Livreur l = new Livreur(0, "gh", "bf", true);
      //  ServiceLivreur L = new ServiceLivreur();
       // L.addClass(l);
       // Livraison li = new Livraison(66, Boolean.TRUE, "mourouj", 5, "BenArous", 0, "zzzz");
       // ServiceLivraison LI = new ServiceLivraison();
       // LI.addlivraison(li);
      //   ServiceCommande sc = new ServiceCommande();
       // sc.modifierCommande("en_attente", "2020-03-23","livraison",48);
//        Commande c2 = new Commande ("2020-06-7", "confirmée", "carte",7,11);
//        
//        sc.ajouterCommande(c2);
       // sc.supprimerCommande(21);
      // ServicePanier sp = new ServicePanier();
      
        Categorie c = new Categorie(1, "souhaiel", "souhaiel");
        ServiceCategorie sc = new ServiceCategorie();
        sc.addCategorie(c);
      
        ServiceCoupon scoupon = new ServiceCoupon();
        scoupon.generateCoupons();
     //  Panier p = new Panier( 950, 4);
     //  sp.ajouterPanier(p);
      
       //sp.displayAll();
       
}
}
