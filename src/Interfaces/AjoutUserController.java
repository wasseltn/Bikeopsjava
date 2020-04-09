/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import Services.ServiceUser;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class AjoutUserController implements Initializable {

    @FXML
    private TextField tfidu;
    @FXML
    private TextField tfusernameu;
    @FXML
    private TextField tfmailu;
    @FXML
    private TextField tfmdpu;
    @FXML
    private Button btnu;
    @FXML
    private Button btnret;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveruser(ActionEvent event) {
        try {
            int idu = Integer.parseInt(tfidu.getText());
            String username = tfusernameu.getText();

            String mail = tfmailu.getText();
            String mdp = tfmdpu.getText();
            if (tfidu.getText().length() == 0 || tfusernameu.getText().length() == 0 || tfmailu.getText().length() == 0 || tfmdpu.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("veuillez remplir le formulaire!!");
                alert.setHeaderText("Attention !");
                alert.setContentText("Certains champs sont vides !!");
                alert.showAndWait();
            }

            User u = new User(idu, username, mail, mdp);
            ServiceUser su = new ServiceUser();
            su.addUser(u);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsUser.fxml"));
            Parent root = loader.load();
            DetailsUserController du = loader.getController();
            du.setTextID("" + u.getId());
            du.setTextUsername(u.getUsername());
            du.setTextMail(u.getMail());
            du.setTextMdp(u.getMdp());

            tfidu.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = (Stage) btnret.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
