/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Commande;
import Entities.Coupon;
import Entities.Panier;
import Entities.PanierDetail;
import Entities.Produit;
import static Interfaces.BikeOpsI.songs;
import Services.ServiceCommande;
import Services.ServiceCoupon;
import Services.ServicePanier;
import Services.ServiceProduit;
import Utils.Mail;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author laoui
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<PanierDetail> table;
    @FXML
    private TableColumn<PanierDetail, Integer> quantiteP;
    ServicePanier p = new ServicePanier();
    ObservableList<PanierDetail> datalist;
    @FXML
    private TableColumn<PanierDetail, Integer> PrixU;
    @FXML
    private TextField coupon;
    @FXML
    private TextField total;
    @FXML
    private TableColumn<PanierDetail, Integer> soustotal;
    @FXML
    private TableColumn<PanierDetail, String> nomP;
    @FXML
    private TextField totalreduced;
    @FXML
    private RadioButton btnCateId;
    @FXML
    private RadioButton btnlivId;
    @FXML
    private RadioButton btnMagId;
    @FXML
    private RadioButton btnDomId;

    final ToggleGroup groupPaiement = new ToggleGroup();
    final ToggleGroup groupLivraison = new ToggleGroup();

    private ToggleButton Musicbtn;
    @FXML
    private Button btnMusicOff;
    @FXML
    private Button btnMusicOn;
    @FXML
    private Button checkoutBtn;
    @FXML
    private Button appltbtn;
    @FXML
    private Button deletebtn;

    PanierDetail sc1 = new PanierDetail();
    @FXML
    private Button returnbtn;

    @FXML
    private TableColumn<PanierDetail, Integer> id_panierDetail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnCateId.setUserData("par_carte");
        btnCateId.setToggleGroup(groupPaiement);
        btnlivId.setUserData("à_la_livraison");
        btnlivId.setToggleGroup(groupPaiement);
        
        btnMagId.setUserData("retrait_magasin");
        btnMagId.setToggleGroup(groupLivraison);
        btnDomId.setUserData("par_livraison");
        btnDomId.setToggleGroup(groupLivraison);
        
        

        // static user
        int user_id = 1;
        int sum = 0;
        ArrayList<PanierDetail> paniers = (ArrayList<PanierDetail>) p.displayAll(user_id);
        paniers.forEach(e -> {
            e.setTotal(e.getPrix() * e.getQuantite());
        });

        for (int i = 0; i < paniers.size(); i++) {
            sum += (paniers.get(i).getTotal());
        }
           System.out.println("");
        total.setText(String.valueOf(sum));
        datalist = FXCollections.observableArrayList(paniers);
        id_panierDetail.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        PrixU.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        soustotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        // soustotal.setCellValueFactory();
        // TableColumn<PanierDetail, Integer> setCellValueFactory = prixT.setCellValueFactory(new PropertyValueFactory<>(p.panierTotal(table.getItems(prixU),table.getItems(quantiteP))));
        // addButtonToTable();
        table.setItems(datalist);

        table.setOnMouseClicked(event -> {
            //pour modifier un produit il faut faire deux click
            if (event.getClickCount() == 1) {
                int idP;

                sc1 = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                                idP=sc1.getId();
                                System.out.println(idP);

                deletebtn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        
                        alert.setTitle("Confirmation de suppression");
                        alert.setContentText(" Voulez-vous supprimer ce Produit?");
                        alert.setHeaderText(null);

                        Optional<ButtonType> resultat = alert.showAndWait();
                        if (resultat.get() == ButtonType.OK) {
                            p.supprimerPanierDetail(idP);
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
    private void applyCoupon(ActionEvent event) {

        ServiceCoupon serviceCoupon = new ServiceCoupon();
        ServicePanier servicePanier = new ServicePanier();
        Coupon theCoupon = serviceCoupon.getCouponByCode(coupon.getText());
        // we acctually have a coupon back from the service
        if (theCoupon.getCode() != null) {
            int reduction = (int) ((Integer.parseInt(total.getText())) * (theCoupon.getPourcentage() / 100));
            int totalReduced = Integer.parseInt(total.getText()) - reduction;
            totalreduced.setText(String.valueOf(totalReduced));
            //updtae the total of the cart
            // for now we use USER with id = 1, later on change it to connected user.
            Panier panier = servicePanier.getPanierByUser(1);
            servicePanier.modifierPanier(panier.getId(), totalReduced);
        } else {
            // display error 
            coupon.setText("Code Expiré");
        }

    }

    public void ref() {    //focntion refrechi tabbkle mte3 affichage a chaque modif
        table.getItems().clear();
        table.getItems().addAll(p.displayAll(1));
    }

    @FXML
    private void ToPreCommande(ActionEvent event) throws IOException, MessagingException {
         ServicePanier servicePanier = new ServicePanier();
                  
         String paiement_type = "";
         String livraison_type ="";
         
         if (groupPaiement.getSelectedToggle() != null) {
            paiement_type = groupPaiement.getSelectedToggle().getUserData().toString();
             // Do something here with the userData of newly selected radioButton
         }

         if (groupLivraison.getSelectedToggle() != null) {
            paiement_type = groupLivraison.getSelectedToggle().getUserData().toString();
             // Do something here with the userData of newly selected radioButton
         }         
        String etat = "en cours";
        Date date = new Date(2020,04,15);
        int user_id = 1;
        Panier panier = servicePanier.getPanierByUser(user_id);
        int panier_id = panier.getId();
        Commande c = new Commande (date, etat, paiement_type, livraison_type, panier_id, user_id);
        ServiceCommande serviceCommande = new ServiceCommande();
        serviceCommande.ajouterCommande(c);

        servicePanier.resetPanierPrice(panier_id);
        
         ArrayList<PanierDetail> lineItems = (ArrayList<PanierDetail>) p.displayAll(user_id);
        lineItems.forEach(e -> {
           servicePanier.deleteLineItems(e.getId());
        });
                Mail.sendMail("laouiniwassel93@gmail.com");
        
        Parent list = FXMLLoader.load(getClass().getResource("Commandefinal.fxml"));
        Scene listE = new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();



    }

    @FXML
    private void stopMusic(ActionEvent event) {
        try {
            songs("stop");
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void startMusic(ActionEvent event) {
        try {
            songs("go");
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void ajouterProduitPanier(int id_produit, int user_id) throws SQLException {
//        // check if user has a cart
//        ServiceProduit serviceProduit = new ServiceProduit();
//        ServicePanier servicePanier = new ServicePanier();
//
//        if (servicePanier.getPanierByUser(user_id) == null) {
//
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
//
//            // if user already has a cart
//        } else {
//            Panier p = servicePanier.getPanierByUser(user_id);
//            Produit px = serviceProduit.getProduitbyId(id_produit);
//            p.setTotal(p.getTotal() + px.getPrix());
//
//            servicePanier.updatePanierPrice(p);
//
//            PanierDetail panierD = new PanierDetail();
//            panierD.setPanier_id(p.getId());
//            panierD.setQuantite(1);
//            panierD.setNomProduit(px.getName());
//            panierD.setProduit_id(p.getId());
//            panierD.setPrix(px.getPrix());
//            panierD.setTotal(px.getPrix());
//            servicePanier.ajouterPanierDetail(panierD);
//        }
//    }

    @FXML
    private void delete(ActionEvent event) {

    }
    
    
//    //add jar : itextpdf-5.5.1.jar
//    @FXML  //JFXButton → fx:id : CreatePdf  // On Action : CreatePdf
//    private void CreatePdf(ActionEvent event) throws Exception {
//        try {
//        Document document = new Document() {};
//       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Desktop\\ReclamationClientPDF.pdf"));
//       document.open();
//       PdfPTable table=new PdfPTable(2);
//       table.setWidthPercentage(100);
//       table.getDefaultCell().setBorder(2);
//       table.addCell("objetreclamation");
//       table.addCell("description");
//       
//        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
//            p.add("Reclamation List ");
//            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
//            document.add(p);
//                  // document.add(com.itextpdf.text.Image.getInstance("C:/Users/pablo/Documents/NetBeansProjects/Pi_dev/src/Images/bank.png"));
//
//      Statement st=con4.createStatement();
//       ResultSet rs=st.executeQuery("SELECT objetreclamation,description FROM `reclamation`");
//       while(rs.next()){
//       table.addCell(rs.getString("objetreclamation"));
//       table.addCell(rs.getString("description")); //name in DB
//
//       
//       }
//       document.add(table);
//        JOptionPane.showMessageDialog(
//                null, " données exportées en pdf avec succés ");
//               document.close();
//           
//            
//
//        } catch (Exception e) {
//
//            System.out.println("Error PDF");
//            System.out.println(e.getStackTrace());
//            System.out.println(e.getMessage());
//        }
//   
//    }

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
