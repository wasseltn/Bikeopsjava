package bikeops;

/* ENTITIES HERE */
import Entities.Commande;
import Entities.Categorie;
import Entities.Produit;
import Entities.Livreur;
import Entities.Livraison;
import Entities.Panier;

/* SERVICES HERE */
import Services.ServiceCategorie;
import Services.ServiceLivreur;
import Services.ServiceLivraison;
import Services.ServiceProduit;
import Entities.User;
import Services.ServiceCommande;
import Services.ServiceCoupon;
import java.sql.SQLException;
import java.sql.Date;
import Services.ServicePanier;
/* Other Java shit here */

import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import Services.ServiceUser;
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
        Calendar c = Calendar.getInstance();
        Date dd = new Date();
        c.set(2019, 3, 16);
        dd = c.getTime();
        User u = new User(666, "souhaiel", "souhaiel", "souhaiel", "souhaiel", "souhaiel", "souhaiel",dd);
        ServiceUser su = new ServiceUser();
        su.UpdateUser(u);
       ServicePanier sp = new ServicePanier();
        Categorie c = new Categorie(1, "souhaiel", "souhaiel");
        ServiceCategorie sc = new ServiceCategorie();
        sc.addCategorie(c);
       Panier p = new Panier( 950, 1);
       sp.ajouterPanier(p);
}
