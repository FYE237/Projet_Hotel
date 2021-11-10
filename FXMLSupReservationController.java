/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.protocol.Resultset;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLSupReservationController implements Initializable {
    @FXML
    private ComboBox<String> SRNchambre;
    @FXML
    private Label TitreMod;
    @FXML
    private DatePicker SRDateArrive;
    @FXML
    private DatePicker SRDatedepart;
    @FXML
    private JFXTextField SRnomclient;
    @FXML
    private ComboBox<String> SRformule;
    @FXML
    private ComboBox<String> SRtypechambre;
    @FXML
    private ComboBox<String> SRmodepaie;
    @FXML
    private TextField SRnbnuit;
    @FXML
    private TextField SRdescription;
    @FXML
    private TextField SRprix;
    @FXML
    private ComboBox<String> SRnreserv;
    BdConnection bd = new BdConnection();
    @FXML
    private Button SRsupprimer;
    @FXML
    private Button SRannuler;
    @FXML
    private Text SRerreur;
    @FXML
    private Text SRbien;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SRsupprimer.setDisable(true);
        SRerreur.setOpacity(0);
        SRbien.setOpacity(0);
        SRDateArrive.setDisable(true);
        SRDatedepart.setDisable(true);
        SRNchambre.setDisable(true);
        SRdescription.setEditable(false);
        SRformule.setDisable(true);
        SRmodepaie.setDisable(true);
        SRnbnuit.setEditable(false);
        SRnomclient.setEditable(false);
        SRprix.setEditable(false);
        SRtypechambre.setDisable(true);
        Connection con = bd.connect();
      
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT idReservation FROM reservation WHERE etat =0;");
             while(rs.next()){
                 SRnreserv.getItems().add(rs.getString("idReservation"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(EnregistrementPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            rs= con.createStatement().executeQuery(" ");
            while(rs.next){
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSupReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
    }    

    @FXML
    private void nReserv(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idReservation='"+SRnreserv.getValue()+"';");
        while(rs.next()){
            SRNchambre.setValue(rs.getString(3));
            ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient='"+rs.getString(4)+"';");
            String nom="";
            while(r.next()){
              nom = r.getString("nom");
            }
            ResultSet r1 = con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre='"+rs.getString(3)+"';");
            String type="";
            String desc="";
            int prix=0;
            while(r1.next()){
               type=r1.getString(5);
               desc = r1.getString(2);
               prix=r1.getInt(4);
            }
            SRtypechambre.setValue(type);
            SRdescription.setText(desc);
            SRprix.setText(""+prix);
            SRnomclient.setText(nom);
            SRDateArrive.setValue(LocalDate.parse(rs.getString(5)));
            SRDatedepart.setValue(LocalDate.parse(rs.getString(6)));
            SRmodepaie.setValue(rs.getString(8));
            SRformule.setValue(rs.getString(9));
            SRnbnuit.setText(rs.getString(10));
            SRsupprimer.setDisable(false);
            
        }
    }

    @FXML
    private void supprimerR(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        if(SRnreserv.getValue()==null){
            SRerreur.setOpacity(1);
        }else{
        int rs=con.createStatement().executeUpdate("DELETE FROM reservation WHERE (idReservation = '"+SRnreserv.getValue()+"');");
        SRbien.setOpacity(1);
        SRerreur.setOpacity(0);
        SRDateArrive.setValue(null);
        SRDatedepart.setValue(null);
        SRNchambre.setValue("");
        SRdescription.clear();
        SRformule.setValue("");
        SRmodepaie.setValue("");
        SRnbnuit.clear();
        SRnomclient.clear();
        SRnreserv.setValue(null);
        SRprix.clear();
        SRtypechambre.setValue("");
        }

    }

    @FXML
    private void annulerSR(ActionEvent event) throws IOException {
         Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FXMLSupReservation.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }
    
}
