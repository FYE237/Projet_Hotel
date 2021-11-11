/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HP
 */
public class Poste {
    
    private final StringProperty idPoste;
    private final StringProperty description_Poste;
    private final IntegerProperty salaire;
    
    
    public Poste (String idPoste,String description_Poste,int salaire)
    {
        this.idPoste=new SimpleStringProperty(idPoste);
        this.description_Poste=new SimpleStringProperty(description_Poste);
        this.salaire=new SimpleIntegerProperty(salaire);
    }
    
    public String getIdPoste() {
        return idPoste.get();
    }

    public int getSalaire() {
        return salaire.get();
    }
    
     public String getdescription_Poste() {
        return description_Poste.get();
    }
    
     public void setIdPoste(String value) {
        idPoste.set(value);
    }
     
    public void setDescription_Poste(String value) {
        description_Poste.set(value);
    } 
    public void setSalaire(int value)
    {
        salaire.set(value);
    }
    
     public StringProperty idPosteProperty() {
        return idPoste;
    }
     
     public StringProperty description_PosteProperty() {
        return description_Poste;
    }
     
     public IntegerProperty salaireProperty()
     {
         return salaire;
     }
    
    
}
