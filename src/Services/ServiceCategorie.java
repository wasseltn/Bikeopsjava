/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
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
 * @author Souhaiel
 */
public class ServiceCategorie {
    Connection conx;
    public ServiceCategorie()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Categorie c) {
        try {
            String requete = "insert into produit (id,type) values(?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getType());
            pst.executeUpdate();
            System.out.println("pCategorie ajouté !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Categorie> ListCategorie() {
        List<Categorie> Mylist = new ArrayList<>();
        try {
            String requete = "select * from categorie";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setType(rs.getString("type"));
                c.setId(rs.getInt("id"));
                Mylist.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    
    public void UpdateClasse(Categorie c) {
        try {
            String requete = "update categorie set (id,type) values(?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(2, c.getType());
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("categorie mis a jour !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void supprimerCategorie(int Id)
    {
        try {
            PreparedStatement pt =conx.prepareStatement("delete from Categorie where Id=?" );
            pt.setInt(1,Id);
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
