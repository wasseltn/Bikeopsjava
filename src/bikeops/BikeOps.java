/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bikeops;

import Entities.Livraison;
import Entities.Livreur;
import Services.ServiceLivraison;
import Services.ServiceLivreur;
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
        Livreur l = new Livreur(0, "gh", "bf", true);
        ServiceLivreur L = new ServiceLivreur();
        L.addClass(l);
        Livraison li = new Livraison(66, Boolean.TRUE, "mourouj", 5, "BenArous", 0, "zzzz");
        ServiceLivraison LI = new ServiceLivraison();
        LI.addlivraison(li);

    }
}
