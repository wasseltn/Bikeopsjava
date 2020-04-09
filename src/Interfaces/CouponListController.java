/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import Entities.Coupon;
import Services.ServiceCoupon;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class CouponListController implements Initializable {

    @FXML
    private Button btnRe;
    @FXML
    private Button btnGenerate;
    @FXML
    private TableColumn<Coupon, String> Code;
    @FXML
    private TableColumn<Coupon, Integer> Pourcentage;
    @FXML
    private TableView<Coupon> tablee;

    ObservableList<Coupon> datalist;
    @FXML
    private Button deletebtn;
    Coupon sc1 = new Coupon();
    ServiceCoupon sc2 = new ServiceCoupon();

    String code;

    /**
     * Initializes the controller class.
     *
     *
     */

    public void initialize(URL url, ResourceBundle rb) {

        ServiceCoupon sc = new ServiceCoupon();

        // static user
        int user_id = 1;
        int sum = 0;
        ArrayList<Coupon> coupons = (ArrayList<Coupon>) sc.ListCoupon();

        datalist = FXCollections.observableArrayList(coupons);
        Code.setCellValueFactory(new PropertyValueFactory<>("code"));
        Pourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));

        tablee.setItems(datalist);
        
        tablee.setOnMouseClicked(event -> {
            //pour modifier un produit il faut faire deux click
            if (event.getClickCount() == 1) {
                sc1 = tablee.getItems().get(tablee.getSelectionModel().getSelectedIndex());
                deletebtn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation de suppression");
                        alert.setContentText(" Voulez-vous supprimer ce Coupon");
                        alert.setHeaderText(null);

                        Optional<ButtonType> resultat = alert.showAndWait();
                        if (resultat.get() == ButtonType.OK) {
                            sc.DeleteClasse(sc1);
                            ref();
                        } else {
                            System.out.println("cancel");
                        }
                    }
                });

            }

        });

    }

    @FXML
    private void toMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage stage = (Stage) btnRe.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CouponListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ref() {    //focntion refrechi tabbkle mte3 affichage a chaque modif
        tablee.getItems().clear();
        tablee.getItems().addAll(sc2.ListCoupon());
    }

    @FXML
    private void GenerateCoupon(ActionEvent event) {

        ServiceCoupon sc = new ServiceCoupon();
        sc.generateCoupons();
        ref();        

    }
}
