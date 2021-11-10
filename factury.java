/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetbd2;

/**
 *
 * @author pc
 */
public class factury {
    private String id;
    private String nom;
    private String Nchambre;
    private String DA;
    private String DS;
    private String modepaie;
    private Integer remise;
    private Integer total;

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNchambre() {
        return Nchambre;
    }

    public String getDA() {
        return DA;
    }

    public String getDS() {
        return DS;
    }

    public String getModepaie() {
        return modepaie;
    }

    public Integer getRemise() {
        return remise;
    }

    public Integer getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNchambre(String Nchambre) {
        this.Nchambre = Nchambre;
    }

    public void setDA(String DA) {
        this.DA = DA;
    }

    public void setDS(String DS) {
        this.DS = DS;
    }

    public void setModepaie(String modepaie) {
        this.modepaie = modepaie;
    }

    public void setRemise(Integer remise) {
        this.remise = remise;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
    
}
