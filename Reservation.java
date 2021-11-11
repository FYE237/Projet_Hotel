/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author pc
 */
public class Reservation {
    private final SimpleStringProperty id;
    private final SimpleStringProperty nomClient;
    private final SimpleIntegerProperty nbNuit;
    private final SimpleStringProperty nChambre;
    private final SimpleStringProperty formule;
    private final SimpleStringProperty DateA;
    private final SimpleStringProperty DateD;
    
  
    public Reservation(String id,String nomClient,Integer nbNuit, String nChambre, String DateA, String DateD,String formule) {
        this.id = new SimpleStringProperty(id);
        this.nomClient = new SimpleStringProperty(nomClient);
        this.nbNuit = new SimpleIntegerProperty(nbNuit);
        this.nChambre = new SimpleStringProperty(nChambre);
        this.DateA = new SimpleStringProperty(DateA);
        this.DateD = new SimpleStringProperty(DateD);
        this.formule=new SimpleStringProperty(formule);
    }
    public String getId() {
        return id.get();
    }

    public String getNomClient() {
        return nomClient.get();
    }

    public String getDateA() {
        return DateA.get();
    }

    public String getDateD() {
        return DateD.get();
    }

    public String getNChambre() {
        return nChambre.get();
    }
     public String getFormule() {
        return formule.get();
    }

    public Integer getNbNuit() {
        return nbNuit.get();
    }
       public void setID(String id){
        this.id.set(id);
    }
     public void setnomClient(String nom){
        this.nomClient.set(nom);
    }
      public void setnChambre(String nC){
        this.nChambre.set(nC);
    }
       public void setDateA(String DA){
        this.DateA.set(DA);
    }
     public void setDateD(String DD){
        this.DateD.set(DD);
    }
      public void setformule(String formule){
        this.formule.set(formule);
    }
     public void setnbNuit(Integer nbNuit){
        this.nbNuit.set(nbNuit);
    }
    // Property values
      public SimpleStringProperty IdProperty(){return id;}
      public SimpleStringProperty formuleProperty(){return formule;}
      public SimpleStringProperty nomProperty(){return nomClient;}
      public SimpleStringProperty nChambreProperty(){return nChambre;}
      public SimpleStringProperty DateAProperty(){return DateA;}
      public SimpleStringProperty DateDProperty(){return DateD;}
      public SimpleIntegerProperty nbNuitProperty(){return nbNuit;}
}
