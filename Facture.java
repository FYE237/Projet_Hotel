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
public class Facture {
    private static SimpleStringProperty Id;
    private static SimpleStringProperty Nom;
    private static SimpleStringProperty Nchambre;
   /* private static SimpleStringProperty Type;
    private static SimpleStringProperty Formule;*/
    private static SimpleStringProperty DateA;
    private static SimpleStringProperty DateS;
    //private static SimpleIntegerProperty Nbnuits;
    private static SimpleStringProperty Modepaie;
    private static SimpleIntegerProperty Remise;
    private static SimpleIntegerProperty Total;

    public Facture(String id,String Nom,String Nchambre, String DateA,String DateS,String modePaie,Integer remise, Integer total) {
        this.Id = new SimpleStringProperty(id);
        this.Nom = new SimpleStringProperty(Nom);
        this.Nchambre = new SimpleStringProperty(Nchambre);
       /* this.Type = new SimpleStringProperty(type);
        this.Formule = new SimpleStringProperty(formule);*/
        this.DateA = new SimpleStringProperty(DateA);
        this.DateS = new SimpleStringProperty(DateS);
        //this.Nbnuits = new SimpleIntegerProperty(nbNuits);
        this.Modepaie = new SimpleStringProperty(modePaie);
        this.Remise = new SimpleIntegerProperty(remise);
        this.Total = new SimpleIntegerProperty(total);
  
        
    }
    
    public String getId() {
        return Id.get();
    }

    public String getNom() {
        return Nom.get();
    }

    public String getNchambre() {
        return Nchambre.get();
    }

   /* public String getType() {
        return Type.get();
    }

    public String getFormule() {
        return Formule.get();
    }*/

    public String getDateA() {
        return DateA.get();
    }
    public String getDateS() {
        return DateS.get();
    }

   /* public Integer getNbnuits() {
        return Nbnuits.get();
    }*/

    public String getModepaie() {
        return Modepaie.get();
    }

    public Integer getRemise() {
        return Remise.get();
    }

    public Integer getTotal() {
        return Total.get();
    }
 public void setId(String id){
        this.Id.set(id);
    }
     public void setNom(String nom){
        this.Nom.set(nom);
    }
      public void setNchambre(String nC){
        this.Nchambre.set(nC);
    }
     /*   public void setType(String nom){
        this.Type.set(nom);
    }
      public void setformule(String nC){
        this.Formule.set(nC);
    }*/
       public void setDateA(String DA){
        this.DateA.set(DA);
    }
     public void setDateS(String DD){
        this.DateS.set(DD);
    }
    
     /*public void setNbnuits(Integer nbNuit){
        this.Nbnuits.set(nbNuit);
    }*/
       public void setModepaie(String formule){
        this.Modepaie.set(formule);
    }
        public void setRemise(Integer nbNuit){
        this.Remise.set(nbNuit);
    }
         public void setTotal(Integer nbNuit){
        this.Total.set(nbNuit);
    }
         
      public SimpleStringProperty IdProperty(){return Id;}
      public SimpleStringProperty NomProperty(){return Nom;}
     
      public SimpleStringProperty NchambreProperty(){return Nchambre;}
      // public SimpleStringProperty TypeProperty(){return Type;}
        //public SimpleStringProperty FormuleProperty(){return Formule;}
      public SimpleStringProperty DateAProperty(){return DateA;}
      public SimpleStringProperty DateSProperty(){return DateS;}
     // public SimpleIntegerProperty NbnuitProperty(){return Nbnuits;}
       public SimpleStringProperty ModepaieProperty(){return Modepaie;}
       public SimpleIntegerProperty RemiseProperty(){return Remise;}
       public SimpleIntegerProperty TotalProperty(){return Total;}
}
