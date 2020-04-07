/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
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
public class ServiceReclamation {
    Connection conx;
    public ServiceReclamation()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void add(Reclamation c) {
        try {
            String requete = "insert into reclamation (id,utilisateur_id,commentaire,sujet,etat) values(?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getUser_id());
            pst.setString(3, c.getCommentaire());
            pst.setString(4, c.getSujet());
            pst.setBoolean(5, c.getEtat());
            pst.executeUpdate();
            System.out.println("Reclamation added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Reclamation> ListLivreur() {
        List<Reclamation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from reclamation";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId(rs.getInt("id"));
                p.setEtat(rs.getBoolean("etat"));
                p.setCommentaire(rs.getString("commentaire"));
                p.setSujet(rs.getString("sujet"));
                p.setUser_id(rs.getInt("utilisateur_id"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    public void UpdateClasse(Reclamation c) {
        try {
            String requete = "update reclamation set (id,utilisateur_id,commentaire,sujet,etat) values(?,?,?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
             pst.setInt(1, c.getId());
            pst.setInt(2, c.getUser_id());
            pst.setString(3, c.getCommentaire());
            pst.setString(4, c.getSujet());
            pst.setBoolean(5, c.getEtat());
            pst.executeUpdate();
            System.out.println("Reclamation Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void DeleteClasse(Reclamation cl) {
        try {
            String requete = "delete from Reclamation where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Reclamation Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
