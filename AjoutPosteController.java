/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutPosteController implements Initializable {

    @FXML
    private TextField nomPoste_box;
    @FXML
    private TextField salaire_box;
    @FXML
    private TextArea descrip_box;

    private BdConnection dc;
    @FXML
    private Label lblSucces;
    @FXML
    private Label lblError;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc=new BdConnection();
    }    

    @FXML
    private void ajouterPoste(ActionEvent event) throws SQLException {
        
        Connection conn=dc.connect();
        System.out.println("INSERT INTO poste values('"+nomPoste_box.getText()+"' ,"+" '"+salaire_box.getText()+"' ,"+"'"+descrip_box.getText()+"');");
        int rs= conn.createStatement().executeUpdate("INSERT INTO poste values('"+nomPoste_box.getText()+"' ,"+" '"+descrip_box.getText()+"' ,"+"'"+salaire_box.getText()+"');");
        System.out.println(rs);  
        
        if(rs==1)
        {
            nomPoste_box.clear();
            salaire_box.clear();
            descrip_box.clear();
            lblSucces.setOpacity(1);
        }
        else {
                nomPoste_box.clear();
            salaire_box.clear();
            descrip_box.clear();
            lblSucces.setOpacity(0);
        }
    }

    @FXML
    private void fermer(ActionEvent event) {
    }

    

    
}
