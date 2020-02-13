/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Matchs;

import ConnexionBD.DbMatchDAO;
import Entities.Arbitres;
import Entities.Enum.Court;
import Entities.Enum.Horaire;
import Entities.Enum.Jour;
import Entities.Enum.Type_Match;
import java.util.ArrayList;

/**
 *
 * @author p1805285
 */
public class Match implements Comparable {
    private ArrayList<Arbitres> ramasseur;
    private ArrayList<Arbitres> aLigne;
    public static int count = 1;
    private Arbitres aChaise;
    private Type_Match tm;
    private Horaire h;
    private Court c ;
    private int id;
    private Jour j;

    public Match( Court c, Type_Match tm, Horaire h, Jour j, Arbitres aChaise, ArrayList<Arbitres> aLigne, ArrayList<Arbitres> ramasseur) {
        this.id = count;
        count++;
        this.c = c;
        this.tm = tm;
        this.h = h;
        this.j = j;
        this.aChaise = aChaise;
        this.aLigne = aLigne;
        this.ramasseur = ramasseur;
    }
    
    public Match(int id, Court c, Type_Match tm, Horaire h, Jour j, Arbitres aChaise, ArrayList<Arbitres> aLigne, ArrayList<Arbitres> ramasseur) {
        this.id = id;
        this.c = c;
        this.tm = tm;
        this.h = h;
        this.j = j;
        this.aChaise = aChaise;
        this.aLigne = aLigne;
        this.ramasseur = ramasseur;
    }

    public int getId() {
        return id;
    }

    public Court getC() {
        return c;
    }


    public Type_Match getTm() {
        return tm;
    }

    public Horaire getH() {
        return h;
    }

    public Jour getJ() {
        return j;
    }

    public Arbitres getaChaise() {
        return aChaise;
    }

    public ArrayList<Arbitres> getaLigne() {
        return aLigne;
    }

    public ArrayList<Arbitres> getRamasseur() {
        return ramasseur;
    }

    public void setC(Court c) {
        this.c = c;
    }
    
    public void setTm(Type_Match tm) {
        this.tm = tm;
    }

    public void setH(Horaire h) {
        this.h = h;
    }

    public void setJ(Jour j) {
        this.j = j;
    }

    public void setaChaise(Arbitres aChaise) {
        this.aChaise = aChaise;
    }

    public void setaLigne(ArrayList<Arbitres> aLigne) {
        this.aLigne = aLigne;
    }

    public void setRamasseur(ArrayList<Arbitres> ramasseur) {
        this.ramasseur = ramasseur;
    }

    @Override
    public String toString() {
        return "Match{" + "c=" + c + ", tm=" + tm + ", h=" + h + ", j=" + j + ", aChaise=" + aChaise + ", aLigne=" + aLigne + ", ramasseur=" + ramasseur + '}';
    }

    @Override
    public int compareTo(Object o) {
        Match m = (Match) o;
        int res = -1;
        if( m.getC().toString().compareTo(this.getC().toString())==0){
            if(m.getH().toString().compareTo(this.getH().toString())==0){
                if(m.getTm().toString().compareTo(this.getTm().toString())==0){
                    if(m.getJ().toString().compareTo(this.getJ().toString())==0){ 
                        res =  0;
                    }
                }
            }
        }
        return res;
    }
    
}
