/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
import Entities.PanierDetail;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author laoui
 */
public class ServicePanier {

    Connection conx = ConnexionBD.getinstance().getcnx();

    public int ajouterPanier(Panier sc) throws SQLException {
        int inserted_id = 0;
        String columnNames[] = new String[]{"id"};
        try {
            PreparedStatement pt = conx.prepareStatement(" insert into Panier ( total,utilisateur_id) values ( ?, ?)", columnNames);

            pt.setInt(1, sc.getTotal());
            pt.setInt(2, sc.getUser_id());
            pt.execute();

            if (pt.executeUpdate() > 0) {
                // Retrieves any auto-generated keys created as a result of executing this Statement object
                java.sql.ResultSet generatedKeys = pt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    inserted_id = generatedKeys.getInt(1);
                }
            }
            return inserted_id;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public void afficherPanier() {
        try {
            PreparedStatement pt = conx.prepareStatement("select * from Panier");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                System.out.println("Panier {Lineitems:" + rs.getInt(2) + " ,Total:" + rs.getInt(3) + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierPanier(int Id, int total) {
        try {
            PreparedStatement pt = conx.prepareStatement("update Pnaier set  total=?, where Id_Panier=?");

            pt.setInt(1, total);
            pt.setInt(2, Id);

            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerPanier(int Id) {
        try {
            PreparedStatement pt = conx.prepareStatement("delete from panier where Id=?");
            pt.setInt(1, Id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PanierDetail> displayAll(int user_id) {
        ArrayList<PanierDetail> tab = new ArrayList();

        try {
            PreparedStatement pt = conx.prepareStatement(" SELECT pc.id, p.name, p.prix, pc.quantite, pa.total, pa.id from produit p INNER join "
                    + "line_item pc INNER join panier pa "
                    + " where pc.produit_id = p.id and pc.panier_id = pa.id and ? = pa.utilisateur_id ");
            pt.setInt(1, user_id);
            ResultSet res = pt.executeQuery();
            while (res.next()) {
                PanierDetail s = new PanierDetail(res.getInt(1),res.getString(2), res.getInt(3), res.getInt(4), res.getInt(5),res.getInt(6));

                tab.add(s);
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        tab.forEach(e -> {
            System.out.println(e);
        });
        return tab;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int panierTotal(int prix, int quantite) {

        int s = prix * quantite;

        return s; 
    }

    public Panier getPanierByUser(int id) {
        Panier panier = new Panier();
        try {
            String requete = "select * from panier where ? = utilisateur_id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                panier.setId(rs.getInt("id"));
                panier.setTotal(rs.getInt("total"));
                panier.setUser_id(id);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (panier != null) {
            return panier;
        } else {
            return null;
        }
    }

    public void updatePanierPrice(Panier p) {
        try {
            PreparedStatement pt = conx.prepareStatement(" update panier set total = ? where id = ? ");

            pt.setInt(1, p.getTotal());
            pt.setInt(2, p.getId());

            pt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int ajouterPanierDetail(PanierDetail pd) throws SQLException {
        int inserted_id = 0;
        int a = 0;
        String columnNames[] = new String[]{"id"};
        try {
            PreparedStatement pt = conx.prepareStatement(" insert into line_item ( panier_id,produit_id, quantite) values ( ?, ?, ?)", columnNames);

            pt.setInt(1, pd.getPanier_id());
            pt.setInt(2, pd.getProduit_id());
            pt.setInt(3, pd.getQuantite());
            pt.execute();

            if (pt.executeUpdate() > 0) {
                a++;
                System.out.println( a );
                // Retrieves any auto-generated keys created as a result of executing this Statement object
                java.sql.ResultSet generatedKeys = pt.getGeneratedKeys();
                System.out.println( pt.getGeneratedKeys());
                if (generatedKeys.next()) {
                    inserted_id = generatedKeys.getInt(1);
                }
            }
            return inserted_id;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }
    
    public void supprimerPanierDetail ( int id) {
        System.out.println("Start to delete ");
                try {
            PreparedStatement pt = conx.prepareStatement("delete from line_item where id = ?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetPanierPrice (int id) {
              try {
            PreparedStatement pt = conx.prepareStatement(" update panier set total = 0 where id = ? ");
            pt.setInt(1, id);

            pt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }  
    }
    
    public void deleteLineItems( int id ) {
                try {
            PreparedStatement pt = conx.prepareStatement(" delete from line_item where id = ? ");

            pt.setInt(1, id);

            pt.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
