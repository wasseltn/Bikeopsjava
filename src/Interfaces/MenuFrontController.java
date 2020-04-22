/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class MenuFrontController implements Initializable {

    @FXML
    private Button UserAffichebtn;
    @FXML
    private Button panierbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UserAffiche(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("UserAffiche.fxml"));
            Stage stage = (Stage) UserAffichebtn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CouponListController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @FXML
    private void panier(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
            Stage stage = (Stage) panierbtn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CouponListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
