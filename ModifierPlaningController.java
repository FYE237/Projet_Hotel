/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
    
  

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierPlaningController implements Initializable {
    @FXML
    private TextField horaire;
    @FXML
    private ComboBox<String> date;

    @FXML
    private ComboBox<String> idEmploye;

    @FXML
    private TextField nomEmploye;

    @FXML
    private TextField prenomEmploye;

    @FXML
    private TextField adresseEmploye;

    @FXML
    private TextField dateEmbauche;

    @FXML
    private TextField tache;

    @FXML
    private TextArea descTache;
    String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    String utilisateur = "root";
    String motDePasse = "mumbeatrice";
    Connection connexion = null;
    ResultSet resultat;
    public ObservableList<String> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
     public static void chargeDriver(){
    try {
        Class.forName( "com.mysql.cj.jdbc.Driver" );
    } catch ( ClassNotFoundException e ) {
      e.printStackTrace();
    }
    } 
    @FXML
    private Label status;
     
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        // TODO
    ModifierPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT jour FROM calendriertravail;" );
     while ( resultat.next() ) {
         date.getItems().add(resultat.getString("jour"));
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
    public void dateAction(ActionEvent e){
        list.clear();
        nomEmploye.clear();
        prenomEmploye.clear();
        adresseEmploye.clear();
        descTache.clear();
        tache.clear();
        dateEmbauche.clear();
        horaire.clear();
       ModifierPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT idEmploye FROM jouremployetravail WHERE jour = '" + date.getValue() + "';");
   
    while ( resultat.next() ) {
        list.add(resultat.getString("idEmploye"));
         //idEmploye.getItems().add(resultat.getString("idEmploye"));
    /* Traiter ici les valeurs récupérées. */
    }
    idEmploye.setItems(list);
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
    
    @FXML
        public void idEmployeAction(ActionEvent e){
        AjoutPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM employe WHERE idEmploye =" + idEmploye.getValue() +  ";");
     while ( resultat.next() ) {
        nomEmploye.setText(resultat.getString("nom"));
        prenomEmploye.setText(resultat.getString("prenom"));
        adresseEmploye.setText(resultat.getString("adresse"));
        dateEmbauche.setText(resultat.getString("dateEmbauche"));

    /* Traiter ici les valeurs récupérées. */
    }
     resultat = statement.executeQuery( "SELECT descriptionTravail,tache,horaire FROM jouremployetravail WHERE jour = '" + date.getValue() + "';");
    while ( resultat.next() ) {
       descTache.setText(resultat.getString("descriptionTravail"));
        tache.setText(resultat.getString("tache"));
        horaire.setText(resultat.getString("horaire"));
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
  
        
    @FXML
  public void modifieAction(ActionEvent event) {
            
            ModifierPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
     int statut = statement.executeUpdate("UPDATE jouremployetravail SET tache = '" + tache.getText() + "', descriptionTravail = '" + descTache.getText() + "', horaire = '" + horaire.getText() + "' WHERE idEmploye = '" + idEmploye.getValue() + "' and jour = '"+ date.getValue() +"';");
   if(statut != 0){
       status.setTextFill(Color.web("green"));
       status.setText("Modification reussi!!!");
   }
 
} catch ( SQLException sql ) {
    status.setTextFill(Color.web("red"));
    status.setText("Erreur de modification!!");
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
