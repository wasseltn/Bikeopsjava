/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Panier;
import Entities.PanierDetail;
import Entities.Produit;
import Services.ServicePanier;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class UserAfficheController implements Initializable {

    @FXML
    private TableView<Produit> tablep;
    @FXML
    private TableColumn<Produit, Integer> idp;
    @FXML
    private TableColumn<Produit, Integer> qtep;
    @FXML
    private TableColumn<Produit, Integer> prixp;
    @FXML
    private TableColumn<Produit, String> nomp;
    @FXML
    private TableColumn<Produit, String> descp;

    private final ObservableList<Produit> datta = FXCollections.observableArrayList();
    public static String idprecup;
    public static String qteprecup;
    public static Float prixprecup;
    public static String nomprecup;
    public static String descprecup;
    @FXML
    private TextField rech;
    @FXML
    private Button cartbtn;
    @FXML
    private Button returnbtn;

    private void settable() {

        ServiceProduit sp = new ServiceProduit();

        ArrayList<Produit> u = (ArrayList<Produit>) sp.ListProduit();
        ObservableList<Produit> obs = FXCollections.observableArrayList(u);

        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomp.setCellValueFactory(new PropertyValueFactory<Produit, String>("name"));
        prixp.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("Prix"));
        qtep.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte"));
        descp.setCellValueFactory(new PropertyValueFactory<Produit, String>("desc"));

        datta.addAll(u);
        tablep.setItems(obs);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settable();
        FilteredList<Produit> filteredData = new FilteredList<>(datta, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(Produit.getId()).equals(lowerCaseFilter)) {
                    return true;
                } else if (Produit.getDesc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Produit.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablep.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablep.setItems(sortedData);
    }

    @FXML
    private void addtocart(ActionEvent event) throws SQLException {


         Produit produitSelected = tablep.getItems().get(tablep.getSelectionModel().getSelectedIndex());
        int id_produit = produitSelected.getId();
        System.out.println("adding to cart : " + id_produit);
        // check if user has a cart
        ServiceProduit serviceProduit = new ServiceProduit();
        ServicePanier servicePanier = new ServicePanier();
        int user_id = 1;
        if (servicePanier.getPanierByUser(user_id) == null) {
//               System.out.println(" new ");
//            Panier p = new Panier();
//            Produit p1 = serviceProduit.getProduitbyId(id_produit);
//            p.setUser_id(user_id);
//            p.setTotal(p1.getPrix());
//            int newPanierid = servicePanier.ajouterPanier(p);
//
//            PanierDetail panierD = new PanierDetail();
//            panierD.setPanier_id(newPanierid);
//            panierD.setQuantite(1);
//            panierD.setNomProduit(p1.getName());
//            panierD.setProduit_id(p.getId());
//            panierD.setPrix(p1.getPrix());
//            panierD.setTotal(p1.getPrix());
//            servicePanier.ajouterPanierDetail(panierD);

            // if user already has a cart
        } else {
            System.out.println(" alrady has ");
            Panier p = servicePanier.getPanierByUser(user_id);
            Produit px = serviceProduit.getProduitbyId(id_produit);
            System.out.println("produit " + px.getPrix() + "  " + px.getId());
            p.setTotal(p.getTotal() + px.getPrix());
            
System.out.println("before totla panier " + p.getTotal());
            servicePanier.updatePanierPrice(p);
System.out.println("after panier " + p.getTotal());
            PanierDetail panierD = new PanierDetail();
            panierD.setPanier_id(p.getId());
            panierD.setQuantite(1);
            panierD.setNomProduit(px.getName());
            panierD.setProduit_id(px.getId());
            panierD.setPrix(px.getPrix());
            panierD.setTotal(px.getPrix());
            System.out.println("once");
            servicePanier.ajouterPanierDetail(panierD);
        }

    }

    @FXML
    private void returnn(ActionEvent event) {
        
         try {
            Parent root = FXMLLoader.load(getClass().getResource("MenuFront.fxml"));
            Stage stage = (Stage) returnbtn.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CouponListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
