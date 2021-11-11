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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class EnregistrementPageController implements Initializable {
    
    @FXML
    private JFXTextField FRNreserv;
    @FXML
    private DatePicker FRdateA;
    @FXML
    private DatePicker FRdateD;
    @FXML
    private JFXTextField FRnomclient;
   
    @FXML
    private TextField FRnbnuit;
    @FXML
    private TextField FRdescription;
    @FXML
    private TextField FRprix;
    @FXML
    private Button FRbtnenreg;
    @FXML
    private Button FRannuler;
    @FXML
    private Text FRerreur;
    @FXML
    private Button FRcalcul;
    
    BdConnection bd = new BdConnection();
    @FXML
    private ComboBox<String> FRbnchambre;
    @FXML
    private ComboBox<String> FRtypeChambre;
    @FXML
    private ComboBox<String> FRformule;
    @FXML
    private ComboBox<String> FRmodepaie;
    @FXML
    private Text RnreservAlaert;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RnreservAlaert.setOpacity(0);
        FRnbnuit.setEditable(false);
        FRprix.setDisable(true);
        FRmodepaie.getItems().addAll("Orange Money", "MTN Money", "Carte bancaire");
        FRformule.getItems().addAll("Aucune", "Petit dejeuner", "Tout inclus");
        FRtypeChambre.getItems().addAll("Simple", "Double", "Sweet");
        Connection con = bd.connect();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery("SELECT idChambre FROM chambre WHERE etat = 0;");
             while(rs.next()){
                 FRbnchambre.getItems().add(rs.getString("idChambre"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(EnregistrementPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       FRerreur.setOpacity(0);
    }

    @FXML
    private void EnregistrerReserv(ActionEvent event) throws SQLException, IOException {
        RnreservAlaert.setOpacity(0);
        FRerreur.setOpacity(0);
       if((FRNreserv.getText().equals(null) || FRnbnuit.getText().equals(null) || FRbnchambre.getValue().equals(null))){ 
        FRerreur.setOpacity(1);
       }else{
           Connection con = bd.connect();
        int i=0;
        ResultSet r;
        r=con.createStatement().executeQuery("SELECT idClient FROM client WHERE nom='"+FRnomclient.getText()+"';");
        String id="";
        while(r.next()){
            id=r.getString("idClient");
        }
        int k=0;
        ResultSet r1;
        r1 = con.createStatement().executeQuery("SELECT * FROM reservation;");
        while(r1.next()){
            if(r1.getString(1).equals(FRNreserv.getText())){
                k++;
            }
        }
        if(k==0){
        int rs;
       try{
        rs = con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`reservation` (`idReservation`, `idEmploye`, `idChambre`, `idClient`, `dateEntree`, `dateSortie`, `etat`, `modepaiement`, `formule`, `nbnuits`) VALUES ('"+FRNreserv.getText()+"', '1', '"+FRbnchambre.getValue()+"', '"+id+"', '"+FRdateA.getValue().toString()+"', '"+FRdateD.getValue().toString()+"', '0', '"+FRmodepaie.getValue()+"', '"+FRformule.getValue()+"', '"+FRnbnuit.getText()+"');");
        rs=con.createStatement().executeUpdate("UPDATE `projet_bd`.`chambre` SET `etat` = '1' WHERE (`idChambre` = '"+FRbnchambre.getValue()+"');");
       }catch(SQLException e){
           e.printStackTrace();
           
       }
            AnnulerReserv(event);
        }else{RnreservAlaert.setOpacity(1);}
       }
       }

    @FXML
    private void AnnulerReserv(ActionEvent event) throws IOException {
        Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("EnregistrementPage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();

    }

    @FXML
    private void Calculer(ActionEvent event) {
        //SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
        
        long l=Math.abs( FRdateD.getValue().toEpochDay()-FRdateA.getValue().toEpochDay());
        FRnbnuit.setText(""+l);
        
        /*try{
            Date arriv = (Date) sdf.parse("25/01/2006");
            Date depart = (Date) sdf.parse("11/02/2007");
            long diff = depart.getTime()-arriv.getTime();
            float res = (diff/(1000*60*60*24));
System.out.println("jours"+res);
//FRnbnuit.setText(""+res);
            
        }catch(ParseException e){
            e.printStackTrace();
        }*/
    
    }
      @FXML
    void FRnCprix(ActionEvent event) throws SQLException {
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
        rs = con.createStatement().executeQuery("SELECT prix FROM projet_bd.chambre WHERE idChambre='"+FRbnchambre.getValue()+"';");
        //System.out.println(rs.getInt(1));
      
        while(rs.next()){
        FRprix.setText(rs.getString("prix"));
        }
        ResultSet rs1;
        rs1=con.createStatement().executeQuery("SELECT description_chambre FROM chambre WHERE idChambre='"+FRbnchambre.getValue()+"';");
        while(rs1.next()){
          FRdescription.setText(rs1.getString("description_chambre"));
    }
    }

   /* private void identifierChambre(ActionEvent event) throws SQLException {
        //FRprix.setText("5000");
         Connection con = bd.connect();
        int i=0;
        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT prix FROM projet_bd.chambre WHERE idChambre='"+FRnchambre.getText()+"';");
        //System.out.println(rs.getInt(1));
        while(rs.next()){
        FRprix.setText(rs.getString("prix"));
        }
    }*/

    @FXML
    private void typeChambre(ActionEvent event) throws SQLException {
        Connection c =bd.connect();
        ResultSet rs;
        int i=0;
        rs=c.createStatement().executeQuery("SELECT idChambre FROM chambre WHERE type='"+FRtypeChambre.getValue()+"' AND etat= 0;");
        FRbnchambre.getItems().clear();
        while(rs.next()){
            FRbnchambre.getItems().add(rs.getString("idChambre"));
    }
    }

    @FXML
    private void Formule(ActionEvent event) {
    }
        
    
}


