/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Commande;
import Entities.Produit;
import Services.ServiceCommande;
import com.gluonhq.charm.glisten.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class ListeCommandeController implements Initializable {

    @FXML
    private TableColumn<Commande, Integer> idCommande;
    @FXML
    private TableColumn<Commande, String> nomClient;
    @FXML
    private TableColumn<Commande, String> status;
    @FXML
    private TableColumn<Commande, String> date;
    @FXML
    private TableColumn<Commande, String> typePaiment;
   
    @FXML
    private Button btnreturn;
    @FXML
    private Button confirmbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private TableView<Commande> tablee;
    ObservableList<Commande> datalist;
//    Commande c1 = new Commande();
//    ServiceCommande c = new ServiceCommande();
//    int id;
    @FXML
    private TextField tfrech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCommande c = new ServiceCommande();
         ArrayList<Commande> commandes = null;
        try {
            commandes = (ArrayList<Commande>) c.afficherCommandeList();
        } catch (SQLException ex) {
            Logger.getLogger(ListeCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        commandes.forEach(a -> System.out.println(a.toString()));

        datalist = FXCollections.observableArrayList(commandes);
        idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        status.setCellValueFactory(new PropertyValueFactory<>("etat"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        typePaiment.setCellValueFactory(new PropertyValueFactory<>("typePaiment"));
      
        tablee.setItems(datalist);
        
       
        FilteredList<Commande> filteredData = new FilteredList<>(datalist, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Commande -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Commande.getTypePaiment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Commande.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Commande> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablee.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablee.setItems(sortedData);

     
//
//        tablee.setOnMouseClicked(event -> {
//            //pour modifier un produit il faut faire deux click
//            if (event.getClickCount() == 1) {
//                c1 = tablee.getItems().get(tablee.getSelectionModel().getSelectedIndex());
//                deletebtn.setOnAction(new EventHandler<ActionEvent>() {
//
//                    @Override
//                    public void handle(ActionEvent event) {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("Confirmation de suppression");
//                        alert.setContentText(" Voulez-vous supprimer cette commande");
//                        alert.setHeaderText(null);
//
//                        Optional<ButtonType> resultat = alert.showAndWait();
//                        if (resultat.get() == ButtonType.OK) {
//                            c.supprimerCommande(id);
//                            try {
//                                ref();
//                            } catch (SQLException ex) {
//                                Logger.getLogger(ListeCommandeController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        } else {
//                            System.out.println("cancel");
//                        }
//                    }
//                });
//            }
//
//        });
//
//    }
//    
//    public void ref() throws SQLException {    //focntion refrechi tabbkle mte3 affichage a chaque modif
//        tablee.getItems().clear();
//        tablee.getItems().addAll(c.afficherCommandeList());
    }
//    
    @FXML
    private void backtomenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = (Stage) btnreturn.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void confirme(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {

    }
}

