/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author pc
 */
public class Client {
    private  final SimpleStringProperty ID;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty sexe;
    private final SimpleStringProperty tel;
    private final SimpleStringProperty Email;
    private final SimpleStringProperty adresse;


    public Client(String ID, String nom, String sexe, String tel, String Email, String adresse) {
        this.ID = new SimpleStringProperty(ID);
        this.nom = new SimpleStringProperty(nom);
        this.sexe = new SimpleStringProperty(sexe);
        this.tel = new SimpleStringProperty(tel);
        this.Email = new SimpleStringProperty(Email);
        this.adresse = new SimpleStringProperty(adresse);
    }

    public String getID() {
        return ID.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getSexe() {
        return sexe.get();
    }

    public String getTel() {
        return tel.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public String getAdresse() {
        return adresse.get();
    }
    
    public void setID(String ID){
        this.ID.set(ID);
    }
     public void setnom(String nom){
        this.nom.set(nom);
    }
      public void setsexe(String sexe){
        this.sexe.set(sexe);
    }
       public void settel(String tel){
        this.tel.set(tel);
    }
     public void setadresse(String adresse){
        this.adresse.set(adresse);
    }
     public void setEmail(String Email){
        this.Email.set(Email);
    }
     // Property values
      public SimpleStringProperty IdProperty(){
          return ID;
      }
      public SimpleStringProperty nomProperty(){return nom;}
      public SimpleStringProperty telProperty(){return tel;}
      public SimpleStringProperty adresseProperty(){return adresse;}
      public SimpleStringProperty sexeProperty(){return sexe;}
      public SimpleStringProperty EmailProperty(){return Email;}
}
