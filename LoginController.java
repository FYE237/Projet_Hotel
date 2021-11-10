/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projetbd2.NomUtilisateur;

import javafx.scene.control.ProgressIndicator;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button Connectbutton;
    @FXML
    private  TextField Nom_Box;
    @FXML
    private PasswordField Password_Box;
    @FXML
    private Label invalid_label;
    @FXML
    private PasswordField UserId_Box;
    
    private BdConnection bd;
    
    //private NomUtilisateur Nom;
    public static String nom;
    public static String id;

    
    //public static String nom; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      // Nom=new NomUtilisateur();
      bd=new BdConnection();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
         
        
        System.out.println("OK1");
      
        if (isValidCredential()==1) {
 
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("AccueilDirecteur.fxml"));
             Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            }
        else 
             if (isValidCredential()==2) {
   
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("ReceptionistePage.fxml"));
             Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            }
        else 
             if (isValidCredential()==0) {
   
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("Employe.fxml"));
             Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            }
        
        else {
            Nom_Box.clear();
            UserId_Box.clear();
            Password_Box.clear();

            invalid_label.setOpacity(1);
        
             }
    }
    
     private int isValidCredential() {
        int present = -1;
    
        System.out.println("SELECT * FROM Employe NATURAL JOIN userPasseword where nom=" + "'" + Nom_Box.getText() +"'"+ "idEmploye="+ "'" + UserId_Box.getText() +"' "+ "and password=" + "'" + Password_Box.getText()+ "';");
        Connection con=bd.connect();

        try {
            
            
            System.out.println("Open database successfully");
            ResultSet rs=con.createStatement().executeQuery("SELECT nom,idEmploye,password,idPoste FROM Employe NATURAL JOIN userPasseword where nom=" + "'" + Nom_Box.getText() +"' " + " and idEmploye="+ "'" + UserId_Box.getText() +"' "+ "and password=" + "'" + Password_Box.getText()+ "';");
            nom=new String(Nom_Box.getText());
            id=new String(UserId_Box.getText());
            if (rs.next()) {
                if ((rs.getString(1) != null) && (rs.getString(2) != null) && (rs.getString(3) != null)) {
                        if(rs.getString(4).equalsIgnoreCase("Directeur")) present = 1;
                        else if(rs.getString(4).equalsIgnoreCase("receptionniste")) present=2;
                        else present=0;
                    //String test=new String(rs.getString(2));
                    //Nom.setNom(rs.getString(2));
                   NomUtilisateur.setNom(rs.getString(2));
                   System.out.println("------- "+rs.getString(2));
                   System.out.println("========= "+NomUtilisateur.getNom());
                }
               
            }
           
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println("Operation donne successfully");
        return present;
    }
    
}
