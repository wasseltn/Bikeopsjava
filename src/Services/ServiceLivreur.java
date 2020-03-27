/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Livreur;
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
public class ServiceLivreur {
    Connection conx;
    public ServiceLivreur()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Livreur c) {
        try {
            String requete = "insert into livreur (id,nom,prenom,etat) values(?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setBoolean(4, c.isEtat());
            pst.executeUpdate();
            System.out.println("livreur added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Livreur> ListLivreur() {
        List<Livreur> Mylist = new ArrayList<>();
        try {
            String requete = "select * from livreur";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Livreur p = new Livreur();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setId(rs.getInt("id"));
                p.setEtat(rs.getBoolean("etat"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    public void UpdateClasse(Livreur c) {
        try {
            String requete = "update livreur set (id,nom,prenom,etat) values(?,?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setInt(1, c.getId());
            pst.setBoolean(4, c.isEtat());
            pst.executeUpdate();
            System.out.println("Classe Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
