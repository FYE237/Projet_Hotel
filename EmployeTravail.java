/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author lenovo
 */
public class EmployeTravail {
    private final SimpleStringProperty idEmploye;
    private final SimpleStringProperty tacheDesc;
    private final SimpleStringProperty tache;
    private final SimpleStringProperty jour;
     private final SimpleStringProperty horaire;
    
      public EmployeTravail(String idEmploye, String tacheDesc, String tache, String jour, String horaire) {
        this.idEmploye = new SimpleStringProperty(idEmploye);
        this.tacheDesc = new SimpleStringProperty(tacheDesc);
        this.tache = new SimpleStringProperty(tache);
        this.jour = new SimpleStringProperty(jour);
        this.horaire = new SimpleStringProperty(horaire);
    }

    public String getIdEmploye() {
        return idEmploye.get();
    }

    public String getTacheDesc() {
        return tacheDesc.get();
    }

    public String getTache() {
        return tache.get();
    }

    public String getJour() {
        return jour.get();
    }
     public String getHoraire() {
        return horaire.get();
    }
     
    
}
