/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static projetbd2.AccueilDirecteurController.stage1;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EmployeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<EmployeTravail> table;
    @FXML private TableColumn<EmployeTravail, String> idEmploye;
    @FXML private TableColumn<EmployeTravail, String> tacheDesc;
    @FXML private TableColumn<EmployeTravail, String> tache;
    @FXML private TableColumn<EmployeTravail, String> jour;
    @FXML private TableColumn<EmployeTravail, String> horaire;
    public ObservableList list = FXCollections.observableArrayList();
    
    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnParametre;
    
    @FXML
    private GridPane pnCalendar;
    @FXML
    private GridPane pnParametre;
     
      @FXML
    private Label label;
    String idEmploy = LoginController.id;
    String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC";
    String utilisateur = "root";
    String motDePasse = "mumbeatrice";
    Connection connexion = null;
    ResultSet resultat = null;
    @FXML
    private Label lblNomEmploye;
    @FXML
    private ImageView imageCalendrier;
    @FXML
    private ImageView imageParametre;
   
    
    @Override
    public void initialize(URL Location, ResourceBundle rb) {
        // TODO
        idEmploye.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("idEmploye"));
        tacheDesc.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tacheDesc"));
        tache.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tache"));
        jour.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("jour"));
        horaire.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("horaire"));
        ArrayList<EmployeTravail> travail = new ArrayList<>();
          ModifierPlaningController.chargeDriver();
          label.setText(LoginController.nom);
          lblNomEmploye.setText(LoginController.nom);
          imageCalendrier.setOpacity(1);
          imageParametre.setOpacity(0);
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM jouremployetravail WHERE idEmploye ='" + idEmploy +"';");
    while ( resultat.next() ) {
         travail.add(new EmployeTravail(resultat.getString("idEmploye"), resultat.getString(2), resultat.getString(3), resultat.getString("jour"), resultat.getString("horaire")));
    /* Traiter ici les valeurs récupérées. */
    }
    for(int i =0; i < travail.size(); i++){
        list.add(travail.get(i));
    }
    table.setItems(list);
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
    public void handleClicks(ActionEvent event) {
        if(event.getSource() == btnCalendar ) {
            pnCalendar.toFront();
            pnCalendar.setOpacity(1);
            imageCalendrier.setOpacity(1);
            imageParametre.setOpacity(0);
            pnParametre.setOpacity(0);
            label.setText("Calendrier");
        }
        else if(event.getSource() == btnParametre){
            pnParametre.toFront();
             pnCalendar.setOpacity(0);
            pnParametre.setOpacity(1);
            imageCalendrier.setOpacity(0);
            imageParametre.setOpacity(1);
            label.setText("Parametres");
        }
    }

    @FXML
    public void refresh(ActionEvent event) {
    ArrayList<EmployeTravail> travail = new ArrayList<>();
    list.clear();
        try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM jouremployetravail WHERE idEmploye ='" + idEmploy +"';");
   
    while ( resultat.next() ) {
         travail.add(new EmployeTravail(resultat.getString("idEmploye"), resultat.getString(2), resultat.getString(3), resultat.getString("jour"), resultat.getString("horaire")));
    /* Traiter ici les valeurs récupérées. */
    }
    for(int i =0; i < travail.size(); i++){
      //  System.out.println(travail.get(i).getTacheDesc());
        //table.getItems().add(travail.get(i));
        list.add(travail.get(i));
    }
    table.refresh();
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
    
   
static Stage stage5;
    @FXML
    private void changePassword(ActionEvent event) throws IOException {
        /*Stage*/ stage5=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("ModifierPasswordEmploye.fxml"));
        Scene scene=new Scene(root);
        stage5.setScene(scene);
        stage5.initStyle(StageStyle.UNDECORATED);
        stage5.showAndWait();
    }

    @FXML
    private void btnDeconnexion(ActionEvent event) throws IOException {
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
    }

}
    
    

