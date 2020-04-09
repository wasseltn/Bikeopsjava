/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author Souhaiel
 */
public class ServiceUser {

    Connection conx;

    public ServiceUser() {
        conx = ConnexionBD.getinstance().getcnx();
    }

    public void addUser(User u) {
        try {
            String requete = "insert into utilisateur (id,username,email,password) values(?,?,?,?)";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getUsername());
            pst.setString(3, u.getMail());
            pst.setString(4, u.getMdp());

            pst.executeUpdate();
            System.out.println("Utilisateur ajouté !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> ListUser() {
        List<User> Mylist = new ArrayList<>();
        try {
            String requete = "select * from produit";
            PreparedStatement pst = conx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setMail(rs.getString("mail"));
                u.setMdp(rs.getString("password"));

                Mylist.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateUser(User u) {
        try {
            String requete = "update utilisateur set (id,username,email,password) values(?,?,?,?) where ? = id";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1, u.getId());

            pst.setString(2, u.getUsername());
            pst.setString(3, u.getMail());
            pst.setString(4, u.getMdp());
            pst.executeUpdate();
            System.out.println("Utilisateur mis a jour !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerUser(int Id) {
        try {
            PreparedStatement pt = conx.prepareStatement("delete from utilisateur where Id=?");
            pt.setInt(1, Id);
            pt.execute();
        } catch (SQLException ex) {
            System.out.println("Utilisateur supprimé !!");
        }
    }
}
