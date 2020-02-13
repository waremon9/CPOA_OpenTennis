/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Matchs;

import java.util.ArrayList;

/**
 *
 * @author dupor
 */
public class Planning {
    
    static int count = 1;
    int id;
    String nom;
    String dateDeb;
    int duree;
    int nbJoueur;
    boolean simple;
    boolean Double;
    boolean effectue;
    public static ArrayList<Planning> lesPlannings = new ArrayList();
    

    public Planning(String nom, String dateDeb, int duree, int nbJoueur, boolean simple, boolean Double, boolean effectue) {
        id = count;
        count++;
        this.nom = nom;
        this.dateDeb = dateDeb;
        this.duree = duree;
        this.nbJoueur = nbJoueur;
        this.simple = simple;
        this.Double = Double;
        this.effectue = effectue;
        lesPlannings.add(this);
    }

    public static int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public int getDuree() {
        return duree;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public boolean isSimple() {
        return simple;
    }

    public boolean isDouble() {
        return Double;
    }

    public boolean isEffectue() {
        return effectue;
    }

    public static ArrayList<Planning> getLesPlannings() {
        return lesPlannings;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public void setDouble(boolean Double) {
        this.Double = Double;
    }

    public void setEffectue(boolean effectue) {
        this.effectue = effectue;
    }
    
    
}
