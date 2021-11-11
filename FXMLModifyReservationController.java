/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
  
public class FXMLModifyReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label TitreMod;

    @FXML
    private Button EnregistrerM;

    @FXML
    private FontAwesomeIcon SaveFont;
     @FXML
    private JFXTextField MRnumeroChambre;
   
    @FXML
    private DatePicker MRdateA;

    @FXML
    private DatePicker MRdateD;

    @FXML
    private JFXTextField MRnom;

    @FXML
    private TextField MRnbnuit;

    @FXML
    private TextField MRdesc;

    @FXML
    private TextField MRprix;

    @FXML
    private Button AnnulerM;
    @FXML
    private Button MRcalcul;
    
    @FXML
    private ComboBox<String> MRnresev;

    @FXML
    private ComboBox<String> MRtype;

    @FXML
    private ComboBox<String> MRformule;

    @FXML
    private ComboBox<String> MRmode;
      
    BdConnection bd =new BdConnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MRprix.setEditable(false);
        MRdateA.setDisable(true);
        MRdateD.setDisable(true);
        MRdesc.setEditable(false);
        MRformule.setDisable(true);
        MRmode.setDisable(true);
        MRnom.setEditable(false);
        MRprix.setEditable(false);
        MRtype.setDisable(true);
        MRnbnuit.setEditable(false);
        MRnumeroChambre.setDisable(true);
        EnregistrerM.setDisable(true);
        Connection con = bd.connect();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery("SELECT idReservation FROM reservation WHERE etat='0';");
            while(rs.next()){
            MRnresev.getItems().add(rs.getString("idReservation"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLModifyReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate date = LocalDate.now();
        System.out.println(date );
         MRtype.getItems().addAll(
                    "Simple",
                    "Double",
                    "Sweet"
            );
    }    

    @FXML
    private void MRAuto(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idReservation='"+MRnresev.getValue()+"';");
        while(rs.next()){
            MRnumeroChambre.setText(rs.getString(3));
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
            MRtype.setValue(type);
            MRdesc.setText(desc);
            MRprix.setText(""+prix);
            MRnom.setText(nom);
            MRdateA.setValue(LocalDate.parse(rs.getString(5)));
            MRdateD.setValue(LocalDate.parse(rs.getString(6)));
            MRmode.setValue(rs.getString(8));
            MRformule.setValue(rs.getString(9));
            MRnbnuit.setText(rs.getString(10));
            EnregistrerM.setDisable(false);
            
        }
         MRprix.setEditable(false);
        MRdateA.setDisable(false);
        MRdateD.setDisable(false);
        MRdesc.setEditable(false);
        MRformule.setDisable(false);
        MRmode.setDisable(false);
        MRnom.setEditable(true);
        MRprix.setEditable(false);
        MRtype.setDisable(false);
        MRnbnuit.setEditable(false);
        MRnumeroChambre.setDisable(false);
        EnregistrerM.setDisable(false);
    }

    @FXML
    private void EnregistreM(ActionEvent event) throws IOException, SQLException {
         Connection con = bd.connect();
         String id="";
          ResultSet r1;
          r1= con.createStatement().executeQuery("SELECT idClient FROM client WHERE nom='"+MRnom.getText()+"';");
         while(r1.next()){
             id=r1.getString("idClient");
         }
          int rs;
         if(!(MRnresev.getValue().equals(""))){
          rs = con.createStatement().executeUpdate("UPDATE `projet_bd`.`reservation` SET `idChambre` = '"+MRnumeroChambre.getText()+"', `dateEntree` = '"+MRdateA.getValue()+"', `dateSortie` = '"+MRdateD.getValue()+"', `modepaiement` = '"+MRmode.getValue()+"', `formule` = '"+MRformule.getValue()+"', `nbnuits` = '"+MRnbnuit.getText()+"', `idClient` = '"+id+"' WHERE (`idReservation` = '"+MRnresev.getValue()+"');");
         // rs = con.createStatement().executeUpdate("UPDATE `projet_bd`.`client` SET `idAdresse` = '"+ModifAdress.getText()+"', `sexe` = '"+ModifSexe.getValue()+"', `Tel` = '"+modifTel.getText()+"', `Email` = '"+ModifEmail.getText()+"', `type` = '"+ModifType.getValue()+"' WHERE (`idAdresse` = '"+this.address+"');");
         }
         AnnuleM(event);
          
    }

    @FXML
    private void AnnuleM(ActionEvent event) throws IOException {
         Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("FXMLModifyReservation.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    @FXML
    private void Mprix(ActionEvent event) throws SQLException {
             Connection con = bd.connect();
        int i=0;
        ResultSet rs;
        /*if(FRtypeChambre.getValue()==null){
            rs=con.createStatement().executeQuery("SELECT type FROM chambre WHERE idChambre='"+FRbnchambre.getValue()+"';");
            while(rs.next()){
                FRtypeChambre.setValue(rs.getString("type"));
        }
            FRtypeChambre.setDisable(true);
        }*/
        rs = con.createStatement().executeQuery("SELECT prix FROM projet_bd.chambre WHERE idChambre='"+MRnumeroChambre.getText()+"';");
        //System.out.println(rs.getInt(1));
      
        while(rs.next()){
        MRprix.setText(rs.getString("prix"));
        }
        ResultSet rs1;
        rs1=con.createStatement().executeQuery("SELECT * FROM chambre WHERE idChambre='"+MRnumeroChambre.getText()+"';");
        while(rs1.next()){
          MRdesc.setText(rs1.getString("description_chambre"));
          MRtype.setValue(rs1.getString(5));
    }
    }

    @FXML
    private void MRcaluculer(ActionEvent event) {
         long l=Math.abs( MRdateD.getValue().toEpochDay()-MRdateA.getValue().toEpochDay());
        MRnbnuit.setText(""+l);
    }
    
}
