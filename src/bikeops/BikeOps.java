/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;

import Entities.Livraison;
import Entities.Livreur;
import Entities.Produit;
import Services.ServiceLivraison;
import Services.ServiceLivreur;
import Services.ServiceProduit;
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
        Produit p = new Produit(1, 100, 100, "souhaiel"  , "souhaiel");
        ServiceProduit L = new ServiceProduit();
        L.supprimerProduit(2);
        

    }
}
