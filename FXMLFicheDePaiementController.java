/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLFicheDePaiementController implements Initializable {

    private static JFXTextField Nreserv;
    @FXML
    private DatePicker FdateA;
    @FXML
    private DatePicker FDateD;
    @FXML
    private JFXTextField Fnom;
    @FXML
    private TextField FnbNuit;
    @FXML
    private TextField FPrix;
    @FXML
    private TextField Fmodepaie;
    @FXML
    private TextField Fformule;
    @FXML
    private TextField FtypeChambre;
    @FXML
    private TextField FNchambre;
    @FXML
    private TextField Ftotal;
    @FXML
    private RadioButton Fconfirmation;
    @FXML
    private ChoiceBox<String> Fremise;
    @FXML
    private Button btnprint;
   // private Button btnfactEnreg;

    @FXML
    private Button btnfactAnnuler;

    BdConnection bd = new BdConnection();
    @FXML
    private JFXTextField Freserv;

    public static String nom;
    public static String tel;
    public static String adresse;
    public static String email;
    public static String dateA;
    public static String dateD;
    public static String nChambre;
    public static String type;
    public static String formule;
    public static String remise;
    public static String prix;
    public static String total;
    public static String nbnuit;
     public static String reservation;
     public static String idC;
      public static String modepaie;
      public static int remiseInt;
    @FXML
    private Text AlertTextprint;
    @FXML
    private Text AlertTextAuto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AlertTextprint.setOpacity(0);
        AlertTextAuto.setOpacity(0);
        Fremise.getItems().addAll("Aucune", "5%", "10%", "15%", "20%", "25%");
       // btnfactEnreg.setDisable(true);
        btnprint.setDisable(true);
        FDateD.setDisable(true);
        FNchambre.setEditable(false);
        FPrix.setEditable(false);
        FdateA.setDisable(true);
        Fformule.setEditable(false);
        Fmodepaie.setEditable(false);
        FnbNuit.setEditable(false);
        Fnom.setEditable(false);
        Ftotal.setEditable(false);
        FtypeChambre.setEditable(false);
    }

    @FXML
    void FactureAuto(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        System.out.println("merde combi !!!");
        ResultSet rs;
        ResultSet r;
        ResultSet r1;
        ResultSet r2;
        r2=con.createStatement().executeQuery("SELECT idReservation FROM reservation WHERE etat='0';");
        int k=0;
        while(r2.next()){
            if(Freserv.getText().equals(r2.getString("idReservation"))){
                k++;
            }
        }
        if(k!=0){
            AlertTextAuto.setOpacity(0);
        String id = "";
        String idC = "";
        
        this.reservation=Freserv.getText();
        try {

            rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idReservation='" + Freserv.getText() + "';");
            while (rs.next()) {
                FNchambre.setText(rs.getString(3));
                FdateA.setValue(LocalDate.parse(rs.getString(5)));
                //FDateD.setValue(LocalDate.parse(rs.getString(6)));
                LocalDate date = LocalDate.now();
                FDateD.setValue(date);
                Fmodepaie.setText(rs.getString(8));
                Fformule.setText(rs.getString(9));
                 long l=Math.abs( FDateD.getValue().toEpochDay()-FdateA.getValue().toEpochDay());
                 FnbNuit.setText(""+l);
                //FnbNuit.setText(rs.getString(10));
                id = rs.getString(4);
                
                this.idC = id;
                this.nChambre=rs.getString(3);
                this.dateA=rs.getString(5);
                this.dateD=rs.getString(6);
                this.formule=rs.getString(9);
                this.nbnuit=rs.getString(10);
                this.modepaie=rs.getString(8);
                System.out.println(id);
            }
            r = con.createStatement().executeQuery("SELECT * FROM client WHERE idClient='" + id + "';");
            while (r.next()) {
                Fnom.setText(r.getString(2));
                this.nom=r.getString("nom");
                this.tel=r.getString(5);
                this.adresse=r.getString(3);
                this.email=r.getString(6);
            }
           
            r1 = con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre='" + nChambre + "';");
            while (r1.next()) {
                
                FPrix.setText(r1.getString(4));
                FtypeChambre.setText(r1.getString(5));
                Fremise.setValue(r1.getInt(3) + "%");
                this.remiseInt=r1.getInt(3);
                this.type=r1.getString(5);
                this.remise=Fremise.getValue();
                this.prix=r1.getString(4);
                long total = 0;
                System.out.println(r1.getInt(3));
                long l = Math.abs(FDateD.getValue().toEpochDay() - FdateA.getValue().toEpochDay());
                total = (long) (r1.getInt(4) * l * (1 - r1.getInt(3) * 1.0 / 100));
                Ftotal.setText("" + total);
                this.total=Ftotal.getText();
                System.out.println(FPrix.getText());
                System.out.println("Ekkkkkkieeh");
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(EnregistrementPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            AlertTextAuto.setOpacity(1);
        }
        /*try {
         ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idReservation='" + Nreserv.getText() + "';");
         while (rs.next()) {
         System.out.println(rs.getString(3));
         FNchambre.setText(rs.getString(3));
         }
         } catch (SQLException ex) {
         Logger.getLogger(FXMLFicheDePaiementController.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
         //System.exit(0);
         System.out.println("ERROR");
         }*/
        /*while (rs.next()) {
         FNchambre.setText(rs.getString(3));
         ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient='" + rs.getString(4) + "';");
         String nom = "";
         while (r.next()) {
         nom = r.getString("nom");
         }
         ResultSet r1 = con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre='" + rs.getString(3) + "';");
         String type = "";
         String desc = "";
         int prix = 0;
         int remise = 0;
         while (r1.next()) {
         type = r1.getString(5);
         desc = r1.getString(2);
         prix = r1.getInt(4);
         remise = r1.getInt(3);

         }
         FtypeChambre.setText(type);
         //Fdescription.setText(desc);
         FPrix.setText("" + prix);
         Fnom.setText(nom);
         System.out.println(nom);
         FdateA.setValue(LocalDate.parse(rs.getString(5)));
         FDateD.setValue(LocalDate.parse(rs.getString(6)));
         Fmodepaie.setText(rs.getString(8));
         Fformule.setText(rs.getString(9));
         FnbNuit.setText(rs.getString(10));
         Fremise.setValue(remise + "%");
         long total = remise;
         System.out.println(remise);
         long l = Math.abs(FDateD.getValue().toEpochDay() - FdateA.getValue().toEpochDay());
         total = prix * l * (1 - total / 100);
         Ftotal.setText("" + total);

         }*/
    }

    @FXML
    void confimer(ActionEvent event) throws SQLException {
        if (Freserv.getText() != null && Fconfirmation.isSelected() == true) {
           // btnfactEnreg.setDisable(false);
            btnprint.setDisable(false);
            
        } else {
           // btnfactEnreg.setDisable(true);
            btnprint.setDisable(true);
        }
    }

    @FXML
    void factAnnuler(ActionEvent event) throws IOException {
        System.out.println(Freserv.getText());
        Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FXMLFicheDePaiement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    void factEnreg(ActionEvent event) throws SQLException {
        if (Freserv.getText() != null) {
            Connection con = bd.connect();
            ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idReservation='" + Freserv.getText() + "';");
            while (rs1.next()) {
                FNchambre.setText(rs1.getString(3));
                ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient='" + rs1.getString(4) + "';");
                String nom = "";
                while (r.next()) {
                    nom = r.getString("nom");
                }
                ResultSet r1 = con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre='" + rs1.getString(3) + "';");
                String type = "";
                String desc = "";
                int prix = 0;
                int remise = 0;
                while (r1.next()) {
                    type = r1.getString(5);
                    desc = r1.getString(2);
                    prix = r1.getInt(4);
                    remise = r1.getInt(3);

                }
                // Connection con = bd.connect();
                int i = 0;
                ResultSet r2;
                r2 = con.createStatement().executeQuery("SELECT idClient FROM client WHERE nom='" + Fnom.getText() + "';");
                
                while (r2.next()) {
                    idC = r2.getString("idClient");
                }
                int rs;
                try {
                    //rs=con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`facture` (`idChambre`, `idClient`, `dateEntree`, `dateSortie`, `modePaiement`, `total`, `remise`) VALUES ('"+FNchambre.getText()+"', '"+id+"', '"+FdateA.getValue().toString()+"', '"+FDateD.getValue().toString()"', '"+Fmodepaie.getText()+"', '"+Ftotal.getText()+"', '"+remise+"');");
                    rs = con.createStatement().executeUpdate("INSERT INTO facture (idChambre, idClient, dateEntree, dateSortie, modePaiement, total, remise) VALUES ('" + FNchambre.getText() + "', '" + idC + "','" + FdateA.getValue().toString() + "', '" + FDateD.getValue().toString() + "', '" + Fmodepaie.getText() + "', '" + Ftotal.getText() + "', '" + remise + "');");
                    rs = con.createStatement().executeUpdate("UPDATE `projet_bd`.`reservation` SET `etat` = '2' WHERE (`idReservation` = '" + Nreserv.getText() + "');");
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    @FXML
    void print(ActionEvent event) throws IOException, SQLException {
        Connection con = bd.connect();
        ResultSet rs;
        rs=con.createStatement().executeQuery("SELECT idFacture FROM facture;");
        int j=0;
        String fact= "0000"+reservation;
        while(rs.next()){
            if(fact.equals(rs.getString("idFacture"))){
            j++;
            }
        }
        if(j==0){
            Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("ImprimerFacturePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.showAndWait();
            //factEnreg(event);
        }else{
            AlertTextprint.setOpacity(1);
        }
        
    }

}
