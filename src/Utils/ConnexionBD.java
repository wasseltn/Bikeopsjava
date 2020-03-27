/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laoui
 */
public class ConnexionBD {

    String url = "jdbc:mysql://localhost:3306/bikeops";
    String login = "root";
    String mdp = "";
     

     private static Connection cnx;
    
    private static ConnexionBD mycon;
    public Connection getcnx(){return cnx;}
     
    private ConnexionBD ()
    {
        try {
        cnx=DriverManager.getConnection(url,login,mdp);
        System.out.println("Connection etablie");
    }
        catch(SQLException ex)
    {
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null,ex);
        System.out.println("erreeuuuur");
    }
    }
    public static ConnexionBD getinstance()
    {
        if (mycon==null)
        {
            mycon=new ConnexionBD();
        }
        return mycon;
    }

}
