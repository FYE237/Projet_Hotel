/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SuprrimerEmployeController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private Label succes;
    @FXML
    private JFXTextField Id_Box;

    private BdConnection bd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bd=new BdConnection();
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        Connection con=bd.connect();
        int i,j,k;
        int rs=con.createStatement().executeUpdate("delete from userPasseword where idEmploye='"+Id_Box.getText()+"';");
        i=rs;
        rs=con.createStatement().executeUpdate("delete from EmployePoste where idEmploye='"+Id_Box.getText()+"';");
        j=rs;
        con.createStatement().executeUpdate("delete from Employe where idEmploye='"+Id_Box.getText()+"';");
        k=rs;
        System.out.println(i+","+j+","+k);
        if(i==0||j==0||k==0)
            {
                Id_Box.clear();
                error.setOpacity(1);
            }
        else {
                succes.setOpacity(1);
        }
    }

    @FXML
    private void fermer(ActionEvent event) {
         Stage stage=AccueilDirecteurController.stage4;
        stage.close();
    }
    
}
