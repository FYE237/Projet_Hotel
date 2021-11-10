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
public class Chambre {
    private final SimpleStringProperty id;
    private final SimpleIntegerProperty prix;
    private final SimpleStringProperty description;
    private final SimpleStringProperty type;
    private final SimpleIntegerProperty etat;

    public Chambre(String id, String description, Integer prix, String type, Integer etat) {
        this.id = new SimpleStringProperty(id);
        this.prix =new SimpleIntegerProperty(prix);
        this.description = new SimpleStringProperty(description);
        this.type = new SimpleStringProperty(type);
        this.etat = new SimpleIntegerProperty(etat);
    }
     public String getId() {
        return id.get();
    }
    
      public Integer getPrix() {
        return prix.get();
    }
    public String getDescription() {
        return description.get();
    }

    public String getType() {
        return type.get();
    }
     public Integer getetat() {
        return etat.get();
    }
    

   
       public void setidC(String ID){
        this.id.set(ID);
    }
       public void setprix(Integer prix){
        this.prix.set(prix);
    }
     public void setnomClient(String description){
        this.description.set(description);
    }
      public void type(String type){
        this.type.set(type);
    }
     public void setetat(Integer etat){
        this.etat.set(etat);
    }
   
    // Property values
      public SimpleStringProperty IdProperty(){
          return id;
      }
      public SimpleIntegerProperty prixProperty(){return prix;}
      public SimpleStringProperty descriptionProperty(){return description;}
      public SimpleStringProperty typeProperty(){return type;}
      public SimpleIntegerProperty etatProperty(){return etat;}
     
      
    
    
}
