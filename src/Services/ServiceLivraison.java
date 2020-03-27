/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author benfr
 */
public class ServiceLivraison {
    Connection conx;
    public ServiceLivraison()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Livraison c) {
        try {
            String requete = "insert into livreur (id,etat,adress,livreurid,ville,commande_id,type) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setBoolean(2, c.getEtat());
            pst.setString(3, c.getAdress());
            pst.setInt(4, c.getLivreurid());
            pst.setString(5, c.getVille());
            pst.setInt(6, c.getCommande_id());
            pst.setString(7, c.getType());
            pst.executeUpdate();
            System.out.println("Livraison added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Livraison> ListLivreur() {
        List<Livraison> Mylist = new ArrayList<>();
        try {
            String requete = "select * from livraison";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Livraison p = new Livraison();
                p.setId(rs.getInt("id"));
                p.setEtat(rs.getBoolean("etat"));
                p.setAdress(rs.getString("adress"));
                p.setLivreurid(rs.getInt("livreurid"));
                p.setVille(rs.getString("ville"));
                p.setCommande_id(rs.getInt("commande_id"));
                p.setType(rs.getString("type"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    public void UpdateClasse(Livraison c) {
        try {
            String requete = "update Livarison set (id,etat,adress,livreurid,ville,commande_id,type) values(?,?,?,?,?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
             pst.setInt(1, c.getId());
            pst.setBoolean(2, c.getEtat());
            pst.setString(3, c.getAdress());
            pst.setInt(4, c.getLivreurid());
            pst.setString(5, c.getVille());
            pst.setInt(6, c.getCommande_id());
            pst.setString(7, c.getType());
            pst.executeUpdate();
            System.out.println("Livraison Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void DeleteClasse(Livraison cl) {
        try {
            String requete = "delete from Livraison where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("livraiosn Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
