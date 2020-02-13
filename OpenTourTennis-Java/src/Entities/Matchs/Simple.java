/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Matchs;

import Entities.Arbitres;
import Entities.Enum.Court;
import Entities.Enum.Horaire;
import Entities.Joueurs;
import Entities.Enum.Jour;
import Entities.Enum.Type_Match;
import java.util.ArrayList;

/**
 *
 * @author p1805285
 */
public class Simple extends Match{
    
    private Joueurs J1;
    private Joueurs J2;

    public Simple(Joueurs J1, Joueurs J2, Court c, Type_Match tm, Horaire h, Jour j, Arbitres aChaise, ArrayList<Arbitres> aLigne, ArrayList<Arbitres> ramasseur) {
        super(c, tm, h, j, aChaise, aLigne, ramasseur);
        this.J1 = J1;
        this.J2 = J2;
    }

    public Joueurs getJ1() {
        return J1;
    }

    public Joueurs getJ2() {
        return J2;
    }

    public void setJ1(Joueurs J1) {
        this.J1 = J1;
    }

    public void setJ2(Joueurs J2) {
        this.J2 = J2;
    }

    @Override
    public String toString() {
        String toString;
        toString = "<html> "+J1.getNom()+" "+J1.getPrenom()+" VS "+J2.getNom()+" "+J2.getPrenom()+" "+this.getTm()+" </html>";
        return toString;
    }
    
    @Override
    public int compareTo(Object o){
        Simple s = (Simple) o;
        int res = -1;
        if(super.compareTo(s)==0){
            if(this.getJ1().compareTo(s.getJ1())==0){
                if(this.getJ2().compareTo(s.getJ2())==0){
                    res = 0;
                }
            }
        }
        return res;
    }
    
}
