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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FicheClientController implements Initializable {


   
    @FXML
    private Text Valeurincorect;
    
   
    BdConnection bd = new BdConnection();
    
    @FXML
    private ChoiceBox<String> clientSexe;

    @FXML
    private ChoiceBox<String> clientPI;

    @FXML
    private ChoiceBox<String> clientType;


    @FXML
    private Text champnonrempli;


    @FXML
    private JFXTextField clientNom;

    @FXML
    private JFXTextField clientTel;


    @FXML
    private JFXTextField clientAdresse;

    @FXML
    private JFXTextField clientEmail;

    @FXML
    private JFXTextField clientNPI;

    @FXML
    private JFXTextField clientVille;
    @FXML
    private Button EnregclientAnnuler;
    @FXML
    private Button clientEnregis;
    @FXML
    private JFXTextField clientNationalite;
    @FXML
    private Text Supnonvalide;

 

    @FXML
    void clientAnnulerEnregis(ActionEvent event) throws IOException {
             Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FicheClient.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clientPI.getItems().addAll("CNI", "Passeport");
        clientSexe.getItems().addAll("Masculin", "Feminin");
        clientType.getItems().addAll("VIP", "Simple");
        champnonrempli.setOpacity(0);
        Valeurincorect.setOpacity(0);
    }

    
    @FXML
    private void EnregistrerClients(ActionEvent event) throws SQLException, IOException {
        Connection con = bd.connect();

        int rs;
        if((clientNom.getText().equals("") || clientAdresse.getText().equals("") || clientTel.getText().equals(""))){
        
        champnonrempli.setOpacity(1);
        }else{
            try {
                String mat=clientNom.getText().substring(0, 3)+clientNPI.getText().substring(1, 4);
           //rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`chambre` SET `etat` = '1' WHERE (`idChambre` = '" + FRbnchambre.getValue() + "');");
           rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`adresse` (`idAdresse`, `ville`, `pieceidentité`, `NPI`) VALUES ('" + clientAdresse.getText() + "', '" + clientVille.getText() + "', '" + clientPI.getValue()+ "', '" + clientNPI.getText() + "');");
            rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`client` (`idClient`, `nom`, `idAdresse`, `sexe`, `Tel`, `Email`, `type`) VALUES ('" +"18p"+mat+ "', '" + clientNom.getText() + "', '" + clientAdresse.getText() + "', '" + clientSexe.getValue() + "', '" + clientTel.getText() + "', '" + clientEmail.getText() +"', '"+clientType.getValue()+ "');");
        } catch (SQLException e) {
            e.printStackTrace();

        } 
            clientAnnulerEnregis(event);
        }
       

    }


   

}
