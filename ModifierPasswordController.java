/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class ModifierPasswordController implements Initializable {

    @FXML
    private JFXPasswordField oldPassword;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private JFXPasswordField newPassword2;

    private BdConnection bd;
    @FXML
    private Label error;
    @FXML
    private Label succes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bd=new BdConnection();
        error.setOpacity(0);
        succes.setOpacity(0);
    }    

    @FXML
    private void validerPassword(ActionEvent event) throws SQLException {
        Connection con=bd.connect();
        
        System.out.println("SELECT password,idEmploye from userpasseword where idEmploye= '"+LoginController.id+"' ;");
        ResultSet rs=con.createStatement().executeQuery("SELECT password,idEmploye from userpasseword where idEmploye='"+LoginController.id+"' ;");

        if(rs.next())
        {   
            System.out.println(rs.getString(1));
                    
        if(oldPassword.getText().toString().equals((String)rs.getString(1)))
        {
            if(newPassword.getText().equals((String)newPassword2.getText()))
            {
               System.out.println("UPDATE userpassword set password='"+newPassword.getText()+"' ;");
               int  rs1=con.createStatement().executeUpdate("UPDATE userpassword set password='"+newPassword.getText()+"' where idEmploye ='"+LoginController.id+"';");
               if(rs1==1) succes.setOpacity(1);
               else error.setOpacity(0);
                
            }  
            else  error.setOpacity(1);
        }
        
        else error.setOpacity(1);

        }
        
    }

    @FXML
    private void fermer(ActionEvent event) {
        Stage stage=AccueilDirecteurController.stage1;
        stage.close();
    }
    
}
