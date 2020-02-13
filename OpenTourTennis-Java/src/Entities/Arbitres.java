/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import ConnexionBD.DbArbitreDAO;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author p1805285
 */
public class Arbitres implements Comparable {
    
    private static int count = 1;
    private final int id;
    private final String nom;
    private final String prenom;
    private final String telephone;
    private final String mail;
    private final String categorie;
    private final String nationalite;

    public Arbitres(String nom, String prenom, String telephone, String mail, String categorie, String nationalite) {
        this.id = count;
        count++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.categorie = categorie;
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

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNationalite() {
        return nationalite;
    }

    public static Arbitres getArbitre(String As){
        Arbitres A = null;
        String[] lesS = As.split(" ");
        for(Arbitres a : DbArbitreDAO.lesArbitres){
            if(a.getNom().equals(lesS[0])){
                if(a.getPrenom().equals(lesS[1])){
                    A = a;
                }
            }
        }
        return A;
    }

    public static ArrayList<Arbitres> getLesRammasseurs(JComboBox c){
        ArrayList<String> lesRammasseurS = new ArrayList();
        ArrayList<Arbitres> lesRammasseur = new ArrayList();
        int taille = c.getItemCount();
        String[] lesS;
        for(int i = 0;i< taille;i++){
            lesRammasseurS.add((String) c.getItemAt(i));
        }
        for(String s : lesRammasseurS){
            lesS = s.split(" ");
            for(Arbitres r : DbArbitreDAO.lesArbitres){
                if(r.getNom().equals(lesS[0])){
                    if(r.getPrenom().equals(lesS[1])){
                        lesRammasseur.add(r);
                    }
                }
            }
        }
        return lesRammasseur;
    }
    
    public static ArrayList<Arbitres> getLesLignes(JComboBox c){
        ArrayList<String> lesLignesS = new ArrayList();
        ArrayList<Arbitres> lesLignes = new ArrayList();
        int taille = c.getItemCount();
        String[] lesS;
        for(int i = 0;i< taille;i++){
            lesLignesS.add((String) c.getItemAt(i));
        }
        for(String s : lesLignesS){
            lesS = s.split(" ");
            for(Arbitres r : DbArbitreDAO.lesArbitres){
                if(r.getNom().equals(lesS[0])){
                    if(r.getPrenom().equals(lesS[1])){
                        lesLignes.add(r);
                    }
                }
            }
        }
        return lesLignes;
    }

    @Override
    public int compareTo(Object o) {
        Arbitres Arbi = (Arbitres) o;
        String compare=(Arbi.getNom()+" "+Arbi.getPrenom());
        /* For Ascending order*/
        System.out.println((this.getNom()+" "+this.getPrenom()).compareTo(compare));
        return (this.getNom()+" "+this.getPrenom()).compareTo(compare);

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }
    
}
