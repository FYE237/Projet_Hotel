/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjoutPlaningController implements Initializable {
    @FXML
    private DatePicker date;
    @FXML
    public ComboBox<String> combo;
     @FXML
    private TextField nomEmploye;

    @FXML
    private TextField prenomEmploye;

    @FXML
    private TextField adresseEmploye;

    @FXML
    private TextField dateEmbauche;
     @FXML
    private  TextArea descTache;
      @FXML
    private ComboBox<String> horaire;
      @FXML
    private TextField tache;
      LocalDate ld;

    ObservableList<String> list;
    String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String utilisateur = "root";
    String motDePasse = "mumbeatrice";
    Connection connexion = null;
    ResultSet resultat;
    @FXML
    private Label status;
    
    @FXML
    public void comboAction(ActionEvent e){
        AjoutPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM employe WHERE idEmploye =" + combo.getValue() +  ";");
     while ( resultat.next() ) {
        nomEmploye.setText(resultat.getString("nom"));
        prenomEmploye.setText(resultat.getString("prenom"));
        adresseEmploye.setText(resultat.getString("adresse"));
        dateEmbauche.setText(resultat.getString("dateEmbauche"));

    /* Traiter ici les valeurs récupérées. */
    }
    

    /* Ici, nous placerons nos requêtes vers la BDD */
    /* ... */

} catch ( SQLException sql ) {
    /* Gérer les éventuelles erreurs ici */
} finally {
    if ( connexion != null )
        try {
            /* Fermeture de la connexion */
            connexion.close();
        } catch ( SQLException ignore ) {
            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
        }
}
    }
    public static void chargeDriver(){
    try {
        Class.forName( "com.mysql.cj.jdbc.Driver" );
    } catch ( ClassNotFoundException e ) {
      e.printStackTrace();
    }
    } 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        // TODO
        /* Connexion à la base de données */
        list = FXCollections.observableArrayList("8h - 20h", "20h - 8h");
        horaire.setItems(list);
       AjoutPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT idEmploye FROM employe;" );
     while ( resultat.next() ) {
         combo.getItems().add(resultat.getString("idEmploye"));

    /* Traiter ici les valeurs récupérées. */
    }
    

    /* Ici, nous placerons nos requêtes vers la BDD */
    /* ... */

} catch ( SQLException e ) {
    /* Gérer les éventuelles erreurs ici */
} finally {
    if ( connexion != null )
        try {
            /* Fermeture de la connexion */
            connexion.close();
        } catch ( SQLException ignore ) {
            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
        }
}
       
    }
 @FXML
    public void ajoutPlaning(ActionEvent event) {
         ld = date.getValue();
         AjoutPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    int statut = statement.executeUpdate( "INSERT INTO calendriertravail VALUES ('" + ld.toString() + "'" + ");");
    int tatut = statement.executeUpdate( "INSERT INTO jouremployetravail VALUES ('" + combo.getValue() + "', '" + descTache.getText() + "', '" + tache.getText() + "', '"+ ld.toString() + "','" + horaire.getValue() +"');");
    /* Ici, nous placerons nos requêtes vers la BDD */
    /* ... */
    if(tatut != 0){
        status.setTextFill(Color.web("green"));
       status.setText("Ajout reussi!!");
   }
} catch ( SQLException e ) {
     status.setTextFill(Color.web("red"));
    status.setText("Erreur d'ajout de planing!!");
    /* Gérer les éventuelles erreurs ici */
} finally {
    if ( connexion != null )
        try {
            /* Fermeture de la connexion */
            connexion.close();
        } catch ( SQLException ignore ) {
            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
        }
}

    }
    
    
    
    
    
    
}
