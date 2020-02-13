/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import ConnexionBD.DbJoueurDAO;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author goncalve
 */
public class Joueurs implements Comparable {
    public static int count =1;
    private int id;
    private final String nom;
    private final String prenom;
    private int classeATP;
    private final String nationalite;

    public Joueurs(String nom, String prenom, int classeATP, String nationalite) {
        this.id = count;
        count++;
        this.nom = nom;
        this.prenom = prenom;
        this.classeATP = classeATP;
        this.nationalite = nationalite;
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getClasseATP() {
        return classeATP;
    }
    public void setClasseATP(int classeATP) {
        this.classeATP = classeATP;
    }
    public String getNationalite() {
        return nationalite;
    }
    
    public static Joueurs getJoueur(String Js){
        Joueurs J = null;
        String[] lesS = Js.split(" ");
        for(Joueurs j : DbJoueurDAO.lesJoueurs){
            if(j.getNom().equals(lesS[0])){
                if(j.getPrenom().equals(lesS[1])){
                    J = j;
                }
            }
        }
        return J;
    }
    
    public static ArrayList<Joueurs> getLesJoueurs(JComboBox c){
        ArrayList<String> LesJoueursS = new ArrayList();
        ArrayList<Joueurs> LesJoueurs = new ArrayList();
        int taille = c.getItemCount();
        String[] lesS;
        String Js;
        for(int i = 0;i< taille;i++){
            LesJoueursS.add((String) c.getItemAt(i));
        }
        for(String s : LesJoueursS){
            lesS = s.split(" ");
            Js = lesS[0]+" "+lesS[1];
            LesJoueurs.add(Joueurs.getJoueur(Js));
        }
        return LesJoueurs;
    }

    @Override
    public int compareTo(Object o) {
        Joueurs j = (Joueurs) o;
        if(this.getId()==j.getId()){
            if(this.getClasseATP()==j.getClasseATP()){
                if(this.getNationalite().compareTo(j.getNationalite())==0){
                    if(this.getNom().compareTo(j.getNom())==0){
                        if(this.getPrenom().compareTo(j.getPrenom())==0){
                            return 0;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
