/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Produit;
import Services.ServiceProduit;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfqte;
    @FXML
    private TextField tfprix;
    @FXML
    private Button btnvalider;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveproduit(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfid.getText());
            int qte = Integer.parseInt(tfqte.getText());
            float prix = Integer.parseInt(tfprix.getText());
            String name = tfname.getText();
            String desc = tfdesc.getText();
            if (tfid.getText().length() == 0 || tfqte.getText().length() == 0 || tfprix.getText().length() == 0 || tfname.getText().length() == 0 || tfdesc.getText().length() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("veuillez remplir le formulaire!!");
                alert.setHeaderText("Attention !");
                alert.setContentText("Certains champs sont vides !!");
                alert.showAndWait();
            }

            Produit p = new Produit(id, qte, id, name, desc);
            ServiceProduit sp = new ServiceProduit();
            sp.addProduit(p);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            Parent root = loader.load();
            DetailsController dw = loader.getController();
            dw.setTextID("" + p.getId());
            dw.setTextQte("" + p.getQte());
            dw.setTextPrix("" + p.getPrix());
            dw.setTextName(p.getName());
            dw.setTextDesc(p.getDesc());

            tfid.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

}
