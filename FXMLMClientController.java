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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLMClientController implements Initializable {
    @FXML
    private ComboBox<String> ModifAuto;
    @FXML
    private ComboBox<String> ModifSexe;
    @FXML
    private JFXTextField modifTel;
    @FXML
    private JFXTextField ModifNationalite;
    @FXML
    private JFXTextField ModifAdress;
    @FXML
    private JFXTextField ModifEmail;
    @FXML
    private JFXTextField ModifVille;
    @FXML
    private JFXTextField ModifNPI;
    @FXML
    private ComboBox<String> ModifPI;
    @FXML
    private ComboBox<String> ModifType;
    @FXML
    private Button ModifCAnnuler;
    @FXML
    private Button ModifCValider;
    @FXML
    private Text AlertText;
BdConnection bd = new BdConnection();
private String address;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ModifSexe.getItems().addAll("Masculin","Feminin");
        ModifPI.getItems().addAll("CNI","Passeport");
        ModifType.getItems().addAll("VIP","Simple");
        AlertText.setOpacity(0);
        Connection con = bd.connect();
        ResultSet rs;
        try {
            rs= con.createStatement().executeQuery("SELECT nom FROM Client;");
            while(rs.next()){
            ModifAuto.getItems().add(rs.getString("nom"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModifAdress.setEditable(false);
        ModifEmail.setEditable(false);
        ModifNPI.setEditable(false);
        ModifNationalite.setEditable(false);
        ModifPI.setDisable(true);
        ModifSexe.setDisable(true);
        ModifType.setDisable(true);
        ModifVille.setEditable(false);
        modifTel.setEditable(false);
        ModifCValider.setDisable(true);
        
    }    

    @FXML
    private void ModifAutomatique(ActionEvent event) throws SQLException {
         Connection con = bd.connect();
        ResultSet rs;
        rs= con.createStatement().executeQuery("SELECT * FROM client WHERE nom ='"+ModifAuto.getValue()+"';");
        //String adress="";
        while(rs.next()){
            ModifEmail.setText(rs.getString(6));
            modifTel.setText(rs.getString(5));
            ModifSexe.setValue(rs.getString(4));
            ModifType.setValue(rs.getString(7));
            ModifAdress.setText(rs.getString(3));
            this.address=rs.getString(3);
        }
        ResultSet r1;
        r1=con.createStatement().executeQuery("SELECT * FROM adresse WHERE idAdresse ='"+ModifAdress.getText()+"';");
        while(r1.next()){
            ModifVille.setText(r1.getString(2));
            ModifPI.setValue(r1.getString(3));
            ModifNPI.setText(r1.getString(4));
        }
        ModifAdress.setEditable(true);
        ModifEmail.setEditable(true);
        ModifNPI.setEditable(true);
        ModifNationalite.setEditable(true);
        ModifPI.setDisable(false);
        ModifSexe.setDisable(false);
        ModifType.setDisable(false);
        ModifVille.setEditable(true);
        modifTel.setEditable(true);
        ModifCValider.setDisable(false);
    }

    @FXML
    private void ModifAnnule(ActionEvent event) throws IOException {
         Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FXMLMClient.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();

    }

    @FXML
    private void ModifValide(ActionEvent event) throws SQLException, IOException {
          Connection con = bd.connect();
          if(!ModifAuto.getValue().equals("")){
         int rs;
         if(!(ModifAuto.getValue().equals(""))){
          rs = con.createStatement().executeUpdate("UPDATE `projet_bd`.`adresse` SET `idAdresse` = '"+ModifAdress.getText()+"', `ville` = '"+ModifVille.getText()+"', `pieceIdentité` = '"+ModifPI.getValue()+"', `NPI` = '"+ModifNPI.getText()+"' WHERE (`idAdresse` = '"+this.address+"');");
          rs = con.createStatement().executeUpdate("UPDATE `projet_bd`.`client` SET `idAdresse` = '"+ModifAdress.getText()+"', `sexe` = '"+ModifSexe.getValue()+"', `Tel` = '"+modifTel.getText()+"', `Email` = '"+ModifEmail.getText()+"', `type` = '"+ModifType.getValue()+"' WHERE (`idAdresse` = '"+this.address+"');");
         }
         ModifAnnule(event);}
          else{
          AlertText.setOpacity(1);}
    }
    
}
