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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjoutChambreController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField ACnChambre;

    @FXML
    private TextField ACPrix;

    @FXML
    private TextField ACdesc;

    @FXML
    private ComboBox<String> ACtype;

    @FXML
    private ComboBox<Integer> ACremise;

    @FXML
    private Button ACenregistrer;

    @FXML
    private Button ACannuler;
    BdConnection bd = new BdConnection();
    @FXML
    private Text AlertTextprint;
    @FXML
    private Text AlertTextvide;

    @FXML
    void AnnulerAC(ActionEvent event) throws IOException {
        Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("AjoutChambre.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();       
    }

    @FXML
    void EnregistrerCh(ActionEvent event) throws SQLException, IOException {
        Connection con =bd.connect();
          AlertTextvide.setOpacity(0);
//        int pr=Integer.parseInt(ACPrix.getText());
        //int remise = ACremise.getValue();
        if((ACnChambre.getText().equals("") || ACPrix.getText().equals(""))){
        
          AlertTextvide.setOpacity(1);
        }else{ try{
            // rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`chambre` (`idChambre`, `description_chambre`, `maxRemise`, `prix`, `type`, `etat`) VALUES ('"+ACnChambre.getText()+"', '"+ACdesc.getText()+"', '"+remise+"', '"+pr+"', '"+ACtype.getValue()+"', '0');");

        int rs;
            rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`chambre` (`idChambre`, `description_chambre`, `maxRemise`, `prix`, `type`, `etat`) VALUES ('"+ACnChambre.getText()+"', '"+ACdesc.getText()+"', '"+ACremise.getValue()+"', '"+Integer.parseInt("12000")+"', '"+ACtype.getValue()+"', '0');");
            AnnulerAC(event);
        }catch(SQLException e){
            AlertTextvide.setOpacity(1);
           e.printStackTrace();
           
       }    
        
        }
        AnnulerAC(event);
        /* Connection con =bd.connect();
        AlertTextprint.setOpacity(0);
        AlertTextvide.setOpacity(0);
        int remise;
        int k=0;
        int prix =Integer.parseInt(ACPrix.getText());
        if(ACremise.getValue().equals("Aucune")){
           remise =0;
        }
        else remise = Integer.parseInt(ACremise.getValue());
        if(!(ACnChambre.getText().equals("")&&ACPrix.getText().equals("")&&ACremise.getValue().equals(""))){
        ResultSet r;
        r=con.createStatement().executeQuery("SELECT idChambre FROM chambre;");
        while(r.next()){
            if(ACnChambre.getText().equals(r.getString("idChambre"))){
                k++;
            }
        }
        if(k==0){
            try{
            // rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`chambre` (`idChambre`, `description_chambre`, `maxRemise`, `prix`, `type`, `etat`) VALUES ('"+ACnChambre.getText()+"', '"+ACdesc.getText()+"', '"+remise+"', '"+pr+"', '"+ACtype.getValue()+"', '0');");

        int rs;
            rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`chambre` (`idChambre`, `description_chambre`, `maxRemise`, `prix`, `type`, `etat`) VALUES ('"+ACnChambre.getText()+"', '"+ACdesc.getText()+"', '"+remise+"', '"+prix+"', '"+ACtype.getValue()+"', '0');");
        }catch(SQLException e){
           e.printStackTrace();
           
       }
        }
        }else{
            AlertTextvide.setOpacity(1);
        }*/
        
        
        }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ACtype.getItems().addAll("Simple", "Double", "Sweet");
        ACremise.getItems().addAll(0, 5, 10,15,20,25);
        AlertTextprint.setOpacity(0);
        AlertTextvide.setOpacity(0);
    }    
    
}
