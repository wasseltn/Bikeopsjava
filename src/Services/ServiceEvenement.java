/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
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
public class ServiceEvenement {
    Connection conx;
    public ServiceEvenement()
    {
        conx = ConnexionBD.getinstance().getcnx();
    }
    public void addClass(Evenement c) {
        try {
            String requete = "insert into Evenement (id,formateur_id,lieu,dateDebut,datefin,nbplace,prix,description,name) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getFormateur_id());
            pst.setString(3, c.getLieu());
            pst.setDate(4, new java.sql.Date(c.getDateDeb().getDate()));
            pst.setDate(5, new java.sql.Date(c.getDateFin().getDate()));
            pst.setInt(6, c.getNbPlace());
            pst.setFloat(7, c.getPrix());
            pst.setString(8, c.getDescription());
            pst.setString(9, c.getName());
            pst.executeUpdate();
            System.out.println("Evenement added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Evenement> ListLivreur() {
        List<Evenement> Mylist = new ArrayList<>();
        try {
            String requete = "select * from Evenement";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId(rs.getInt("id"));
                p.setFormateur_id(rs.getInt("formateur_id"));
                p.setLieu(rs.getString("lieu"));
                p.setDateDeb(rs.getDate("datedebut"));
                p.setDateFin(rs.getDate("datefin"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setName(rs.getString("name"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    public void UpdateClasse(Evenement c) {
        try {
            String requete = "update Evenement set (id,formateur_id,lieu,dateDebut,datefin,nbplace,prix,description,name) values(?,?,?,?,?,?,?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getFormateur_id());
            pst.setString(3, c.getLieu());
            pst.setDate(4, new java.sql.Date(c.getDateDeb().getDate()));
            pst.setDate(5, new java.sql.Date(c.getDateFin().getDate()));
            pst.setInt(6, c.getNbPlace());
            pst.setFloat(7, c.getPrix());
            pst.setString(8, c.getDescription());
            pst.setString(9, c.getName());
            pst.executeUpdate();
            System.out.println("Event Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void DeleteClasse(Evenement cl) {
        try {
            String requete = "delete from evenement where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Evenement Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
