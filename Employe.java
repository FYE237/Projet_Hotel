

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
public class Employe {
    
    private final StringProperty idEmploye;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty adresse;
    private final StringProperty dateEmbauche;
    private final StringProperty poste;
    private final StringProperty sexe;
    private final StringProperty pieceIdentite;

    public Employe(String idEmploye, String nom, String prenom, String adresse, String dateEmbauche,String poste,String sexe,String PieceIdentite) {
        this.idEmploye = new SimpleStringProperty(idEmploye);
        this.nom = new SimpleStringProperty (nom);
        this.prenom = new SimpleStringProperty (prenom);
        this.adresse =new SimpleStringProperty (adresse);
        this.dateEmbauche = new SimpleStringProperty(dateEmbauche);
        this.poste = new SimpleStringProperty(poste);
        this.sexe=new SimpleStringProperty(sexe);
        this.pieceIdentite=new SimpleStringProperty(PieceIdentite);
    }
    
     public Employe(String idEmploye, String nom, String prenom, String adresse,String poste) {
        this.idEmploye = new SimpleStringProperty(idEmploye);
        this.nom = new SimpleStringProperty (nom);
        this.prenom = new SimpleStringProperty (prenom);
        this.adresse =new SimpleStringProperty (adresse);
        this.dateEmbauche = null;
        this.poste = new SimpleStringProperty(poste);
        this.pieceIdentite=null;
        this.sexe=null;
    }

      public Employe( String nom, String prenom, String sexe,String  poste,String dateEmbauche,String pieceIdentite) {
        this.nom = new SimpleStringProperty (nom);
        this.prenom = new SimpleStringProperty (prenom);
        this.dateEmbauche = new SimpleStringProperty (dateEmbauche);
        this.poste = new SimpleStringProperty (poste);
        this.sexe=new SimpleStringProperty (sexe);
        this.pieceIdentite=new SimpleStringProperty (pieceIdentite);
        this.idEmploye=null;
        this.adresse=null;
    }
    
    public String getIdEmploye() {
        return idEmploye.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getPrenom() {
        return prenom.get();
    }

    public String getAdresse() {
        return adresse.get();
    }

    public String getDateEmbauche() {
        return dateEmbauche.get();
    }
    
     public void setIdEmploye(String value) {
        idEmploye.set(value);
    }
     public String getIdPoste() {
        return poste.get();
    }
    public String getPieceIdentite() {
        return pieceIdentite.get();
    }
    public String getSexe() {
        return sexe.get();
    }
    
      public void setNom(String value) {
        nom.set(value);
    }

    public void setPrenom(String value) {
        prenom.set(value);
    }
    
    public void setAdresse(String value) {
        adresse.set(value);
    }
    
    public void setDateEmbauche (String value) {
        dateEmbauche.set(value);
    }
    public void setPoste (String value) {
        poste.set(value);
    }
    public void setPieceIdentite (String value) {
        pieceIdentite.set(value);
    }
    public void setSexe (String value) {
        sexe.set(value);
    }
    
     public StringProperty idEmployeProperty() {
        return idEmploye;
    }
    
       public StringProperty nomProperty() {
        return nom;
    }

    public StringProperty prenomProperty() {
        return prenom;
    }
       public StringProperty adresseProperty() {
        return adresse;
    }

    public StringProperty dateEmbaucheProperty() {
        return dateEmbauche;
    }
    
    public StringProperty posteProperty() {
        return poste;
    }
    public StringProperty pieceIdentiteProperty() {
        return pieceIdentite;
    }
    public StringProperty sexeProperty() {
        return sexe;
    }
    
}
