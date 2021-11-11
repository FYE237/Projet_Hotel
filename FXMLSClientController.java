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
public class FXMLSClientController implements Initializable {
    @FXML
    private JFXTextField SupTel;
    @FXML
    private JFXTextField SupNationalite;
    @FXML
    private JFXTextField SupAdress;
    @FXML
    private JFXTextField SupEmail;
    @FXML
    private JFXTextField SupNPI;
    @FXML
    private JFXTextField SupVille;
    @FXML
    private ComboBox<String> SupType;
    @FXML
    private ComboBox<String> SupAuto;
    @FXML
    private ComboBox<String> SupSexe;
    @FXML
    private ComboBox<String> SupPI;
    @FXML
    private Text SupTextAlert;
    @FXML
    private Button SupClientValide;
    @FXML
    private Button SupClientAnnuler;

    BdConnection bd = new BdConnection();
    private String address;
    @FXML
    private Text Supnonvalide;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SupSexe.getItems().addAll("Masculin","Feminin");
        SupPI.getItems().addAll("CNI","Passeport");
        SupType.getItems().addAll("VIP","Simple");
        SupTextAlert.setOpacity(0);
        Supnonvalide.setOpacity(0);
        Connection con = bd.connect();
        ResultSet rs;
        try {
            rs= con.createStatement().executeQuery("SELECT nom FROM Client;");
            while(rs.next()){
            SupAuto.getItems().add(rs.getString("nom"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SupAdress.setEditable(false);
        SupEmail.setEditable(false);
        SupNPI.setEditable(false);
        SupNationalite.setEditable(false);
        SupPI.setDisable(true);
        SupSexe.setDisable(true);
        SupType.setDisable(true);
        SupVille.setEditable(false);
        SupTel.setEditable(false);
        SupClientValide.setDisable(true);
    }    

    @FXML
    private void SupAutomatique(ActionEvent event) throws SQLException {
          Connection con = bd.connect();
        ResultSet rs;
        rs= con.createStatement().executeQuery("SELECT * FROM client WHERE nom ='"+SupAuto.getValue()+"';");
        //String adress="";
        while(rs.next()){
            SupEmail.setText(rs.getString(6));
            SupTel.setText(rs.getString(5));
            SupSexe.setValue(rs.getString(4));
            SupType.setValue(rs.getString(7));
            SupAdress.setText(rs.getString(3));
            this.address=rs.getString(3);
        }
        ResultSet r1;
        r1=con.createStatement().executeQuery("SELECT * FROM adresse WHERE idAdresse ='"+SupAdress.getText()+"';");
        while(r1.next()){
            SupVille.setText(r1.getString(2));
            SupPI.setValue(r1.getString(3));
            SupNPI.setText(r1.getString(4));
            System.out.println(r1.getString(4));
        }
        SupAdress.setEditable(true);
        SupEmail.setEditable(true);
        SupNPI.setEditable(true);
        SupNationalite.setEditable(true);
        SupPI.setDisable(false);
        SupSexe.setDisable(false);
        SupType.setDisable(false);
        SupVille.setEditable(true);
        SupTel.setEditable(true);
        SupClientValide.setDisable(false);
    }

    @FXML
    private void SupValider(ActionEvent event) throws SQLException, IOException {
         Connection c=bd.connect();
         if(!SupAuto.getValue().equals("")){
             ResultSet r;
             int k=0;
             r=c.createStatement().executeQuery("SELECT * FROM reservation NATURAL JOIN client WHERE nom='"+SupAuto.getValue()+"';");
        while(r.next()){
            k++;
        }
        if(k==0){
             int rs;
        rs=c.createStatement().executeUpdate("DELETE FROM `projet_bd`.`client` WHERE (`nom` = '"+SupAuto.getValue()+"');");
        SupClientValide.setDisable(false);
        SupAnnuler(event);
        }else{Supnonvalide.setOpacity(1);
            
        }
         }else{
             SupTextAlert.setOpacity(1);
         }
    }

    @FXML
    private void SupAnnuler(ActionEvent event) throws IOException {
         Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FXMLSClient.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }
    
}
