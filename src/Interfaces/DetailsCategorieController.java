/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class DetailsCategorieController implements Initializable {

    @FXML
    private TextField tfiddc;
    @FXML
    private TextField tfnamedc;
    @FXML
    private TextField tfdescdc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setTextID(String message) {
        this.tfiddc.setText(message);
    }
    
    public void setTextName(String message) {
        this.tfnamedc.setText(message);
    }
    public void setTextDesc(String message) {
        this.tfdescdc.setText(message);
    }
}
