/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXSpinner;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ImprimerFacturePageController implements Initializable {
    
    @FXML
    private Pane PanePrint;
    @FXML
    private Label Pnom;
    @FXML
    private Label Ptel;
    @FXML
    private Label Padresse;
    @FXML
    private Label Pemail;
    @FXML
    private Label PdateA;
    @FXML
    private Label PdateS;
    @FXML
    private Label PnbNuits;
    @FXML
    private Label PnChambre;
    @FXML
    private Label Ptype;
    @FXML
    private Label Pformule;
    @FXML
    private Label Pprix;
    @FXML
    private Label Premise;
    @FXML
    private Label Ptotal;
       @FXML
    private Button Pvalider;

    @FXML
    private Button Pannule;
    @FXML
    private Label Nfacture;
    BdConnection bd = new BdConnection();
    @FXML
    private ImageView logo;
    @FXML
    private Text AlertTextprint;
   
  
    @FXML
    void PAnnuler(ActionEvent event) throws IOException {
         Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("ImprimerFacturePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.close();
    }

    @FXML
    void pvaliderPrint(ActionEvent event) throws SQLException, IOException {
        
      String nom = "";
        String prenom = "";
        String tel = "";
        String adresse = "";
        String Email = "";
        String Prix = "";
        String nchambre = "";
        String type = "";
        String DateA="";
        String DateS="";
        //java.sql.Date check_IN_Date = null;
        String nbnuit = "";
        String remise = "";
        String total="";
        String formule="";
        String nFact="";
        PreparedStatement ppcm=null;
        /*String query = "SELECT * FROM ((rooms LEFT JOIN bookings USING(rooms_id))LEFT JOIN customers USING(customers_id))LEFT JOIN payments USING(customers_id) WHERE customers_id="+ID_client+";";
        String url = "jdbc:mysql://127.0.0.1:3306/hotelprojetbd?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false";*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
         
                nom = FXMLFicheDePaiementController.nom;
                tel =FXMLFicheDePaiementController.tel;
                Email = FXMLFicheDePaiementController.email;
                nchambre = FXMLFicheDePaiementController.nChambre;
                type = FXMLFicheDePaiementController.type;
                adresse = FXMLFicheDePaiementController.adresse;
                Prix = FXMLFicheDePaiementController.prix;
                DateA = FXMLFicheDePaiementController.dateA;
                nbnuit = FXMLFicheDePaiementController.nbnuit;
                total=FXMLFicheDePaiementController.total;
                remise=FXMLFicheDePaiementController.remise;
                DateS=FXMLFicheDePaiementController.dateD;
                formule=FXMLFicheDePaiementController.formule;
                nFact="0000"+FXMLFicheDePaiementController.reservation;
                String nompdf = nom+"_Facture";
            

        } catch (Exception e) {
          //  JavaFXDialogs.exceptionDialog(e,"Impossible to access all the information in the database");
        }
         java.sql.Connection c = bd.connect();
        ResultSet rs;
        rs=c.createStatement().executeQuery("SELECT idFacture FROM facture;");
        int j=0;
        while(rs.next()){
            if(nFact.equals(rs.getString("idFacture"))){
            j++;
            }
        }
        if(j==0){
        Document document = new Document();
        try
        {
            //C:\Users\HP\Desktop\ACADEMIE\GI 3\3gi\Semestre 2\Projet_BD\PROJET_BD_2021\ProjetBD2\src\Styles
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\HP\\Desktop\\Facture\\"+nom+".pdf"));
            document.open();//C:\Users\HP\Desktop\Facture
            Image image = Image.getInstance("C:\\Users\\HP\\Desktop\\ACADEMIE\\GI 3\\3gi\\Semestre 2\\Projet_BD\\PROJET_BD_2021\\PROJETBD2\\src\\Styles\\Capture25.PNG");
            image.setAbsolutePosition(230, 700);
            document.add(image);
            for (int i = 0; i <= 6; i++) {
                document.add(new Paragraph("\n"));
            }
            Paragraph para0 = new Paragraph("\tN° Facture : "+nFact);
            document.add(para0);
              for (int i = 0; i <= 0; i++) {
                document.add(new Paragraph("\n"));
            }
            Paragraph para1 = new Paragraph("****************************************************************************************************************");
            document.add(para1);
            Paragraph para50 = new Paragraph("                                                           Details Clients                                                           ");
            document.add(para50);
            //addEmtyLine(para, 7);
            //para.setClip(250, 720);
            //document.add(Chunk.NEWLINE);
            Paragraph para2 = new Paragraph("\tNom :" + nom + "\nTel : " + tel + "\nAdresse : " + adresse + "\nEmail : " + Email + "");
            document.add(para2);
            document.add(para1);
             Paragraph para51 = new Paragraph("                                                           Details sur la chambre                                                          ");
            document.add(para51);
             Paragraph para3 = new Paragraph("\tNumber: " + nchambre + "\nType: " + type + "\nFormule : " + formule + "\nPrix : " + Prix + " FCFA ");
            document.add(para3);
             for (int i = 0; i <= 1; i++) {
                document.add(new Paragraph("\n"));
            }
            document.add(para1);
           /* Paragraph para53 = new Paragraph("                                                                                           Total : "+total+" ");
            document.add(para53);
            document.add(para1);*/

            PdfPTable table = new PdfPTable(5);
            table.addCell("Date Entrée : " + DateA + "");
            table.addCell(" Date Sortie : " + DateS + "");
            table.addCell("Nombre de nuits : " + nbnuit + "");
            table.addCell("Remise : " + remise+ "");
            table.addCell("Total : " + total+ " FCFA");
            document.add(table);
            document.add(para1);
            Paragraph para4 = new Paragraph("                                                                                   vous avez aimé votre séjour ?");
            document.add(para4);
              Paragraph para60 = new Paragraph("                                                                                            Partagez votre Expérience !");
            document.add(para60);
            //ppcm.executeUpdate();

        }catch(Exception e)
        {
           // JavaFXDialogs.exceptionDialog(e,"an error occured");
        }
        document.close();
        int a=JOptionPane.showConfirmDialog(null, "Do you Want to Print Bill", "SELECT",JOptionPane.YES_NO_OPTION);
        //F:\Facture
        if(a==0)
        {
            try
            {
                 if((new File("C:\\Users\\HP\\Desktop\\Facture\\"+nom+".pdf")).exists());
                {
                    Process p= Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\HP\\Desktop\\Facture\\"+nom+".pdf");
                }
                if (!((new File("C:\\Users\\HP\\Desktop\\Facture\\"+nom+".pdf")).exists()))
                {
                   System.out.println(" n'existe pas !!!");// JavaFXDialogs.informationDialog("File Is Not Exists");
                }
            }catch (Exception e)
            {
              //  JavaFXDialogs.exceptionDialog(e,"an error occured");
            }
            PAnnuler(event);
        }
         java.sql.Connection con = bd.connect();
         int r;
                try {
                    //rs=con.createStatement().executeUpdate("INSERT INTO `projet_bd`.`facture` (`idChambre`, `idClient`, `dateEntree`, `dateSortie`, `modePaiement`, `total`, `remise`) VALUES ('"+FNchambre.getText()+"', '"+id+"', '"+FdateA.getValue().toString()+"', '"+FDateD.getValue().toString()"', '"+Fmodepaie.getText()+"', '"+Ftotal.getText()+"', '"+remise+"');");
                    r = con.createStatement().executeUpdate("INSERT INTO facture (idChambre, idClient, dateEntree, dateSortie, modePaiement, total, remise) VALUES ('" + nchambre + "', '" + FXMLFicheDePaiementController.idC + "','" + DateA + "', '" + DateS + "', '" + FXMLFicheDePaiementController.modepaie + "', '" + total + "', '" + FXMLFicheDePaiementController.remiseInt + "');");
                   
                 ResultSet rs1 = con.createStatement().executeQuery("SELECT nom FROM client WHERE idClient='"+FXMLFicheDePaiementController.idC+"';");
                 if(rs1.next())  
                 {
                     r = con.createStatement().executeUpdate("insert into archiveclient values ('"+rs1.getString("nom").toString()+"','"+nchambre+"','"+ DateA + "', '" + DateS + "');");
                 }
                    System.out.println("insert into archiveclient values ('"+rs1.getString("nom").toString()+"','"+nchambre+"','"+ DateA + "', '" + DateS + "');");
                    r = con.createStatement().executeUpdate("UPDATE `projet_bd`.`reservation` SET `etat` = '2' WHERE (`idReservation` = '" + FXMLFicheDePaiementController.reservation + "');");
                } catch (SQLException e) {
                    e.printStackTrace();

                }
        }else{
            AlertTextprint.setOpacity(1);
        }
       
        
        

    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Pnom.setText(FXMLFicheDePaiementController.nom);
        Padresse.setText(FXMLFicheDePaiementController.adresse);
        PdateA.setText(FXMLFicheDePaiementController.dateA);
        PdateS.setText(FXMLFicheDePaiementController.dateD);
        Pemail.setText(FXMLFicheDePaiementController.email);
        Pformule.setText(FXMLFicheDePaiementController.formule);
        PnChambre.setText(FXMLFicheDePaiementController.nChambre);
        PnbNuits.setText(FXMLFicheDePaiementController.nbnuit);
        Pprix.setText(FXMLFicheDePaiementController.prix+" FCFA");
        Premise.setText(FXMLFicheDePaiementController.remise);
        Ptel.setText(FXMLFicheDePaiementController.tel);
        Ptotal.setText(FXMLFicheDePaiementController.total+ "FCFA");
        Ptype.setText(FXMLFicheDePaiementController.type);
        Nfacture.setText("000"+FXMLFicheDePaiementController.reservation);
//        Connection con= (Connection) bd.connect();
        AlertTextprint.setOpacity(0);
        
        
    }    

    
}
