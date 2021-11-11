/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import static com.sun.deploy.cache.Cache.exists;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RechercheEmployeController implements Initializable {

    @FXML
    private TextField Nom_Box;
    @FXML
    private TextField Id_Box;
    @FXML
    private Label error_label;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblAdresse;
    @FXML
    private Label lblPoste;
    @FXML
    private Label lblDate;
    @FXML
    private GridPane pnRechercheEmploye;
    @FXML
    private GridPane pnFicheEmploye;
    @FXML
    private Label lblId;

    private ObservableList<Employe> data;
    @FXML
    private GridPane pnEmploye1;
    @FXML
    private Pane Fiche;
    
    static int a=0;
   private BdConnection bd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pnEmploye1.setOpacity(0);
        pnRechercheEmploye.toFront();
        pnRechercheEmploye.setOpacity(1);
        bd=new BdConnection();
    }

    @FXML
    private void Rechercher(ActionEvent event) throws IOException, SQLException {

        boolean present = false;

        System.out.println("SELECT * FROM employe where nom=" + "'" + Nom_Box.getText() + "' " + "and idEmploye=" + "'" + Id_Box.getText() + "';");
        Connection con=bd.connect();

        try {
            
            System.out.println("Open database successfully");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employe where nom=" + "'" + Nom_Box.getText() + "' " + "and idEmploye=" + "'" + Id_Box.getText() + "';");

            if (rs.next()) {
                if (rs.getString(1) != null)  {
                    present = true;
                }
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation donne successfully");

        if (present == true) {
               a=1;
            pnRechercheEmploye.setOpacity(0);
            pnEmploye1.toFront();
            pnEmploye1.setOpacity(1);
            pnFicheEmploye.setOpacity(1);

            data = FXCollections.observableArrayList();

            ResultSet rs1 = con.createStatement().executeQuery("SELECT idEmploye,nom,prenom,adresse,dateEmbauche,idPoste,sexe,pieceIdentite FROM employe natural join employeposte where nom=" + "'" + Nom_Box.getText() + "' " + "and idEmploye=" + "'" + Id_Box.getText() + "';");

            if (rs1.next()) {
                data.add(new Employe(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6),rs1.getString(7),rs1.getString(8)));
                if (rs1.getString(2) != null) {
                    lblNom.setText(data.get(0).getNom());
                } else {
                    lblNom.setText("/");
                }
                if (rs1.getString(1) != null) {
                    lblId.setText(data.get(0).getIdEmploye());
                } else {
                    lblId.setText("/");
                }
                if (rs1.getString(3) != null) {
                    lblPrenom.setText(data.get(0).getPrenom());
                } else {
                    lblPrenom.setText("/");
                }
                if (rs1.getString(4) != null) {
                    lblAdresse.setText(data.get(0).getAdresse());
                } else {
                    lblAdresse.setText("/");
                }
                if (rs1.getString(5) != null) {
                    lblDate.setText(data.get(0).getDateEmbauche());
                } else {
                    lblDate.setText("/");
                }
                if (rs1.getString(6) != null) {
                    lblPoste.setText(data.get(0).getIdPoste());
                } else {
                    lblPoste.setText("/");
                }

            }

        } else {
            Nom_Box.clear();
            Id_Box.clear();
            error_label.setOpacity(1);
        }
    }

    @FXML
    private void imprimer(ActionEvent event) throws SQLException {
        /*Toolkit kt=Toolkit.getDefaultToolkit();
        PrintJob pj=kt.getPrintJob((Frame)Fiche, "Fiche Employe", null);
         */
         /*if(a!=0)
         {
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A5,
                        PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);//HARDWARE_MINIMUM);
                PrinterJob job = PrinterJob.createPrinterJob();
                if (job != null && job.showPrintDialog(Fiche.getScene().getWindow())) {
                    boolean success = job.printPage(pageLayout, Fiche);
                    if (success) {
                        job.endJob();
                    }
          }
        }*/
         if(a!=0){
             BdConnection bd=new BdConnection();
             Connection con=bd.connect();
             ResultSet rs=con.createStatement().executeQuery("select * from employe where idEmploye ='"+lblId.getText()+"';");
     if(rs.next())
     {
        Document document = new Document();
        try
        {
            //C:\Users\HP\Desktop\ACADEMIE\GI 3\3gi\Semestre 2\Projet_BD\PROJET_BD_2021\ProjetBD2\src\Styles
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\HP\\Desktop\\FicheEmploye\\Fiche Employe"+lblNom.getText()+".pdf"));
            document.open();//C:\Users\HP\Desktop\Facture
            Image image = Image.getInstance("C:\\Users\\HP\\Desktop\\ACADEMIE\\GI 3\\3gi\\Semestre 2\\Projet_BD\\PROJET_BD_2021\\PROJETBD2\\src\\Styles\\Capture25.PNG");
            image.setAbsolutePosition(230, 700);
            document.add(image);
            for (int i = 0; i <= 6; i++) {
                document.add(new Paragraph("\n"));
            }
            Paragraph para0 = new Paragraph("\tId Employe : "+lblId.getText());
            document.add(para0);
              for (int i = 0; i <= 0; i++) {
                document.add(new Paragraph("\n"));
            }
            Paragraph para1 = new Paragraph("****************************************************************************************************************");
            document.add(para1);
            Paragraph para50 = new Paragraph("                                                           Fiche Employé                                                            ");
            document.add(para50);
            for (int i = 0; i <= 1; i++) {
                document.add(new Paragraph("\n"));
            }
            //addEmtyLine(para, 7);
            //para.setClip(250, 720);
            //document.add(Chunk.NEWLINE);
            Paragraph para2 = new Paragraph("\tNom :" + lblNom.getText() + "\nPrénom : " + lblPrenom.getText() + "\nAdresse : " + lblAdresse.getText() + "\nN° Piece Identité : " + rs.getString("PieceIdentite").toString() + "\nSexe : "+ rs.getString("Sexe").toString() + "\nPoste Occupé : " + lblPoste.getText() +"\nDate d'embauche : "+ lblDate.getText() +"");
            document.add(para2);
            //document.add(para1);
             //Paragraph para51 = new Paragraph("                                                           Details sur l'employe                                                          ");
            //document.add(para51);
            /* Paragraph para3 = new Paragraph("\t: " + nchambre + "\nType: " + type + "\nFormule : " + formule + "\nPrix : " + Prix + " FCFA ");
            document.add(para3);*/
             for (int i = 0; i <= 1; i++) {
                document.add(new Paragraph("\n"));
            }
            document.add(para1);
           /* Paragraph para53 = new Paragraph("                                                                                           Total : "+total+" ");
            document.add(para53);
            document.add(para1);*/

            /*PdfPTable table = new PdfPTable(5);
            table.addCell("Date Entrée : " + DateA + "");
            table.addCell(" Date Sortie : " + DateS + "");
            table.addCell("Nombre de nuits : " + nbnuit + "");
            table.addCell("Remise : " + remise+ "");
            table.addCell("Total : " + total+ " FCFA");
            document.add(table);
            document.add(para1);*/
           /* Paragraph para4 = new Paragraph("                                                                                   vous avez aimé votre séjour ?");
            document.add(para4);
              Paragraph para60 = new Paragraph("                                                                                            Partagez votre Expérience !");
            document.add(para60);*/
            //ppcm.executeUpdate();

        }catch(Exception e)
        {
           // JavaFXDialogs.exceptionDialog(e,"an error occured");
        }
        document.close();
        /*int a=JOptionPane.showConfirmDialog(null, "Do you Want to Print Bill", "SELECT",JOptionPane.YES_NO_OPTION);
        //F:\Facture
        if(a==0)
        {*/
            try
            {                                                                         //Fiche Employe"+lblNom.getText()+".pdf"
                 if((new File("C:\\Users\\HP\\Desktop\\FicheEmploye\\Fiche Employe"+lblNom.getText()+".pdf")).exists());
                {
                    Process p= Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\HP\\Desktop\\FicheEmploye\\Fiche Employe"+lblNom.getText()+".pdf");
                }
                if (!((new File("C:\\Users\\HP\\Desktop\\Facture\\Fiche Employe"+lblNom.getText()+".pdf")).exists()))
                {
                   System.out.println(" n'existe pas !!!");// JavaFXDialogs.informationDialog("File Is Not Exists");
                }
            }catch (Exception e)
            {
              //  JavaFXDialogs.exceptionDialog(e,"an error occured");
            }
     }
        rs.close();
        con.close();
            fermer(event);
        }
    }

    @FXML
    private void fermer(ActionEvent event) {
         Stage stage=AccueilDirecteurController.stage2;
        stage.close();
    }

}
