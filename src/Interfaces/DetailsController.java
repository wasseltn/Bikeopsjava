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
public class DetailsController implements Initializable {

    @FXML
    private TextField tfid2;
    @FXML
    private TextField tfqte2;
    @FXML
    private TextField tfprix2;
    @FXML
    private TextField tfname2;
    @FXML
    private TextField tfdesc2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextID(String message) {
        this.tfid2.setText(message);
    }

    public void setTextQte(String message) {
        this.tfqte2.setText(message);
    }

    public void setTextPrix(String message) {
        this.tfprix2.setText(message);
    }
    public void setTextName(String message) {
        this.tfname2.setText(message);
    }
    public void setTextDesc(String message) {
        this.tfdesc2.setText(message);
    }
}
