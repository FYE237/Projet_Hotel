/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class ArchiveClient {
   
    private final SimpleStringProperty nom;
    private final SimpleStringProperty idChambre;
    private final SimpleStringProperty dateE;
    private final SimpleStringProperty dateS;

    public ArchiveClient(String nom, String idChambre, String dateE, String dateS) {
        
        this.nom = new SimpleStringProperty(nom);
        this.idChambre = new SimpleStringProperty(idChambre);
        this.dateE = new SimpleStringProperty(dateE);
        this.dateS = new SimpleStringProperty(dateS);
    }

    public String getNom() {
        return nom.get();
    }

    public String getIdChambre() {
        return idChambre.get();
    }

    public String getDateE() {
        return dateE.get();
    }

    public String getDateS() {
        return dateS.get();
    }
    
    
    
}
