

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutEmployeController implements Initializable {

    @FXML
    private Label lblAjoutReussi;
    @FXML
    private JFXTextField Nom_Box;
    @FXML
    private Label lblObg1;
    @FXML
    private JFXTextField Adresse_Box;
    @FXML
    private JFXTextField Id_Box;
    @FXML
    private Label error_label;
    @FXML
    private JFXTextField Prenom_Box;
    @FXML
    private JFXPasswordField Password_Box;
    @FXML
    private ComboBox<String> Poste_Box;
    @FXML
    private JFXDatePicker DateEmbauche_Box;
    @FXML
    private Label lblError_message;
    @FXML
    private ComboBox<String> Box_Sex;
    @FXML
    private JFXTextField Piece_Id;
    @FXML
    private Label lblObg11;
    @FXML
    private Label lblObg111;
    private JFXTextField Salaire_Box;
    @FXML
    private Label lblObg1111;
    @FXML
    private Label lblObg1113;

    private BdConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                dc=new BdConnection();
                Connection con1=dc.connect();
        try {
            ResultSet rs=con1.createStatement().executeQuery("select idPoste from poste");
            while(rs.next()) 
              {
                  Poste_Box.getItems().addAll(rs.getString(1));
              }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
                lblAjoutReussi.setOpacity(0);
                error_label.setOpacity(1);
                lblError_message.setOpacity(0);
                Box_Sex.getItems().addAll(
                    "masculin",
                    "feminin");
    }    

    @FXML
    private void ValiderAjout(ActionEvent event) {
        
        String test;
        System.out.println(DateEmbauche_Box.getValue().toString());
        System.out.println("INSERT INTO Employe(idEmploye,nom,prenom,dateEmbauche,Adresse,Salaire,Sexe,pieceIdentite) values ("+ "'"+Id_Box.getText()+"' , '"+Nom_Box.getText()+"' , '"+Prenom_Box.getText()+"', '"+DateEmbauche_Box.getValue().toString()+"' ,'"+Adresse_Box.getText()+"' ,'"+Box_Sex.getValue()+"','"+Piece_Id.getText()+"');");
        Connection con=dc.connect() ;
        int i=0,j=0,k=0,n=0;
        int rs=0;
        try {
            System.out.println("Open database successfully");
                 rs=con.createStatement().executeUpdate("INSERT INTO Employe(idEmploye,nom,prenom,dateEmbauche,Adresse,Sexe,pieceIdentite) values ('"+Id_Box.getText()+"' , '"+Nom_Box.getText()+"' , '"+Prenom_Box.getText()+"', '"+DateEmbauche_Box.getValue().toString()+"' ,'"+Adresse_Box.getText()+"' ,'"+Box_Sex.getValue()+"','"+Piece_Id.getText()+"');");
                 i=rs;
                 rs=con.createStatement().executeUpdate("INSERT INTO userpasseword values('"+Id_Box.getText()+"' , '"+Password_Box.getText()+"','"+Poste_Box.getValue()+"');");
                 j=rs;
                 rs=con.createStatement().executeUpdate("INSERT INTO EmployePoste values('"+Id_Box.getText()+ "','"+Poste_Box.getValue()+"' );");
                 k=rs;
                 rs=con.createStatement().executeUpdate("insert into archiveemploye values('"+Nom_Box.getText()+"','"+Prenom_Box.getText()+"', '"+Box_Sex.getValue()+"','"+Poste_Box.getValue()+"' , '"+DateEmbauche_Box.getValue().toString()+"' ,'"+Piece_Id.getText()+"');");
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println(i+","+j+","+k);
        if(i==0||j==0||k==0)
            {
                Id_Box.clear();
                Password_Box.clear();
                Nom_Box.clear();
                Prenom_Box.clear();
                Adresse_Box.clear();
                Piece_Id.clear();
                lblError_message.setOpacity(1);
            }
        else{
                lblAjoutReussi.setOpacity(1);
                error_label.setOpacity(1);
                lblError_message.setOpacity(0);
        }
        
    }

    @FXML
    private void fermer(ActionEvent event) {
        Stage stage=AccueilDirecteurController.stage3;
        stage.close();
    }


    
}
