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
    public void addCategorie(Categorie c) {
        try {
            String requete = "insert into produit (id,name,description) values(?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getName());
            pst.setString(3, c.getDesc());
            pst.executeUpdate();
            System.out.println("Categorie ajouté !!!!");
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
                c.setName(rs.getString("name"));
                c.setId(rs.getInt("id"));
                c.setDesc(rs.getString("desc"));
                Mylist.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    
    public void UpdateCategorie(Categorie c) {
        try {
            String requete = "update categorie set (id,name,description) values(?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(3, c.getDesc());
            pst.setString(2, c.getName());
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
