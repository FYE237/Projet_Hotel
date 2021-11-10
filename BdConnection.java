/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class BdConnection {
    
    public Connection connect()
        {
             try {

            String url = "jdbc:mysql://localhost:3306/PROJET_BD?useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "mumbeatrice";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BdConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
}
