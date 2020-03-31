/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Formateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author kinga
 */
public class ServiceFormateur {
    Connection conx;
    public ServiceFormateur()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Formateur c) {
        try {
            String requete = "insert into formateur (id,evennement,nom,prenom,specialite) values(?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getEvent());
            pst.setString(3, c.getNom());
            pst.setString(4, c.getPrenom());
            pst.setString(5, c.getSpec());
            pst.executeUpdate();
            System.out.println("formateur added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Formateur> ListLivreur() {
        List<Formateur> Mylist = new ArrayList<>();
        try {
            String requete = "select * from Formateur";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Formateur p = new Formateur();
                p.setId(rs.getInt("id"));
                p.setEvent(rs.getInt("evennement"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setSpec(rs.getString("specialite"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    public void UpdateClasse(Formateur c) {
        try {
            String requete = "update Formateur set (id,evennement,nom,prenom,specialite) values(?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getEvent());
            pst.setString(3, c.getNom());
            pst.setString(4, c.getPrenom());
            pst.setString(5, c.getSpec());
            pst.executeUpdate();
            System.out.println("Formateur Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void DeleteClasse(Formateur cl) {
        try {
            String requete = "delete from Formateur where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Formateur Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
