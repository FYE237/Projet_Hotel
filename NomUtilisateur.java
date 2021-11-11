/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HP
 */
public class NomUtilisateur {
    
       private  static  String nom;//Property nom=null;

   /* public NomUtilisateur(String nom1) {
        nom=new SimpleStringProperty(nom1);
    }
    
    public String getNom() {
        return nom.get();
    }

    public NomUtilisateur ()
    {
        
    }
    
    
    public void setNom(String value) {
        nom.set(value);
    }
       
    public StringProperty nomProperty() {
        return nom;
    }*/
       
       public static void setNom(String value){
           nom=new String(value);
       }
       
       public static String getNom(){
           return nom;
       }
}
