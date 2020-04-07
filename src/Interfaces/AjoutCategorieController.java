/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private TextField tfidc;
    @FXML
    private TextField tfnamec;
    @FXML
    private TextField tfdescc;
    @FXML
    private Button btnc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savecategorie(ActionEvent event) {try {
            int id = Integer.parseInt(tfidc.getText());
      
            String name = tfnamec.getText();
            String desc = tfdescc.getText();
            if (tfidc.getText().length() == 0 ||  tfnamec.getText().length() == 0 || tfdescc.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("veuillez remplir le formulaire!!");
                alert.setHeaderText("Attention !");
                alert.setContentText("Certains champs sont vides !!");
                alert.showAndWait();
            }

            Categorie c = new Categorie(id, name, desc) ;
            ServiceCategorie sc = new ServiceCategorie();
            sc.addCategorie(c);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCategorie.fxml"));
            Parent root = loader.load();
            DetailsCategorieController dc = loader.getController();
            dc.setTextID("" + c.getId());
            dc.setTextName(c.getName());
            dc.setTextDesc(c.getDesc());

            tfidc.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
    }
    

