/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class SupprimerChambreController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField SCPrix;

    @FXML
    private TextField SCdesc;

    @FXML
    private ComboBox<String> SCtype;

    @FXML
    private ComboBox<String> SCremise;

    @FXML
    private Button SCenregistrer;

    @FXML
    private Button SCannuler;

    @FXML
    private ComboBox<String> SCnChambreBox;
    BdConnection bd =new BdConnection();
    @FXML
    void ChambreAutoS(ActionEvent event) throws SQLException, IOException {
          Connection con = bd.connect();
        ResultSet rs;
        rs=con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre ='"+SCnChambreBox.getValue()+"';");
        while(rs.next()){
            SCPrix.setText(rs.getInt(4)+"");
            SCdesc.setText(rs.getString(2));
            SCtype.setValue(rs.getString(5));
            SCremise.setValue(rs.getInt(3)+ "%");
            
            
        }
        SCenregistrer.setDisable(false);
        SAnnulerC(event);
    }

    @FXML
    void Mremise(ActionEvent event) {

    }

    @FXML
    void Mtype(ActionEvent event) {

    }

    @FXML
    void SAnnulerC(ActionEvent event) throws IOException {
          Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("SupprimerChambre.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    @FXML
    void SEnregistrerCh(ActionEvent event) throws SQLException {
        Connection c=bd.connect();
        int rs;
        rs=c.createStatement().executeUpdate("DELETE FROM `projet_bd`.`chambre` WHERE (`idChambre` = '"+SCnChambreBox.getValue()+"');");
        SCenregistrer.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SCPrix.setDisable(true);
        SCdesc.setDisable(true);
        SCremise.setDisable(true);
        SCtype.setDisable(true);
        SCenregistrer.setDisable(true);
        Connection c = bd.connect();
        ResultSet rs;
        try {
            rs=c.createStatement().executeQuery("SELECT idChambre FROM chambre");
            while(rs.next()){
                SCnChambreBox.getItems().add(rs.getString("idChambre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
