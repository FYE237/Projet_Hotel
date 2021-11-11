/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static projetbd2.EmployeController.stage5;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ReceptionistePageController implements Initializable {

    @FXML
    private JFXButton btnClients;
    @FXML
    private JFXButton btnChambres;
    @FXML
    private JFXButton btnCalendrier;
    private JFXButton btnArchives;
    @FXML
    private JFXButton btnParametres;
    @FXML
    private JFXButton btnReservation;
    @FXML
    private JFXButton btnFacturation;
    @FXML
    private Pane priStatus;
    @FXML
    private Label libstatus;
    @FXML
    private GridPane pnclients;

    @FXML
    private GridPane pnchambres;

    private GridPane pnparametres;

    @FXML
    private GridPane pnarchives;

    @FXML
    private GridPane pncalendrier;
    @FXML
    private GridPane pnreservation;
    @FXML
    private GridPane pnFacturation;
    //TableClient
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
    private Button btnEditerFacture;
    @FXML
    private TextField TxtRechercheFact;
    @FXML
    private Button btnRechercheFact;
    @FXML
    private Button btnAjoutChambre;
    @FXML
    private Button btnModifierC;
    @FXML
    private Button btnSupprimerC;
    @FXML
    private Button btnAjoutReser;
    @FXML
    private Button btnModifierRe;

    @FXML
    private Button btnSupprimerRESER;

    //Objet Observables
    private ObservableList<Client> data;
    private ObservableList<Reservation> reser;
    private ObservableList<Chambre> chambreO;
    private ObservableList<Facture> FactureO;

    @FXML
    private Button btnAjoutClient;
    @FXML
    private Button btnRechercheResev;
    @FXML
    private TextField txtRechercheresev;
    @FXML
    private ComboBox<String> ChoixRC;

    BdConnection bd = new BdConnection();
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
    private TableView<Chambre> tabC;
    @FXML
    private TableColumn<Chambre, String> nChambre;
    @FXML
    private TableColumn<Chambre, Integer> prixChambre;
    @FXML
    private TableColumn<Chambre, String> descriptionc;
    @FXML
    private TableColumn<Chambre, String> typeC;
    @FXML
    private TableColumn<Chambre, Integer> etatC;
    @FXML
    private Button btnModifierClient;
    @FXML
    private Button SupprimerClient;
    @FXML
    private TableView<factury> tabFactres;
    @FXML
    private TableColumn<factury, String> FidC;
    @FXML
    private TableColumn<factury, String> FnomC;
    @FXML
    private TableColumn<factury, String> FnChambreC;
    /*@FXML
     private TableColumn<Facture,String> FtypeC;
     @FXML
     private TableColumn<Facture,String> FformuleC;*/
    @FXML
    private TableColumn<factury, String> FdateAC;
    @FXML
    private TableColumn<factury, String> FdateDC;
    /*@FXML
     private TableColumn<Facture,Integer> FnbNuitsC;*/
    @FXML
    private TableColumn<factury, String> FmodePaieC;
    @FXML
    private TableColumn<factury, Integer> FremiseC;
    @FXML
    private TableColumn<factury, Integer> FtotalC;
    long nbnuit = 0;
    @FXML
    private Button btnChambresliste;
    @FXML
    private TextField RechercheCambre;
    @FXML
    private Button btnRecherchechambre;
    @FXML
    private DatePicker datedebutreche;
    @FXML
    private DatePicker dateFinReche;
    @FXML
    private TextField txtRechercheClient;
    @FXML
    private Button btnRechercheClient;
    @FXML
    private ImageView imagereception;
    @FXML
    private ImageView imageFacture;
    @FXML
    private ImageView imageChambre;
    @FXML
    private ImageView imageReservation;
    @FXML
    private GridPane pnParametre;
    @FXML
    private Label lblNomEmploye;
    @FXML
    private GridPane pnCalendar;
    @FXML
    private TableView<EmployeTravail> table;
    @FXML
    private TableColumn<EmployeTravail, String> idEmploye;
    @FXML
    private TableColumn<EmployeTravail, String> tache;
    @FXML
    private TableColumn<EmployeTravail, String> jour;
    @FXML
    private TableColumn<EmployeTravail, String> tacheDesc;
    @FXML
    private TableColumn<EmployeTravail, String> horaire;
    public ObservableList list = FXCollections.observableArrayList();

    String idEmploy = LoginController.id;
    String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC";
    String utilisateur = "root";
    String motDePasse = "mumbeatrice";
    Connection connexion = null;
    ResultSet resultat = null;
    @FXML
    private ImageView imageParametre;
    @FXML
    private ImageView imageCalendrier;
    
    @FXML
    private TableView<factury> tabFact;
    ObservableList<factury> grand ;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL Location, ResourceBundle rb) {
        // TODO
        imagereception.setOpacity(1);
        imageFacture.setOpacity(0);
        imageChambre.setOpacity(0);
        imageReservation.setOpacity(0);
        imageParametre.setOpacity(0);
        imageCalendrier.setOpacity(0);
        pnFacturation.setOpacity(0);
        pnarchives.setOpacity(0);
        pncalendrier.setOpacity(0);
        pnCalendar.setOpacity(1);
        pnchambres.setOpacity(0);
        pnclients.setOpacity(1);
        pnParametre.setOpacity(0);
        pnreservation.setOpacity(0);
        pnCalendar.setOpacity(0);
        ChoixRC.getItems().addAll("Nom", "Id", "Adresse");

        libstatus.setText("Clients");
        pnclients.toFront();
        idEmploye.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("idEmploye"));
        tacheDesc.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tacheDesc"));
        tache.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("tache"));
        jour.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("jour"));
        horaire.setCellValueFactory(new PropertyValueFactory<EmployeTravail, String> ("horaire"));
        ArrayList<EmployeTravail> travail = new ArrayList<>();
          ModifierPlaningController.chargeDriver();
          //label.setText(LoginController.nom);
          lblNomEmploye.setText(LoginController.nom);
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
        //Connection con;
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
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
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
            tab.setItems(data);
        } else {

            /* Stage stage=new Stage();
             Parent root=FXMLLoader.load(getClass().getResource("ErreurRechercheRapide.fxml"));
             Scene scene=new Scene(root);
             stage.setScene(scene);
             stage.showAndWait();*/
            //Boîte du message d'erreur
            tab.setItems(null);
            /*JOptionPane jop3 = new JOptionPane();
             jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
             JOptionPane.ERROR_MESSAGE);*/
        }

        // Chambre c=new Chambre("005",30000,"Luxe","Double",0);
        //dupontd tabC.getItems().add(c);
    }

    @FXML
    public void handleClicks(ActionEvent event) throws SQLException {
        if (event.getSource() == btnReservation) {
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(1);
            imageParametre.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(0);
            pnchambres.setOpacity(0);
            pnclients.setOpacity(0);
            pnParametre.setOpacity(0);
            pnreservation.setOpacity(1);
            libstatus.setText("Reservation");
            pnreservation.toFront();
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

            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb( 29, 38, 62),CornerRadii.EMPTY,Insets.EMPTY)));
        } else if (event.getSource() == btnFacturation) {
            imagereception.setOpacity(0);
            imageFacture.setOpacity(1);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnFacturation.setOpacity(1);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(0);
            pnchambres.setOpacity(0);
            pnclients.setOpacity(0);
            pnParametre.setOpacity(0);
            pnreservation.setOpacity(0);
            libstatus.setText("Facturation");
            pnFacturation.toFront();
            int u=0;
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
                grand.add(fact);
              
                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString(5));
               // FactureO.add(new Facture(rs.getString(13), rs.getString(2), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(14), rs.getInt(12)));
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

            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb( 29, 38, 62),CornerRadii.EMPTY,Insets.EMPTY)));
        } else if (event.getSource() == btnClients) {
            imagereception.setOpacity(1);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(0);
            pnchambres.setOpacity(0);
            pnclients.setOpacity(1);
            pnParametre.setOpacity(0);
            pnreservation.setOpacity(0);
            libstatus.setText("Clients");
            pnclients.toFront();
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
                data = FXCollections.observableArrayList();
                while (rs.next()) {
                    data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
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
                tab.setItems(data);
            } else {

                tab.setItems(null);

            }

            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb( 29, 38, 62),CornerRadii.EMPTY,Insets.EMPTY)));
        } else if (event.getSource() == btnChambres) {
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(1);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(0);
            imageCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(0);
            pnchambres.setOpacity(1);
            pnclients.setOpacity(0);
            pnParametre.setOpacity(0);
            pnreservation.setOpacity(0);
            libstatus.setText("Chambres");
            pnchambres.toFront();
            btnSupprimerC.setOpacity(0);
            btnSupprimerC.setDisable(true);
            btnAjoutChambre.setDisable(true);
            btnAjoutChambre.setOpacity(0);
            btnModifierC.setOpacity(0);
            btnModifierC.setDisable(true);
            RechercheCambre.setPromptText("Entrez le numéro de la chambre ");
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

            System.out.println(nChambre.getCellData(1));
            System.out.println(descriptionc.getCellData(0));

        } else if (event.getSource() == btnCalendrier) {
            libstatus.setText("Calendrier");
            pncalendrier.toFront();
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageParametre.setOpacity(0);
            imageCalendrier.setOpacity(1);
            imageReservation.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(1);
            pnCalendar.setOpacity(1);
            pnchambres.setOpacity(0);
            pnclients.setOpacity(0);
            pnParametre.setOpacity(0);
            pnreservation.setOpacity(0);
        } else if (event.getSource() == btnParametres) {
            libstatus.setText("Parametres");
            pnParametre.toFront();
            imagereception.setOpacity(0);
            imageFacture.setOpacity(0);
            imageChambre.setOpacity(0);
            imageReservation.setOpacity(0);
            imageParametre.setOpacity(1);
            imageCalendrier.setOpacity(0);
            pnFacturation.setOpacity(0);
            pnarchives.setOpacity(0);
            pncalendrier.setOpacity(0);
            pnchambres.setOpacity(0);
            pnclients.setOpacity(0);
            pnParametre.setOpacity(1);
            pnreservation.setOpacity(0);
            libstatus.setText("Parametres");
        } else {

        }

    }

    // Facturation
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
    private void rechercheFact(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        int k = 0;
        String nom = "bt";
        String prenom = " bravo";
        System.out.println(prenom.indexOf(nom));
        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT * FROM facture  ; ");
        FactureO = FXCollections.observableArrayList();
        while (rs.next()) {
            ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient ='" + rs.getString(2) + "';");
            while (r.next()) {
                if (r.getString("nom").toLowerCase().indexOf(TxtRechercheFact.getText().toLowerCase(Locale.FRENCH)) != -1) {
                    k++;
                    System.out.println(r.getString("nom"));
                    FactureO.add(new Facture(rs.getString(8), r.getString("nom"), rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
                }
            }
        }
        if (k != 0) {
            FremiseC.setCellValueFactory(new PropertyValueFactory<>("Remise"));
            FmodePaieC.setCellValueFactory(new PropertyValueFactory<>("Modepaie"));
            FdateAC.setCellValueFactory(new PropertyValueFactory<>("DateA"));
            FdateDC.setCellValueFactory(new PropertyValueFactory<>("DateS"));
            FnChambreC.setCellValueFactory(new PropertyValueFactory<>("Nchambre"));
            FidC.setCellValueFactory(new PropertyValueFactory<>("Id"));
            FnomC.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            FtotalC.setCellValueFactory(new PropertyValueFactory<>("Total"));

            tabFactres.setItems(grand);
        } else {
            tabFactres.setItems(null);
        }

    }

    //Chambre
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
        Parent FicheP;
        FicheP = FXMLLoader.load(getClass().getResource("SupprimerChambre.fxml"));
        Scene FicheP_scene = new Scene(FicheP);
        Stage FicheP_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        FicheP_stage.hide();
        FicheP_stage.initStyle(StageStyle.UTILITY);
        FicheP_stage.setScene(FicheP_scene);
        FicheP_stage.showAndWait();

    }

    //Reservation
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
    void ModifierRe(ActionEvent event) throws IOException {
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
    void SupprimerReser(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FXMLSupReservation.fxml"));
        Scene root_scene = new Scene(root);
        Stage root_stage = new Stage(); //(Stage)((Node)event.getSource()).getScene().getWindow();
        root_stage.hide();
        root_stage.initStyle(StageStyle.UTILITY);
        root_stage.setScene(root_scene);
        root_stage.showAndWait();
    }

    //Client
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

        /* System.out.println("SELECT * FROM Client" + ";");
         Connection con;
         int i = 0;

         try {

         String url = "jdbc:mysql://localhost:3306/projet_bd?Datetimecode=false&&serverTimezone=UTC";
         String user = "root";
         String password = "dcsuperheroesgirls20020102#()";

         Class.forName("com.mysql.cj.jdbc.Driver");
         //Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("Open database successfully");
         con = DriverManager.getConnection(url, user, password);
         //con.setAutoCommit(false);
         System.out.println("Open database successfully");
         ResultSet rs;/* comboBox.getValue()*/

        /* rs = con.createStatement().executeQuery("SELECT * FROM Client" + ";");
         data = FXCollections.observableArrayList();
         while (rs.next()) {
         data.add(new Client(rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(4), rs.getString(7)));
         i++;
         }

         rs.close();
         } catch (ClassNotFoundException | SQLException e) {
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
         tab.setItems(data);
         } else {*/

        /* Stage stage=new Stage();
         Parent root=FXMLLoader.load(getClass().getResource("ErreurRechercheRapide.fxml"));
         Scene scene=new Scene(root);
         stage.setScene(scene);
         stage.showAndWait();*/
            //Boîte du message d'erreur
        // tab.setItems(null);****************************
            /*JOptionPane jop3 = new JOptionPane();
         jop3.showMessageDialog(null, "ERREUR!!! AUCUNE CORRESPONDANCE", "Erreur",
         JOptionPane.ERROR_MESSAGE);*/
        // }*******************
    }

    @FXML
    private void rechercheReservationDate(ActionEvent event) {
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

        /* Connection con = bd.connect();
         int j = 0;
         int i = 0;
         tabR.getItems().clear();
         System.out.println("Open database successfully");

         try {
         ResultSet r = con.createStatement().executeQuery("SELECT idClient FROM client WHERE nom='" + txtRechercheresev.getText() + "'; ");

         String idRe = "";
         while (r.next()) {
         idRe = r.getString("idClient");
         System.out.println(idRe);
         j++;
         }
         if (j != 0) {
         ResultSet rs;*//* comboBox.getValue()*/

        /* rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE idClient='" + idRe + "';");
         reser = FXCollections.observableArrayList();
         while (rs.next()) {
         reser.add(new Reservation(rs.getString(1), txtRechercheresev.getText(), rs.getInt(10), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(9)));
         i++;
         }

         rs.close();
         }
         } catch (SQLException e) {
         System.out.println(e.getClass().getName() + ": " + e.getMessage());
         //System.exit(0);
         System.out.println("ERROR");
         }

         System.out.println("Operation donne successfully" + i + " " + j);
         if (i != 0) {
         IdR.setCellValueFactory(new PropertyValueFactory<>("id"));
         nomClientR.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
         nbnuitR.setCellValueFactory(new PropertyValueFactory<>("nbNuit"));
         NchambreR.setCellValueFactory(new PropertyValueFactory<>("nChambre"));
         DateAR.setCellValueFactory(new PropertyValueFactory<>("DateA"));
         DateSR.setCellValueFactory(new PropertyValueFactory<>("DateD"));
         formuleR.setCellValueFactory(new PropertyValueFactory<>("formule"));
         System.out.println("Operation donne successfully" + i + " " + j);
         tabR.setItems(reser);
         } else {

         tab.setItems(null);

         }*/
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
    private void listeChambre(ActionEvent event) {
        int k = 0;
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
/*
    @FXML
    private void recherchedebutR(ActionEvent event) {
        Connection con = bd.connect();
        long j = datedebutreche.getValue().toEpochDay();
        //long k = dateFinReche.getValue().toEpochDay();
        System.out.println("Open database successfully");
        int l = 0;
        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

          /*  rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE dateEntree='" + datedebutreche.getValue() + "' ;");
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
        Connection con = bd.connect();
        //long j = datedebutreche.getValue().toEpochDay();
        long i = dateFinReche.getValue().toEpochDay();
        System.out.println("Open database successfully");

        try {
            // ResultSet r = con.createStatement().executeQuery("SELECT nom FROM client ; ");

            String idRe;

            ResultSet rs;/* comboBox.getValue()*/

           /* rs = con.createStatement().executeQuery("SELECT * FROM reservation WHERE dateSortie ='" + dateFinReche.getValue() + "' ;");
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
    }*/

    @FXML
    private void RechercheClient(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        if (ChoixRC.getValue().equals("Nom")) {
            //txtRechercheClient.setPromptText("Entrez le nom du client");
            int i = 0;
            System.out.println("Open database successfully");
            ResultSet rs;

            rs = con.createStatement().executeQuery("SELECT * FROM client" + ";");
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(2).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
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
                tab.setItems(data);
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
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(3).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
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
                tab.setItems(data);
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
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                if (rs.getString(1).toLowerCase().indexOf(txtRechercheClient.getText().toLowerCase()) != -1) {
                    data.add(new Client(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(3)));
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
                tab.setItems(data);
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
    private void listefactToday(ActionEvent event) throws SQLException {
        Connection con = bd.connect();
        ResultSet r;
        ResultSet rs;
            //r = con.createStatement().executeQuery("SELECT * FROM client ; ");
        LocalDate date = LocalDate.now();
        int l = 0;
        rs = con.createStatement().executeQuery("select * from facture NATURAL JOIN client WHERE dateSortie ='" + date + "';");
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
          //  FactureO.add(new Facture(rs.getString(8), rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(7), rs.getInt(6)));
        }
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
    private void btnDeconnexion(ActionEvent event) throws IOException {
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
    }

    static Stage stage5;
    @FXML
    private void changePassword(ActionEvent event) throws IOException {
        stage5=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("ModifierPasswordReceptionniste.fxml"));
        Scene scene=new Scene(root);
        stage5.setScene(scene);
        stage5.initStyle(StageStyle.UNDECORATED);
        stage5.showAndWait();
    }

    @FXML
    private void refresh(ActionEvent event) {
        
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

    @FXML
    private void recherchedebutR(ActionEvent event) {
    }

    @FXML
    private void rechercheFinR(ActionEvent event) {
    }

}
