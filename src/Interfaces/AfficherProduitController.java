/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Produit;
import Services.ServiceProduit;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Souhaiel
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<Produit> tablep;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnret;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmod;
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
    public static Integer prixprecup;
    public static String nomprecup;
    public static String descprecup;

    private void settable() {

        ServiceProduit sp = new ServiceProduit();

        ArrayList<Produit> p = (ArrayList<Produit>) sp.ListProduit();
        ObservableList<Produit> obs = FXCollections.observableArrayList(p);

        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomp.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixp.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("Prix"));
        qtep.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("qte"));
        descp.setCellValueFactory(new PropertyValueFactory<Produit, String>("desc"));

        datta.addAll(p);
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
        tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
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

    @FXML
    private void supprimer(ActionEvent event) {
        ImageIcon icon = new ImageIcon("E −\\new.PNG");
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(250, 100));
        panel.setLayout(null);
        JLabel label1 = new JLabel("Delete Produit");
        label1.setVerticalAlignment(SwingConstants.BOTTOM);
        label1.setBounds(20, 20, 200, 30);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);
        JLabel label2 = new JLabel("Do you still want to Delete it?");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(20, 80, 200, 20);
        panel.add(label2);
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        int res = JOptionPane.showConfirmDialog(null, panel, "File",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        if (res == 0) {
            Produit p = tablep.getSelectionModel().getSelectedItem();

            ServiceProduit sp = new ServiceProduit();
            sp.supprimerProduit(p.getId());
            System.out.println("Produit deleted");
        //    Notifications.create().title("Supression").text("Produit supprimé").showConfirm();
        } else if (res == 1) {
            System.out.println("Pressed No");

        } else {
            System.out.println("Pressed CANCEL");
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        try {
            Produit p = tablep.getSelectionModel().getSelectedItem();

            AfficherProduitController.idprecup = "" + p.getId();
            AfficherProduitController.qteprecup = "" + p.getQte();
            AfficherProduitController.prixprecup = p.getPrix();
            AfficherProduitController.nomprecup = p.getName();
            AfficherProduitController.descprecup = p.getDesc();

            Parent root = FXMLLoader.load(getClass().getResource("DetailsProduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
