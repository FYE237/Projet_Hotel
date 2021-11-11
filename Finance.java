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
public class Finance {
    
    private final IntegerProperty idFacture;
    private final StringProperty idChambre;
    private final IntegerProperty total;
    private final IntegerProperty remise;
    
    public Finance(int idFacture,String idChambre,int total,int remise)
        {
            this.idFacture= new SimpleIntegerProperty(idFacture);
            this.idChambre=new SimpleStringProperty(idChambre);
            this.total=new SimpleIntegerProperty(total);
            this.remise= new SimpleIntegerProperty(remise);
            
        }
        public Integer getIdFacture() {
        return idFacture.get();
    }
         public String getIdChambre() {
        return idChambre.get();
    }
    public Integer getTotal() {
        return total.get();
    }
    public Integer getRemise() {
        return remise.get();
    }
    
     public void setIdFacture(int value) {
        idFacture.set(value);
    }
    public void setTotal(int value) {
        total.set(value);
    }
    public void setRemise(int value) {
        remise.set(value);
    }
     public void setIdChambre(String value) {
        idChambre.set(value);
    }
     public StringProperty idChambreProperty() {
        return idChambre;
    }
     public IntegerProperty idFactureProperty() {
        return idFacture;
    }
     public IntegerProperty remiseProperty() {
        return remise;
    }
     public IntegerProperty totalProperty() {
        return total;
    }
}
