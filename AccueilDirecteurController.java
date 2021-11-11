package projetbd2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import projetbd2.NomUtilisateur;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AccueilDirecteurController implements Initializable {

    @FXML
    private JFXButton btnEmploye;
    @FXML
    private JFXButton btnClient;
    @FXML
    private JFXButton btnChambre;
    @FXML
    private JFXButton btnCalendrier;
    @FXML
    private JFXButton btnArchives;
    @FXML
    private JFXButton btnFinances;
    @FXML
    private JFXButton btnPostes;
    @FXML
    private JFXButton btnParametres;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatus;
    @FXML
    private GridPane pnEmployes;
    @FXML
    private GridPane pnClient;
    @FXML
    private Button btnRechercheEmploye;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField FieldRechercheEmploye;
    @FXML
    private TableView<Employe> tableUser;
    @FXML
    private TableColumn<Employe, String> columnIdEmploye;
    @FXML
    private TableColumn<Employe, String> columnNom;
    @FXML
    private TableColumn<Employe, String> columnPrenom;
    @FXML
    private TableColumn<Employe, String> columnAdresse;
    private TableColumn<Employe, String> columnDateEmbauche;

    
     private ObservableList<Employe> data;
    @FXML
    private TableColumn<Employe, String> columnPoste;
    @FXML
    private Label lblNomEmploye;
    
    private NomUtilisateur Nom= new NomUtilisateur();
    @FXML
    private GridPane pnCalendrier;
    @FXML
    private GridPane pnArchive;
    @FXML
    private GridPane pnFinance;
    @FXML
    private GridPane pnPoste;
    @FXML
    private GridPane pnParametre;
    @FXML
    private GridPane pnChambre;
    @FXML
    private GridPane pnArchiveClient;
    @FXML
    private TableView<ArchiveClient> archiveTableClient;
    @FXML
    private ComboBox<String> choixTypeArchiveClient;
    @FXML
    private GridPane pnArchiveClientDate;
    @FXML
    private Button RechercherClientDate;
    @FXML
    private DatePicker ClientDate1;
    @FXML
    private DatePicker ClientDate2;
    @FXML
    private GridPane pnArchiveClientNom;
    @FXML
    private Button RechercherClientNom;
    @FXML
    private TextField RechercheNomClient;
    
    
    @FXML
    private GridPane pnArchiveEmploye;
    @FXML
    private ComboBox<String> ChoixTypeArchiveEmploye;
    @FXML
    private GridPane pnArchiveEmployeDate;
    @FXML
    private DatePicker EmployeDate1;
    @FXML
    private GridPane pnArchiveEmployePoste;
    @FXML
    private GridPane pnArchiveEmployeNom;
    @FXML
    private ComboBox<String> ChoixArchives;
    /**
     * Initializes the controller class.
     */
    
    
    private BdConnection dc;
    @FXML
    private TextField RechercheEmployeNom_box;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_Nom;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_Prenom;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_Sexe;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_Poste;
    
    private ObservableList<Employe> data_ArchiveEmploye;
    @FXML
    private TableView<Employe> table_ArchiveEmploye;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_date;
    @FXML
    private GridPane pnArchiveClient2;
    @FXML
    private GridPane pnArchiveEmploye2;
    @FXML
    private TableColumn<Poste, String> columnPoste_idPoste;
    @FXML
    private TableColumn<Poste, String> columnPoste_salaire;
    @FXML
    private TableColumn<Poste, String> columnPoste_description;
    @FXML
    private TableView<Poste> tablePoste;
    
    private ObservableList<Poste> data_Poste;
    @FXML
    private ComboBox<String> ArchivePoste_Box;
    @FXML
    private TableColumn<Employe, String> columnArchiveEmploye_pieceIdentite;
    @FXML
    private JFXDatePicker dateEntre;
    @FXML
    private JFXDatePicker dateSortie;
    @FXML
    private TableView<Finance> tableFinance;
    private ObservableList<Finance> data_Finance;
    @FXML
    private TableColumn<Finance, Integer> columnFinance_IdFacture;
    @FXML
    private TableColumn<Finance, String> columnFinance_IdChambre;
    @FXML
    private TableColumn<Finance, Integer> columnFinance_total;
    @FXML
    private TableColumn<Finance, Integer> columnFinance_Remise;
    @FXML
    private Label Recette;
    
    @FXML private TableView<EmployeTravail> table;
    @FXML private TableColumn<EmployeTravail, String> idEmploye;
    @FXML private TableColumn<EmployeTravail, String> tacheDesc;
    @FXML private TableColumn<EmployeTravail, String> tache;
    @FXML private TableColumn<EmployeTravail, String> jour;
    @FXML private TableColumn<EmployeTravail, String> horaire;
    
    String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC";  
    String utilisateur = "root";
    String motDePasse = "mumbeatrice";
        Connection connexion = null;
    ResultSet resultat;
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ImageView imageChambre;
    @FXML
    private ImageView imageReservation;
    @FXML
    private ImageView imageFacture;
    @FXML
    private ImageView imagereception;
    @FXML
    private JFXButton btnReservation;
    @FXML
    private JFXButton btnFacturation;
    @FXML
    private TableView<Client> tab;
    @FXML
    private TableColumn<Client, String> IdC;
    @FXML
    private TableColumn<Client, String> nomC;
    @FXML
    private TableColumn<Client, String> sexeC;
    @FXML
    private TableColumn<Client, String> adresseC;
    @FXML
    private TableColumn<Client, String> telC;
    @FXML
    private TableColumn<Client, String> EmailC;
    @FXML
    private TextField txtRechercheClient;
    @FXML
    private Button btnRechercheClient;
    @FXML
    private ComboBox<String> ChoixRC;
    @FXML
    private Button btnAjoutClient;
    @FXML
    private Button btnModifierClient;
    @FXML
    private Button SupprimerClient;
    @FXML
    private Button btnAjoutChambre;
    @FXML
    private Button btnModifierC;
    @FXML
    private Button btnSupprimerC;
    @FXML
    private Button btnChambresliste;
    @FXML
    private TextField RechercheCambre;
    @FXML
    private Button btnRecherchechambre;
    @FXML
    private TableView<Chambre> tabC;
    @FXML
    private TableColumn<Chambre,String> nChambre;
    @FXML
    private TableColumn<Chambre,Integer> prixChambre;
    @FXML
    private TableColumn<Chambre,String> descriptionc;
    @FXML
    private TableColumn<Chambre,String> typeC;
    @FXML
    private TableColumn<Chambre,Integer> etatC;
    @FXML
    private GridPane pnreservation;
    @FXML
    private TableView<Reservation> tabR;
    @FXML
    private TableColumn<Reservation, String> IdR;
    @FXML
    private TableColumn<Reservation, String> nomClientR;
    @FXML
    private TableColumn<Reservation, Integer> nbnuitR;
    @FXML
    private TableColumn<Reservation, String> DateAR;
    @FXML
    private TableColumn<Reservation, String> DateSR;
    @FXML
    private TableColumn<Reservation, String> NchambreR;
    @FXML
    private TableColumn<Reservation, String> formuleR;
    @FXML
    private DatePicker datedebutreche;
    @FXML
    private DatePicker dateFinReche;
    @FXML
    private Button btnRechercheResev;
    @FXML
    private TextField txtRechercheresev;
    @FXML
    private Button btnAjoutReser;
    @FXML
    private Button btnModifierRe;
    @FXML
    private Button btnSupprimerRESER;
    @FXML
    private GridPane pnFacturation;
    @FXML
    private TableView<factury> tabFactres;
    @FXML
    private TableColumn<factury,String> FidC;
    @FXML
    private TableColumn<factury,String> FnomC;
    @FXML
    private TableColumn<factury,String> FnChambreC;
    @FXML
    private TableColumn<factury,String> FdateAC;
    @FXML
    private TableColumn<factury,String> FdateDC;
    @FXML
    private TableColumn<factury,String> FmodePaieC;
    @FXML
    private TableColumn<factury,Integer> FremiseC;
    @FXML
    private TableColumn<factury,Integer> FtotalC;
    @FXML
    private Button btnEditerFacture;
    @FXML
    private TextField TxtRechercheFact;
    @FXML
    private Button btnRechercheFact;
     private ObservableList<Client> data1;
    private ObservableList<Reservation> reser;
    private ObservableList<Chambre> chambreO;
    private ObservableList<Facture> FactureO;
    private ObservableList<ArchiveClient> data_ArchiveClient;
    @FXML
    private TableColumn<ArchiveClient, String> Archive_ClientNom;
    @FXML
    private TableColumn<ArchiveClient, String> Archive_ClientDateE;
    @FXML
    private TableColumn<ArchiveClient, String> Archive_ClientDateS;
    @FXML
    private TableColumn<ArchiveClient, String> Archive_ClientChambre;
    @FXML
    private ImageView imageEmploye;
    @FXML
    private ImageView imageParametre;
    @FXML
    private ImageView imageCalendrier;
    @FXML
    private JFXButton btnStatistiques;
    @FXML
    private GridPane pnStatistiques;
    @FXML
    private BarChart<?, ?> grapheFinance;
    int a=0,b=0;
    @FXML
    private Label MoisMax;
    @FXML
    private ImageView imagePoste;
    @FXML
    private TableView<factury> tabFact;
    ObservableList<factury> grand ;

    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        // TODO
            imageCalendrier.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imagePoste.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(1);
            dc = new BdConnection();
             pnClient.setOpacity(0);
             pnArchive.setOpacity(0);
             pnPoste.setOpacity(0);
             pnFinance.setOpacity(0);
             pnStatistiques.setOpacity(0);
             pnCalendrier.setOpacity(0);
             pnParametre.setOpacity(0);
             comboBox.setPromptText("Choisir un champ");
             ChoixRC.getItems().addAll("Nom","Id","Adresse");
             comboBox.getItems().addAll(
                    "nom",
                    "prenom",
                    "idEmploye",
                    "Poste"
            );
            ChoixArchives.getItems().addAll(
                    "Employes",
                    "Clients"
            );
            ChoixTypeArchiveEmploye.getItems().addAll(
                    "Nom",
                    "Poste",
                    "dateEmbauche"
            );
            choixTypeArchiveClient.getItems().addAll(
                    "Nom",
                    "durée Séjour"
            );
            pnEmployes.toFront();
            pnEmployes.setOpacity(1);
            System.out.println(LoginController.nom);
            lblNomEmploye.setText(LoginController.nom);
            
               try {
            Connection conn=dc.connect();
            data = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            System.out.println("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            int i=0;
            while(rs.next())
            {
                data.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnIdEmploye.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
                columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnPoste.setCellValueFactory(new PropertyValueFactory<>("poste"));
                columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tableUser.setItems(data);
            }     
           } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
          dc=new BdConnection();
                Connection con1=dc.connect();
        try {
            ResultSet rs=con1.createStatement().executeQuery("select idPoste from poste");
            while(rs.next()) 
              {
                  ArchivePoste_Box.getItems().addAll(rs.getString(1));
              }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        idEmploye.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("idEmploye"));
        tacheDesc.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tacheDesc"));
        tache.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tache"));
        jour.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("jour"));
        horaire.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("horaire"));
        ArrayList<EmployeTravail> travail = new ArrayList<>();
          ModifierPlaningController.chargeDriver();
        
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM jouremployetravail;");
   
    while ( resultat.next() ) {
         travail.add(new EmployeTravail(resultat.getString("idEmploye"), resultat.getString(2), resultat.getString(3), resultat.getString("jour"), resultat.getString("horaire")));
         
    /* Traiter ici les valeurs récupérées. */
    }
     for(int i =0; i < travail.size(); i++){
       // System.out.println(travail.get(i).getTacheDesc());
        //table.getItems().add(travail.get(i));
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
          /* BdConnection bd = new BdConnection();
          Connection con = bd.connect();
          ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("select dateEntree from facture;");
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ArrayList mois=new ArrayList<String>();
            mois.add("Janvier");
            mois.add("Fevrier");
            mois.add("Mars");
            mois.add("Avril");
            mois.add("Mai");
            mois.add("Juin");
            mois.add("Juillet");
            mois.add("Août");
            mois.add("Septembre");
            mois.add("Octobre");
            mois.add("Novembre");
            mois.add("Decembre");
            double[] nbFactures=new double[12];
            for(int i=0;i<12;i++) nbFactures[i]=0;
        try {
            while(rs.next())
            {   
                for(int i=0;i<12;i++)
                {
                    if(rs.getDate("dateEntree").getMonth()==i) nbFactures[i]++;
                }
                
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    XYChart.Series series=new XYChart.Series();
                    for(int i=0;i<12;i++)
                        {
                            series.getData().add(new XYChart.Data(mois.get(i), nbFactures[i]) );
                        }
              grapheFinance.getData().addAll(series);
              a++;*/
    }    
    @FXML
    private void handleClicks(ActionEvent event) throws SQLException{
        
            BdConnection bd = new BdConnection();
        
        if(event.getSource()==btnEmploye)
        {   imageCalendrier.setOpacity(0);
            imageParametre.setOpacity(0);
            imagePoste.setOpacity(0);
            imageEmploye.setOpacity(1);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
           a--;
           b=a;
           grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            lblStatus.setText("Employés");
            pnArchive.setOpacity(0);
            pnClient.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            pnPoste.setOpacity(0);
            pnFinance.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnEmployes.toFront();
            pnEmployes.setOpacity(1);
            System.out.println("========+++ "+NomUtilisateur.getNom());
            lblNomEmploye.setText(LoginController.nom);
            
             try {
            Connection conn=dc.connect();
            data = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            System.out.println("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            int i=0;
            while(rs.next())
            {
                data.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnIdEmploye.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
                columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnPoste.setCellValueFactory(new PropertyValueFactory<>("poste"));
                columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tableUser.setItems(data);
            }     
           } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
        
         else 
            if(event.getSource()==btnClient)
        {   
            imagePoste.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(0);
            imageCalendrier.setOpacity(0);
            imagereception.setOpacity(1);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            lblStatus.setText("Clients");
            pnEmployes.setOpacity(0);
            pnPoste.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnParametre.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFinance.setOpacity(0);
            pnArchive.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnreservation.setOpacity(0);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnClient.toFront();
            pnClient.setOpacity(1);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            
            int i = 0;

            try {

                /*String url1 = "jdbc:mysql://localhost:3306/projet_bd?Datetimecode=false&&serverTimezone=UTC";
                 String user = "root";
                 String password = "dcsuperheroesgirls20020102#()";

                 Class.forName("com.mysql.cj.jdbc.Driver");*/
                //Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Open database successfully");
                //con = DriverManager.getConnection(url1, user, password);
                Connection con = bd.connect();
                System.out.println("Open database successfully");
                ResultSet rs;/* comboBox.getValue()*/

                rs = con.createStatement().executeQuery("SELECT * FROM Client" + ";");
                data1 = FXCollections.observableArrayList();
                while (rs.next()) {
                    data1.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
                    i++;
                }

                rs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println("Operation donne successfully");
            if (i != 0) {
                IdC.setCellValueFactory(new PropertyValueFactory<>("ID"));
                nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
                sexeC.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
                EmailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
                tab.setItems(data1);
            } else {

                tab.setItems(null);

            }
            
            
        }
         else 
            if(event.getSource()==btnChambre)
        {   
            imagePoste.setOpacity(0);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(1);
            imageCalendrier.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(0);
            pnStatistiques.setOpacity(0);
            lblStatus.setText("Chambres");
            pnArchive.setOpacity(0);
            pnClient.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            pnEmployes.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnFinance.setOpacity(0);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnCalendrier.setOpacity(0);
            pnChambre.toFront();
            pnChambre.setOpacity(1);
            btnSupprimerC.setOpacity(1);
            btnSupprimerC.setDisable(false);
            btnAjoutChambre.setOpacity(1);
            btnAjoutChambre.setDisable(false);
            btnModifierC.setOpacity(1);
            btnModifierC.setDisable(false);
            int k = 0;
            Connection c = bd.connect();

            try {
                ResultSet rc = c.createStatement().executeQuery("SELECT idChambre,description_chambre,prix,type,etat FROM chambre;");
                chambreO = FXCollections.observableArrayList();
                while (rc.next()) {

                    //Chambre ch = new Chambre(null, k, null, null, k);
                    chambreO.add(new Chambre(rc.getString(1), rc.getString(2), rc.getInt(3), rc.getString(4), rc.getInt(5)));
                    System.out.println(rc.getString(1));
                    k++;
                }
                // rc.close();
                // c.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println(k);
            if (k != 0) {

                nChambre.setCellValueFactory(new PropertyValueFactory<>("id"));
                descriptionc.setCellValueFactory(new PropertyValueFactory<>("description"));
                prixChambre.setCellValueFactory(new PropertyValueFactory<>("prix"));
                typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
                etatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
//                Chambre cd = chambreO.get(1);
               // System.out.println(cd.getId());

                tabC.setItems(chambreO);
            }

        }
         else 
            if(event.getSource()==btnCalendrier)
        {   
            imagePoste.setOpacity(0);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            pnStatistiques.setOpacity(0);
            imageEmploye.setOpacity(0);
            imageCalendrier.setOpacity(1);
            lblStatus.setText("Calendrier");
             pnArchive.setOpacity(0);
            pnClient.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            pnEmployes.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnFinance.setOpacity(0);
            pnCalendrier.toFront();
            pnCalendrier.setOpacity(1);
           
        }
         else 
            if(event.getSource()==btnArchives)
        {   
            imagePoste.setOpacity(0);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(0);
            pnStatistiques.setOpacity(0);
            imageCalendrier.setOpacity(0);
            lblStatus.setText("Archives");
            pnClient.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnEmployes.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFinance.setOpacity(0);
            pnArchive.toFront();
            pnArchive.setOpacity(1);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
                               
        }
         else 
            if(event.getSource()==btnFinances)
        {   
            imagePoste.setOpacity(0);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(0);
            pnStatistiques.setOpacity(0);
            imageCalendrier.setOpacity(0);
            lblStatus.setText("Finances");
             pnEmployes.setOpacity(0);
            pnClient.setOpacity(0);
            pnArchive.setOpacity(0);
            pnParametre.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnPoste.setOpacity(0);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnChambre.setOpacity(0);
            pnFinance.toFront();
            pnFinance.setOpacity(1);
        }
         else 
            if(event.getSource()==btnPostes)
        {   imagePoste.setOpacity(1);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageEmploye.setOpacity(0);
            imageCalendrier.setOpacity(0);
            lblStatus.setText("Postes");
             pnEmployes.setOpacity(0);
            pnClient.setOpacity(0);
            pnArchive.setOpacity(0);
            pnParametre.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnCalendrier.setOpacity(0);
             pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnFinance.setOpacity(0);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnChambre.setOpacity(0);
            pnPoste.toFront();
            pnPoste.setOpacity(1);
            
              try {
            Connection conn=dc.connect();
            data_Poste= FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select * from poste;");
            System.out.println("select * from poste;");
            int i=0;
            while(rs.next())
            {
                data_Poste.add(new Poste(rs.getString(1), rs.getString(2),rs.getInt(3)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnPoste_idPoste.setCellValueFactory(new PropertyValueFactory<>("idPoste"));
                columnPoste_description.setCellValueFactory(new PropertyValueFactory<>("description_Poste"));
                columnPoste_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
                tablePoste.setItems(data_Poste);
            }     
           } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        }
         else 
            if(event.getSource()==btnParametres)
        {   
            imagePoste.setOpacity(0);
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(1);
            imageEmploye.setOpacity(0);
            imageCalendrier.setOpacity(0);
            lblStatus.setText("Parametres");
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            pnEmployes.setOpacity(0);
            pnClient.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnArchive.setOpacity(0);           
            pnPoste.setOpacity(0);
            pnParametre.toFront();
            pnParametre.setOpacity(1);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            lblNomEmploye.setText(LoginController.nom);
        }
        else
                if(event.getSource()==btnFacturation)
                {
                        imagePoste.setOpacity(0);
                        imagereception.setOpacity(0);
            imageFacture.setOpacity(1);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            lblStatus.setText("Facturation");
            pnEmployes.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFinance.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnArchive.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnFacturation.toFront();
            pnClient.setOpacity(0);
            pnFacturation.setOpacity(1);
            a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnreservation.setOpacity(0);
                        int u=0;
                        grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            Connection con = bd.connect();
          /* ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT * FROM facture  ; ");
        FactureO = FXCollections.observableArrayList();
       while (rs.next()) {
            ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(2) + "';");
            while (r.next()) {
                    u++;
                    System.out.println(r.getString("nom"));
                    FactureO.add(new Facture(rs.getString(7), r.getString("nom"), rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
                
            }
        }
        if (u != 0) {
            FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
            FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
            FdateAC.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            FdateDC.setCellValueFactory(new PropertyValueFactory<>("DateS"));
            FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
            FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
            FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

            tabFactres.setItems(FactureO);
        } else {
            tabFactres.setItems(null);
        }*/
            
             ResultSet r;
            ResultSet rs;
            //r = con.createStatement().executeQuery("SELECT * FROM client ; ");

            int l = 0;
            rs = con.createStatement().executeQuery("select * from client natural join facture;");
            System.out.println(rs.getRow());
            grand = FXCollections.observableArrayList();
            grand.clear();
           
            while (rs.next()) {
                l++;
                factury fact = new factury();
                fact.setId(rs.getString(13));
                fact.setNom(rs.getString(2));
                fact.setNchambre(rs.getString(8));
                fact.setDA(rs.getString(9));
                fact.setDS(rs.getString(10));
                fact.setModepaie(rs.getString(11));
                fact.setRemise(rs.getInt(14));
                fact.setTotal(rs.getInt(12));
                grand.add(fact);
                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString(5));
                //FactureO.add(new Facture(rs.getString(13), rs.getString(2), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(14), rs.getInt(12)));
            }
           ObservableList<Facture> O;
           O=FactureO;
            if (l != 0) {
                FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
                FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
                FdateAC.setCellValueFactory(new PropertyValueFactory<>("DA"));
                FdateDC.setCellValueFactory(new PropertyValueFactory<>("DS"));
                FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
                FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
                FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

                tabFactres.setItems(grand);
            } else {

                tabFactres.setItems(null);

            }

                }
               else
                if(event.getSource()==btnReservation)
                {   
                        imagePoste.setOpacity(0);
                        imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(1);
            lblStatus.setText("Reservation");
            pnEmployes.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnPoste.setOpacity(0);
            a--;
            b=a;
            pnParametre.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFinance.setOpacity(0);
            pnArchive.setOpacity(0);
            pnStatistiques.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnreservation.toFront();
            pnClient.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(1);
            Connection con = bd.connect();
            int j = 0;
            int i = 0;
            System.out.println("Open database successfully");

            try {
                // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

                String idRe;

                ResultSet rs;/* comboBox.getValue()*/

                rs = con.createStatement().executeQuery("SELECT * FROM reservation ;");
                reser = FXCollections.observableArrayList();
                while (rs.next()) {
                    ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                    while (r.next()) {
                        System.out.println(r.getString("nom"));

                        reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                        i++;
                    }

                }

                rs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println("Operation donne successfully");
            if (i != 0) {

                nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
                NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
                DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
                DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
                formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
                IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

                tabR.setItems(reser);
            } else {

                tab.setItems(null);

            }
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
                b=a;
                }
        else
                    
                if(event.getSource()==btnStatistiques)
                {   
                    imagePoste.setOpacity(0);
                     imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageEmploye.setOpacity(0);
            lblStatus.setText("Statistiques");
            pnEmployes.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnPoste.setOpacity(0);
            pnParametre.setOpacity(0);
            pnChambre.setOpacity(0);
            pnFinance.setOpacity(0);
            pnArchive.setOpacity(0);
            pnCalendrier.setOpacity(0);
            pnStatistiques.toFront();
            pnStatistiques.setOpacity(1);
            pnClient.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnreservation.setOpacity(0);
            
            a++;
          if((b+1>=a)&&(a>=b))   
          {Connection con = bd.connect();
            ResultSet rs=con.createStatement().executeQuery("select dateEntree from facture;");
            ArrayList mois=new ArrayList<String>();
            mois.add("Janvier");
            mois.add("Fevrier");
            mois.add("Mars");
            mois.add("Avril");
            mois.add("Mai");
            mois.add("Juin");
            mois.add("Juillet");
            mois.add("Août");
            mois.add("Septembre");
            mois.add("Octobre");
            mois.add("Novembre");
            mois.add("Decembre");
            double[] nbFactures=new double[12];
            for(int i=0;i<12;i++) nbFactures[i]=0;
            double Max=nbFactures[0]; int max=0;
            while(rs.next())
            {   System.out.println(rs.getDate("dateEntree").getMonth());
                for(int i=0;i<12;i++)
                {
                    if(rs.getDate("dateEntree").getMonth()==i) {
                        nbFactures[i]++;
                        if(nbFactures[i]>Max) {
                            Max=nbFactures[i];
                            max=i;
                        }
                    }
                    System.out.println(mois.get(i)+" ++++++++++++  "+nbFactures[i]);
                }
                
                
            }
                rs.close();
                con.close();
                MoisMax.setText((String) mois.get(max));
                    XYChart.Series series=new XYChart.Series();
                    for(int i=0;i<12;i++)
                        {
                            series.getData().add(new XYChart.Data(mois.get(i), nbFactures[i]) );
                        }
                    grapheFinance.getData().addAll(series);
                }
           }
    }

    static Stage stage3;
    @FXML
    private void ajoutEmploye(ActionEvent event) throws IOException {
        stage3=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("AjoutEmploye.fxml"));
        Scene scene=new Scene(root);
        stage3.setScene(scene);
        stage3.initStyle(StageStyle.UNDECORATED);
        stage3.showAndWait();       
    }

    static Stage stage2;
    @FXML
    private void rechercheEmploye(ActionEvent event) throws IOException {
         stage2=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("RechercheEmploye.fxml"));
        Scene scene=new Scene(root);
        stage2.setScene(scene);
        stage2.initStyle(StageStyle.UNDECORATED);
        stage2.showAndWait();
    }


    @FXML
    private void rechercheRapideEmploye(ActionEvent event) throws IOException {
        
                     
        System.out.println("SELECT idEmploye,nom,prenom,adresse,idPoste FROM Employe natural join EmployePoste where id"+comboBox.getValue() +"=" + "'" +FieldRechercheEmploye.getText()+ "';");
        Connection con=dc.connect() ;
        int i=0;

        try {
            
            System.out.println("Open database successfully");
            ResultSet rs;/* comboBox.getValue()*/
            if(comboBox.getValue()=="Poste")  rs=con.createStatement().executeQuery("SELECT idEmploye,nom,prenom,adresse,idPoste FROM Employe natural join employeposte where id"+comboBox.getValue()+ "=" + "'" +FieldRechercheEmploye.getText()+ "';");
            
            else  rs=con.createStatement().executeQuery("SELECT idEmploye,nom,prenom,adresse,idPoste FROM Employe natural join employeposte where "+comboBox.getValue()+ "=" + "'" +FieldRechercheEmploye.getText()+ "';");
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                i++;
            }
           
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println("Operation donne successfully");
  
        if(i!=0){
               
                columnIdEmploye.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
                columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnPoste.setCellValueFactory(new PropertyValueFactory<>("poste"));
                columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tableUser.setItems(data);
        }
        else {
            
            tableUser.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
        }
             
    }

    @FXML
    private void choixArchive(ActionEvent event) {
            if (ChoixArchives.getValue().toString().equalsIgnoreCase("Employes"))
            {
              pnArchiveEmploye.toFront();
              pnArchiveEmploye.setOpacity(1);
              pnArchiveEmploye2.setOpacity(1);
              pnArchiveClient.toBack();
              pnArchiveClient.setOpacity(0);
              
              
            }
            if (ChoixArchives.getValue().toString().equalsIgnoreCase("Clients"))
            {
              pnArchiveClient.toFront();
              pnArchiveClient.setOpacity(1);
              pnArchiveClient2.setOpacity(1);
              pnArchiveEmploye.setOpacity(0);
              pnArchiveEmploye.toBack();
              
            }
  
        
    }

    @FXML
    private void choixArchiveEmploye(ActionEvent event) throws SQLException {
         if(ChoixTypeArchiveEmploye.getValue().toString().equalsIgnoreCase("Nom"))
              {
                  pnArchiveEmployeDate.toBack();
                  pnArchiveEmployeDate.setOpacity(0);
                  pnArchiveEmployePoste.toBack();
                  pnArchiveEmployePoste.setOpacity(0);
                  pnArchiveEmployeNom.toFront();
                  pnArchiveEmployeNom.setOpacity(1);
                  
            Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   ;");
            System.out.println("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   ;");
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));                
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
              }
              if(ChoixTypeArchiveEmploye.getValue().toString().equalsIgnoreCase("dateEmbauche"))
              {
                  pnArchiveEmployeNom.toBack();
                  pnArchiveEmployeNom.setOpacity(0);
                  pnArchiveEmployePoste.toBack();
                  pnArchiveEmployePoste.setOpacity(0);
                  pnArchiveEmployeDate.toFront();
                  pnArchiveEmployeDate.setOpacity(1);
                  Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   ;");
            System.out.println("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   ;");
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));                
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
              }
               if(ChoixTypeArchiveEmploye.getValue().toString().equalsIgnoreCase("Poste"))
              {
                  pnArchiveEmployeNom.toBack();
                  pnArchiveEmployeNom.setOpacity(0);
                  pnArchiveEmployeDate.toBack();
                  pnArchiveEmployeDate.setOpacity(0);
                  pnArchiveEmployePoste.toFront();
                  pnArchiveEmployePoste.setOpacity(1);
                 // select * from archiveemploye inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye;
                       Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye  inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye ;");
            System.out.println("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye  ;");
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));                
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
              }
              
    
    }

    @FXML
    private void choixArchivClient(ActionEvent event) throws SQLException {
         if(choixTypeArchiveClient.getValue().toString().equalsIgnoreCase("Nom"))
              {
                  pnArchiveClientDate.toBack();
                  pnArchiveClientDate.setOpacity(0);
                  pnArchiveClientNom.toFront();
                  pnArchiveClientNom.setOpacity(1);
                  String nom=RechercheNomClient.getText();
        
        BdConnection bd= new BdConnection();
        Connection con=bd.connect();
        ResultSet rs= con.createStatement().executeQuery("select * from archiveclient ;");
        data_ArchiveClient = FXCollections.observableArrayList();
        int i=0;
            while(rs.next())
            {
                data_ArchiveClient.add(new ArchiveClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                Archive_ClientNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                Archive_ClientChambre.setCellValueFactory(new PropertyValueFactory<>("idChambre"));
                Archive_ClientDateE.setCellValueFactory(new PropertyValueFactory<>("dateE"));                
                Archive_ClientDateS.setCellValueFactory(new PropertyValueFactory<>("dateS"));
                archiveTableClient.setItems(data_ArchiveClient);
            }
              }
              if(choixTypeArchiveClient.getValue().toString().equalsIgnoreCase("durée Séjour"))
              {
                  pnArchiveClientNom.toBack();
                  pnArchiveClientNom.setOpacity(0);
                  pnArchiveClientDate.toFront();
                  pnArchiveClientDate.setOpacity(1);
                  String d1=ClientDate1.getValue().toString();
        String d2=ClientDate2.getValue().toString();
        
         BdConnection bd= new BdConnection();
        Connection con=bd.connect();
        ResultSet rs= con.createStatement().executeQuery("select * from archiveclient ;");
        data_ArchiveClient = FXCollections.observableArrayList();
        int i=0;
            while(rs.next())
            {
                data_ArchiveClient.add(new ArchiveClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                Archive_ClientChambre.setCellValueFactory(new PropertyValueFactory<>("idChambre"));
                Archive_ClientDateE.setCellValueFactory(new PropertyValueFactory<>("dateE"));                
                Archive_ClientDateS.setCellValueFactory(new PropertyValueFactory<>("dateS"));
                archiveTableClient.setItems(data_ArchiveClient);
            }
              }
    }

    @FXML
    private void btnDeconnexion(ActionEvent event) throws IOException {
         
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();

        
    }

    @FXML
    private void RechercherEmployeNom(ActionEvent event) {
        //ArchivePoste_Box.setValue(null);
        
        try {
            Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idPoste,dateEmbauche,pieceIdentite from archiveemploye   where nom='" + RechercheEmployeNom_box.getText() + "';");
            System.out.println("select nom,prenom,sexe,idPoste,dateEmbauche,pieceIdentite from archiveemploye   where nom= '" + RechercheEmployeNom_box.getText() + "';");
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));                
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
            
            else {
                table_ArchiveEmploye.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    static Stage stage5;
    @FXML
    private void ajouterPoste(ActionEvent event) throws IOException {
        stage5=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("AjoutPoste.fxml"));
        Scene scene=new Scene(root);
        stage5.setScene(scene);
        stage5.initStyle(StageStyle.UNDECORATED);
        stage5.showAndWait();
    }

    @FXML
    private void actualiserPoste(ActionEvent event) {
        
          try {
            Connection conn=dc.connect();
            data_Poste= FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select * from poste;");
            System.out.println("select * from poste;");
            int i=0;
            while(rs.next())
            {
                data_Poste.add(new Poste(rs.getString(1), rs.getString(2),rs.getInt(3)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnPoste_idPoste.setCellValueFactory(new PropertyValueFactory<>("idPoste"));
                columnPoste_description.setCellValueFactory(new PropertyValueFactory<>("description_Poste"));
                columnPoste_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
                tablePoste.setItems(data_Poste);
            }     
           } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void RechercherEmployePoste(ActionEvent event) {
        try {
            Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye   where idPoste=" + "'" + ArchivePoste_Box.getValue() + "';");
            System.out.println("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye   where idPoste=" + "'" + ArchivePoste_Box.getValue()+ "';");
            //select * from archiveemploye inner join employeposte on archiveemploye.`idEmp` = employeposte.idEmploye;
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));                
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
            
            else {
                table_ArchiveEmploye.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    static Stage stage1;
    @FXML
    private void changePassword(ActionEvent event) throws IOException {
        /*Stage*/ stage1=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("ModifierPassword.fxml"));
        Scene scene=new Scene(root);
        stage1.setScene(scene);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.showAndWait();
        
        
    }

    @FXML
    private void actualiserEmploye(ActionEvent event) {
    
           FieldRechercheEmploye.clear();
             try {
            Connection conn=dc.connect();
            data = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            System.out.println("select idEmploye,nom,prenom,adresse,idPoste from Employe natural join employePoste;");
            int i=0;
            while(rs.next())
            {
                data.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnIdEmploye.setCellValueFactory(new PropertyValueFactory<>("idEmploye"));
                columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnPoste.setCellValueFactory(new PropertyValueFactory<>("poste"));
                columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                tableUser.setItems(data);
            }     
           } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    static Stage stage4;
    @FXML
    private void supprimerEmploye(ActionEvent event) throws IOException {
        stage4=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("SuprrimerEmploye.fxml"));
        Scene scene=new Scene(root);
        stage4.initStyle(StageStyle.UNDECORATED);
        stage4.setScene(scene);
        stage4.showAndWait();
    }

    @FXML
    private void RechercherEmployeDate(ActionEvent event) {
        try {
            Connection conn=dc.connect();
            data_ArchiveEmploye = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   where dateEmbauche>=" + "'" + EmployeDate1.getValue() + "';");
            System.out.println("select nom,prenom,sexe,idEmp,dateEmbauche,pieceIdentite from archiveemploye   where dateEmbauche>=" + "'" + EmployeDate1.getValue() + "';");
            int i=0;
            while(rs.next())
            {
                data_ArchiveEmploye.add(new Employe(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                i++;
                System.out.println(rs.getString(6));
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                columnArchiveEmploye_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                columnArchiveEmploye_Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                columnArchiveEmploye_Poste.setCellValueFactory(new PropertyValueFactory<>("poste"));
                columnArchiveEmploye_date.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
                columnArchiveEmploye_pieceIdentite.setCellValueFactory(new PropertyValueFactory<>("pieceIdentite"));
                table_ArchiveEmploye.setItems(data_ArchiveEmploye);
            }
            
            else {
                table_ArchiveEmploye.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficherFinance(ActionEvent event) {
        try {
            Connection conn=dc.connect();
            data_Finance = FXCollections.observableArrayList();
            
            ResultSet rs = conn.createStatement().executeQuery("select idfacture,idChambre,total,remise from facture where dateEntree>= '"+dateEntre.getValue()+"' and dateSortie<= '"+dateSortie.getValue()+"';");
            System.out.println("select idfacture,idChambre,total,remise from facture where dateEntree>= '"+dateEntre.getValue()+"' and dateSortie<= '"+dateSortie.getValue()+"';");
            int S =0;
            int i=0;
            while(rs.next())
            {
                data_Finance.add(new Finance(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4)));
                S=S+rs.getInt(3);
                i++;
            }
            String Somme=String.valueOf(S);
            System.out.println(Somme);
            rs.close();
            
            if(i!=0)
            {
                columnFinance_IdFacture.setCellValueFactory(new PropertyValueFactory<>("idFacture"));
                columnFinance_IdChambre.setCellValueFactory(new PropertyValueFactory<>("idChambre"));
                columnFinance_Remise.setCellValueFactory(new PropertyValueFactory<>("remise"));
                columnFinance_total.setCellValueFactory(new PropertyValueFactory<>("total"));                
                tableFinance.setItems(data_Finance);
                Recette.setText(Somme+"FCFA");
                Recette.setOpacity(1);
            }
            
            else {
                table_ArchiveEmploye.setItems(null);
                Recette.setText("0 FCFA");
                Recette.setOpacity(1);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "Oups!!! AUCUNE ENTREE DISPONIBLE", "Alert",
JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void refresh(ActionEvent event) {
         ArrayList<EmployeTravail> travail = new ArrayList<>();
    //table.getItems().remove(0, table.getItems().size()-1);
    list.clear();
    
        try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
    Statement statement = connexion.createStatement();
    resultat = statement.executeQuery( "SELECT * FROM jouremployetravail;");
   
    while ( resultat.next() ) {
         travail.add(new EmployeTravail(resultat.getString("idEmploye"), resultat.getString(2), resultat.getString(3), resultat.getString("jour"), resultat.getString("horaire")));
    /* Traiter ici les valeurs récupérées. */
    }
    for(int i =0; i < travail.size(); i++){
        list.add(travail.get(i));
        //table.getItems().add(travail.get(i));
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

    @FXML
    private void ajouterPlaning(ActionEvent event) {
         try {
            Stage stage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("AjoutPlaning.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierPlaning(ActionEvent event) {
        try {
            Stage stage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("ModifierPlaning.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerPlanning(ActionEvent event) {
        try {
            Stage stage=new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("SupprimerPlaning.fxml"));
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilDirecteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RechercheClient(ActionEvent event) throws SQLException {
       BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        if (ChoixRC.getValue().equals("Nom")) {
            //txtRechercheClient.setPromptText("Entrez le nom du client");
            int i = 0;
            System.out.println("Open database successfully");
            ResultSet rs;

            rs = con.createStatement().executeQuery("SELECT * FROM client" + ";");
            data1 = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(2).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data1.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
                    i++;
                }
            }

            rs.close();
            con.close();
            System.out.println("Operation donne successfully");
            if (i != 0) {
                IdC.setCellValueFactory(new PropertyValueFactory<>("ID"));
                nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
                sexeC.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
                EmailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
                tab.setItems(data1);
            } else {

                tab.setItems(null);

            }
        }

        if (ChoixRC.getValue().equals("Adresse")) {
            int i = 0;
            //txtRechercheClient.setPromptText("Entrez l'adresse du client");
            System.out.println("Open database successfully");
            ResultSet rs;

            rs = con.createStatement().executeQuery("SELECT * FROM Client" + ";");
            data1 = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(3).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data1.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
                    i++;
                }
            }

            rs.close();
            con.close();
            System.out.println("Operation donne successfully");
            if (i != 0) {
                IdC.setCellValueFactory(new PropertyValueFactory<>("ID"));
                nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
                sexeC.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
                EmailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
                tab.setItems(data1);
            } else {

                tab.setItems(null);

            }

        }
        if (ChoixRC.getValue().equals("Id")) {
            int i = 0;
            //txtRechercheClient.setPromptText("Entrez l'ID du client");
            System.out.println("Open database successfully");
            ResultSet rs;

            rs = con.createStatement().executeQuery("SELECT * FROM Client" + ";");
            data1 = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(1).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data1.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
                    i++;
                }
            }

            rs.close();
            con.close();
            System.out.println("Operation donne successfully");
            if (i != 0) {
                IdC.setCellValueFactory(new PropertyValueFactory<>("ID"));
                nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
                sexeC.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
                EmailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
                tab.setItems(data1);
            } else {

                tab.setItems(null);

            }

        
    }
    }

    @FXML
    private void Choisir(ActionEvent event) {
        if (ChoixRC.getValue().equals("Nom")) {
            txtRechercheClient.setPromptText("Entrez le nom du client");
        }
        if (ChoixRC.getValue().equals("Id")) {
            txtRechercheClient.setPromptText("Entrez l'Id du client");
        }
        if (ChoixRC.getValue().equals("Adresse")) {
            txtRechercheClient.setPromptText("Entrez l'Adresse du client");
        }
    }

    @FXML
    private void AjoutClient(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("FicheClient.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();

    }

    @FXML
    private void ModifierClient(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLMClient.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();
    
    }

    @FXML
    private void Supprimerclient(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLSClient.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);

        //root_stage.hide();
        root_stage.showAndWait();


    }

    @FXML
    private void AjouterChambre(ActionEvent event) throws IOException {
         Parent FicheP;
        FicheP = FXMLLoader.load(getClass().getResource("AjoutChambre.fxml"));
        Scene FicheP_scene = new Scene(FicheP);
        Stage FicheP_stage = new Stage(); 
        FicheP_stage.hide();
        FicheP_stage.initStyle(StageStyle.UTILITY);
        FicheP_stage.setScene(FicheP_scene);
        FicheP_stage.showAndWait();
    }

    @FXML
    private void ModifierC(ActionEvent event) throws IOException {
         Parent FicheP;
        FicheP = FXMLLoader.load(getClass().getResource("ModifierChambre.fxml"));
        Scene FicheP_scene = new Scene(FicheP);
        Stage FicheP_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        FicheP_stage.hide();
        FicheP_stage.initStyle(StageStyle.UTILITY);
        FicheP_stage.setScene(FicheP_scene);
        FicheP_stage.showAndWait();

    }

    @FXML
    private void SupprimerC(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("SupprimerChambre.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);

        //root_stage.hide();
        root_stage.showAndWait();

    }

    @FXML
    private void listeChambre(ActionEvent event) {
        int k = 0;
         BdConnection bd = new BdConnection();
        Connection c = bd.connect();

        try {
            ResultSet rc = c.createStatement().executeQuery("SELECT idChambre,description_chambre,prix,type,etat FROM chambre WHERE etat ='0';");
            chambreO = FXCollections.observableArrayList();
            while (rc.next()) {

                //Chambre ch = new Chambre(null, k, null, null, k);
                chambreO.add(new Chambre(rc.getString(1), rc.getString(2), rc.getInt(3), rc.getString(4), rc.getInt(5)));
                System.out.println(rc.getString(1));
                k++;
            }
            // rc.close();
            // c.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println(k);
        if (k != 0) {

            nChambre.setCellValueFactory(new PropertyValueFactory<>("id"));
            descriptionc.setCellValueFactory(new PropertyValueFactory<>("description"));
            prixChambre.setCellValueFactory(new PropertyValueFactory<>("prix"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            etatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
            Chambre cd = chambreO.get(1);
            System.out.println(cd.getId());

            tabC.setItems(chambreO);
        } else {
            tabC.setItems(null);
        }
    }

    @FXML
    private void RechercheNchambre(ActionEvent event) {
        BdConnection bd = new BdConnection();
        int k = 0;
        Connection c = bd.connect();

        try {
            ResultSet rc = c.createStatement().executeQuery("SELECT idChambre,description_chambre,prix,type,etat FROM chambre WHERE idChambre='" + RechercheCambre.getText() + "';");
            chambreO = FXCollections.observableArrayList();
            while (rc.next()) {

                //Chambre ch = new Chambre(null, k, null, null, k);
                chambreO.add(new Chambre(rc.getString(1), rc.getString(2), rc.getInt(3), rc.getString(4), rc.getInt(5)));
                System.out.println(rc.getString(1));
                k++;
            }
            // rc.close();
            // c.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println(k);
        if (k != 0) {

            nChambre.setCellValueFactory(new PropertyValueFactory<>("id"));
            descriptionc.setCellValueFactory(new PropertyValueFactory<>("description"));
            prixChambre.setCellValueFactory(new PropertyValueFactory<>("prix"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            etatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
            //Chambre cd = chambreO.get(1);
            //System.out.println(cd.getId());

            tabC.setItems(chambreO);
        } else {
            tabC.setItems(null);
        }
    }

    @FXML
    private void recherchedebutR(ActionEvent event) {
         BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        long j = datedebutreche.getValue().toEpochDay();
        //long k = dateFinReche.getValue().toEpochDay();
        System.out.println("Open database successfully");
        int l = 0;
        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

            rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE dateEntree='" + datedebutreche.getValue() + "' ;");
            reser = FXCollections.observableArrayList();
            while (rs.next()) {
                ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                while (r.next()) {
                    System.out.println(r.getString("nom"));

                    reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                    l++;
                }

            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        int i = l;
        System.out.println("Operation donne successfully");
        if (i != 0) {

            nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
            NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
            DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
            IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

            tabR.setItems(reser);
        } else {

            tab.setItems(null);

        }
    }

    @FXML
    private void rechercheFinR(ActionEvent event) {
        BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        //long j = datedebutreche.getValue().toEpochDay();
        long i = dateFinReche.getValue().toEpochDay();
        System.out.println("Open database successfully");

        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

            rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE dateSortie ='" + dateFinReche.getValue() + "' ;");
            reser = FXCollections.observableArrayList();
            while (rs.next()) {
                ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                while (r.next()) {
                    System.out.println(r.getString("nom"));

                    reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                    i++;
                    
                }

            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println("Operation donne successfully");
        if (i != 0) {

            nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
            NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
            DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
            IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

            tabR.setItems(reser);
        } else {

            tab.setItems(null);

        }
    }

    @FXML
    private void rechercheReservationDate(ActionEvent event) {
        BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        long j = datedebutreche.getValue().toEpochDay();
        long i = dateFinReche.getValue().toEpochDay();
        System.out.println("Open database successfully");

        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

            rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE dateEntree>='" + datedebutreche.getValue() + "'AND dateSortie<='" + dateFinReche.getValue() + "' ;");
            reser = FXCollections.observableArrayList();
            while (rs.next()) {
                ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                while (r.next()) {
                    System.out.println(r.getString("nom"));

                    reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                    i++;
                }

            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println("Operation donne successfully");
        if (i != 0) {

            nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
            NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
            DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
            IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

            tabR.setItems(reser);
        } else {

            tab.setItems(null);

        }
    }

    @FXML
    private void RechercheRnom(ActionEvent event) {
         BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        int j = 0;
        int i = 0;
        System.out.println("Open database successfully");

        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

            rs = con.createStatement().executeQuery("SELECT * FROM reservation ;");
            reser = FXCollections.observableArrayList();
            while (rs.next()) {
                ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                while (r.next()) {
                    if (r.getString("nom").toLowerCase().indexOf(txtRechercheresev.getText().toLowerCase()) != -1) {
                        System.out.println(r.getString("nom"));

                        reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                        i++;
                    }
                }

            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
            System.out.println("ERROR");
        }
        System.out.println("Operation donne successfully");
        if (i != 0) {

            nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
            NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
            DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
            formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
            IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

            tabR.setItems(reser);
        } else {

            tab.setItems(null);

        }
    }

    @FXML
    private void AjoutReser(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("EnregistrementPage.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();
    }

    @FXML
    private void ModifierRe(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLModifyReservation.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();

    }

    @FXML
    private void SupprimerReser(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLSupReservation.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();
    }

    @FXML
    private void EditerFacture(ActionEvent event) throws IOException {
        Parent FicheP;
        FicheP = FXMLLoader.load(getClass().getResource("FXMLFicheDePaiement.fxml"));
        Scene FicheP_scene = new Scene(FicheP);
        Stage FicheP_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        FicheP_stage.hide();
        FicheP_stage.initStyle(StageStyle.UTILITY);
        FicheP_stage.setScene(FicheP_scene);
        FicheP_stage.showAndWait();

    }

    @FXML
    private void listefactToday(ActionEvent event) throws SQLException {
         BdConnection bd = new BdConnection(); 
        Connection con = bd.connect();
        ResultSet r;
        ResultSet rs;
        LocalDate date = LocalDate.now();
        int l = 0;
        rs = con.createStatement().executeQuery("select * from facture NATURAL JOIN client WHERE dateSortie ='" + date + "';");
        System.out.println(rs.getRow());
        FactureO = FXCollections.observableArrayList();
        grand=FXCollections.observableArrayList();
        grand.clear();
        while (rs.next()) {
            l++;
              factury fact = new factury();
                fact.setId(rs.getString(13));
                fact.setNom(rs.getString(2));
                fact.setNchambre(rs.getString(8));
                fact.setDA(rs.getString(9));
                fact.setDS(rs.getString(10));
                fact.setModepaie(rs.getString(11));
                fact.setRemise(rs.getInt(14));
                fact.setTotal(rs.getInt(12));
                grand.add(fact);
            System.out.println(rs.getString("nom"));
            System.out.println(rs.getString(5));
            //FactureO.add(new Facture(rs.getString(8), rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
        }
        if (l != 0) {
            FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
            FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
            FdateAC.setCellValueFactory(new PropertyValueFactory<>("DA"));
            FdateDC.setCellValueFactory(new PropertyValueFactory<>("DS"));
            FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
            FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
            FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

            tabFactres.setItems(grand);
        } else {

            tabFactres.setItems(null);

        }
    }

    @FXML
    private void rechercheFact(ActionEvent event) throws SQLException {
        BdConnection bd = new BdConnection();
        Connection con = bd.connect();
        int k = 0;
        String nom = "bt";
        String prenom = " bravo";
        System.out.println(prenom.indexOf(nom));
        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT * FROM facture  ; ");
        FactureO = FXCollections.observableArrayList();
        grand = FXCollections.observableArrayList();
        grand.clear();
        while (rs.next()) {
            ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(2) + "';");
            while (r.next()) {
                if (r.getString("nom").toLowerCase().indexOf(TxtRechercheFact.getText().toLowerCase(Locale.FRENCH)) != -1) {
                    k++;
                      factury fact = new factury();
                fact.setId(rs.getString(13));
                fact.setNom(rs.getString(2));
                fact.setNchambre(rs.getString(8));
                fact.setDA(rs.getString(9));
                fact.setDS(rs.getString(10));
                fact.setModepaie(rs.getString(11));
                fact.setRemise(rs.getInt(14));
                fact.setTotal(rs.getInt(12));
                grand.add(fact);
                    System.out.println(r.getString("nom"));
                    FactureO.add(new Facture(rs.getString(8), r.getString("nom"), rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
                }
            }
        }
        if (k != 0) {
            FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
            FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
            FdateAC.setCellValueFactory(new PropertyValueFactory<>("DA"));
            FdateDC.setCellValueFactory(new PropertyValueFactory<>("DS"));
            FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
            FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
            FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

            tabFactres.setItems(grand);
        } else {
            tabFactres.setItems(null);
        }

    }

    @FXML
    private void RechercheClinetDate(ActionEvent event) throws SQLException {
        String d1=ClientDate1.getValue().toString();
        String d2=ClientDate2.getValue().toString();
        
         BdConnection bd= new BdConnection();
        Connection con=bd.connect();
        ResultSet rs= con.createStatement().executeQuery("select * from archiveclient where DateEntree>= '"+d1+"' and DateSortie<='"+d2+"';");
        data_ArchiveClient = FXCollections.observableArrayList();
        int i=0;
            while(rs.next())
            {
                data_ArchiveClient.add(new ArchiveClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                Archive_ClientChambre.setCellValueFactory(new PropertyValueFactory<>("idChambre"));
                Archive_ClientDateE.setCellValueFactory(new PropertyValueFactory<>("dateE"));                
                Archive_ClientDateS.setCellValueFactory(new PropertyValueFactory<>("dateS"));
                archiveTableClient.setItems(data_ArchiveClient);
            }
            
            else {
                archiveTableClient.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
            }
    }

    @FXML
    private void RechercheClientNom(ActionEvent event) throws SQLException {
        String nom=RechercheNomClient.getText();
        
        BdConnection bd= new BdConnection();
        Connection con=bd.connect();
        ResultSet rs= con.createStatement().executeQuery("select * from archiveclient where nom= '"+nom+"';");
        data_ArchiveClient = FXCollections.observableArrayList();
        int i=0;
            while(rs.next())
            {
                data_ArchiveClient.add(new ArchiveClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                i++;
            }
            rs.close();
            
            if(i!=0)
            {
                columnArchiveEmploye_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                Archive_ClientChambre.setCellValueFactory(new PropertyValueFactory<>("idChambre"));
                Archive_ClientDateE.setCellValueFactory(new PropertyValueFactory<>("dateE"));                
                Archive_ClientDateS.setCellValueFactory(new PropertyValueFactory<>("dateS"));
                archiveTableClient.setItems(data_ArchiveClient);
            }
            
            else {
                archiveTableClient.setItems(null);
            JOptionPane jop3 = new JOptionPane();
jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
JOptionPane.ERROR_MESSAGE);
            }
    }

    @FXML
    private void actualiserFacture(ActionEvent event) throws SQLException {
        BdConnection bd=new BdConnection();
        a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnreservation.setOpacity(0);
                        int u=0;
                        grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            Connection con = bd.connect();
          /* ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT * FROM facture  ; ");
        FactureO = FXCollections.observableArrayList();
       while (rs.next()) {
            ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(2) + "';");
            while (r.next()) {
                    u++;
                    System.out.println(r.getString("nom"));
                    FactureO.add(new Facture(rs.getString(7), r.getString("nom"), rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
                
            }
        }
        if (u != 0) {
            FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
            FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
            FdateAC.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            FdateDC.setCellValueFactory(new PropertyValueFactory<>("DateS"));
            FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
            FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
            FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

            tabFactres.setItems(FactureO);
        } else {
            tabFactres.setItems(null);
        }*/
            
             ResultSet r;
            ResultSet rs;
            //r = con.createStatement().executeQuery("SELECT * FROM client ; ");

            int l = 0;
            rs = con.createStatement().executeQuery("select * from client natural join facture;");
            System.out.println(rs.getRow());
            FactureO = FXCollections.observableArrayList();
            grand= FXCollections.observableArrayList();
            grand.clear();
            while (rs.next()) {
                l++;
                  factury fact = new factury();
                fact.setId(rs.getString(13));
                fact.setNom(rs.getString(2));
                fact.setNchambre(rs.getString(8));
                fact.setDA(rs.getString(9));
                fact.setDS(rs.getString(10));
                fact.setModepaie(rs.getString(11));
                fact.setRemise(rs.getInt(14));
                fact.setTotal(rs.getInt(12));
                grand.add(fact);
                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString(5));
                FactureO.add(new Facture(rs.getString(13), rs.getString(2), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(14), rs.getInt(12)));
            }
           ObservableList<Facture> O;
           O=FactureO;
            if (l != 0) {
                FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
                FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
                FdateAC.setCellValueFactory(new PropertyValueFactory<>("DateA"));
                FdateDC.setCellValueFactory(new PropertyValueFactory<>("DateS"));
                FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
                FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
                FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

                tabFactres.setItems(grand);
            } else {

                tabFactres.setItems(null);

            }
    }

    @FXML
    private void actualiserReservation(ActionEvent event) {
       BdConnection bd=new BdConnection();
        Connection con = bd.connect();
        a--;
        b=a;
            int j = 0;
            int i = 0;
            System.out.println("Open database successfully");

            try {
                // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

                String idRe;

                ResultSet rs;/* comboBox.getValue()*/

                rs = con.createStatement().executeQuery("SELECT * FROM reservation ;");
                reser = FXCollections.observableArrayList();
                while (rs.next()) {
                    ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(4) + "';");
                    while (r.next()) {
                        System.out.println(r.getString("nom"));

                        reser.add(new Reservation(rs.getString(1), r.getString("nom"), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
                        i++;
                    }

                }

                rs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println("Operation donne successfully");
            if (i != 0) {

                nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
                NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
                DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
                DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
                formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
                IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
                nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

                tabR.setItems(reser);
            } else {

                tab.setItems(null);

            }
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
                b=a;
    }

    @FXML
    private void actualiserClient(ActionEvent event) {
        int i = 0;
        a--;
        b=a;
            BdConnection bd=new BdConnection();
            try {

                /*String url1 = "jdbc:mysql://localhost:3306/projet_bd?Datetimecode=false&&serverTimezone=UTC";
                 String user = "root";
                 String password = "dcsuperheroesgirls20020102#()";

                 Class.forName("com.mysql.cj.jdbc.Driver");*/
                //Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Open database successfully");
                //con = DriverManager.getConnection(url1, user, password);
                Connection con = bd.connect();
                System.out.println("Open database successfully");
                ResultSet rs;/* comboBox.getValue()*/

                rs = con.createStatement().executeQuery("SELECT * FROM Client" + ";");
                data1 = FXCollections.observableArrayList();
                while (rs.next()) {
                    data1.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
                    i++;
                }

                rs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println("Operation donne successfully");
            if (i != 0) {
                IdC.setCellValueFactory(new PropertyValueFactory<>("ID"));
                nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
                sexeC.setCellValueFactory(new PropertyValueFactory<>("sexe"));
                adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                telC.setCellValueFactory(new PropertyValueFactory<>("tel"));
                EmailC.setCellValueFactory(new PropertyValueFactory<>("Email"));
                tab.setItems(data1);
            } else {

                tab.setItems(null);

            }
    }

    @FXML
    private void actualiserChambre(ActionEvent event) {
         a--;
            b=a;
            grapheFinance.getData().removeAll(Collections.singleton(grapheFinance.getData().setAll()));
            pnCalendrier.setOpacity(0);
            pnChambre.toFront();
            pnChambre.setOpacity(1);
            btnSupprimerC.setOpacity(1);
            btnSupprimerC.setDisable(false);
            btnAjoutChambre.setOpacity(1);
            btnAjoutChambre.setDisable(false);
            btnModifierC.setOpacity(1);
            btnModifierC.setDisable(false);
            int k = 0;
            BdConnection bd= new BdConnection();
            Connection c = bd.connect();

            try {
                ResultSet rc = c.createStatement().executeQuery("SELECT idChambre,description_chambre,prix,type,etat FROM chambre;");
                chambreO = FXCollections.observableArrayList();
                while (rc.next()) {

                    //Chambre ch = new Chambre(null, k, null, null, k);
                    chambreO.add(new Chambre(rc.getString(1), rc.getString(2), rc.getInt(3), rc.getString(4), rc.getInt(5)));
                    System.out.println(rc.getString(1));
                    k++;
                }
                // rc.close();
                // c.close();
            } catch (SQLException e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                //System.exit(0);
                System.out.println("ERROR");
            }
            System.out.println(k);
            if (k != 0) {

                nChambre.setCellValueFactory(new PropertyValueFactory<>("id"));
                descriptionc.setCellValueFactory(new PropertyValueFactory<>("description"));
                prixChambre.setCellValueFactory(new PropertyValueFactory<>("prix"));
                typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
                etatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
//                Chambre cd = chambreO.get(1);
               // System.out.println(cd.getId());

                tabC.setItems(chambreO);
            }

    }
          
    }