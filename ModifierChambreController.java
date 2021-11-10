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
public class ModifierChambreController implements Initializable {
    private TextField MCnChambre;

    @FXML
    private TextField MCPrix;

    @FXML
    private TextField MCdesc;

    @FXML
    private ComboBox<String> MCtype;

    @FXML
    private ComboBox<Integer> MCremise;

    @FXML
    private Button MCenregistrer;

    @FXML
    private Button MCannuler;
    
    BdConnection bd = new BdConnection();
    @FXML
    private ComboBox<String> MCnChambreBox;
    @FXML
    void ChambreAuto(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        ResultSet rs;
        rs=con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre ='"+MCnChambreBox.getValue()+"';");
        while(rs.next()){
            MCPrix.setText(rs.getInt(4)+"");
            MCdesc.setText(rs.getString(2));
            MCtype.setValue(rs.getString(5));
            MCremise.setValue(rs.getInt(3));
            
        }
         MCenregistrer.setDisable(false);
    }

    @FXML
    void MAnnulerC(ActionEvent event) throws IOException {
          Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("ModifierChambre.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    @FXML
    void MEnregistrerCh(ActionEvent event) throws SQLException, IOException {
        Connection con = bd.connect();
        int rs;
        int prix = Integer.parseInt(MCPrix.getText());
        rs=con.createStatement().executeUpdate("UPDATE `projet_bd`.`chambre` SET `description_chambre` = '"+MCdesc.getText()+"', `maxRemise` = '"+MCremise.getValue()+"', `prix` = '"+prix+"', `type` = '"+MCtype.getValue()+"' WHERE (`idChambre` = '"+MCnChambreBox.getValue()+"');");
        MAnnulerC(event);
    }

    @FXML
    void Mremise(ActionEvent event) {

    }

    @FXML
    void Mtype(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          MCtype.getItems().addAll("Simple", "Double", "Sweet");
        MCremise.getItems().addAll(0, 5, 10,15,20,25);
        MCenregistrer.setDisable(true);
        Connection c = bd.connect();
        ResultSet rs;
        try {
            rs=c.createStatement().executeQuery("SELECT idChambre FROM chambre");
            while(rs.next()){
                MCnChambreBox.getItems().add(rs.getString("idChambre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
