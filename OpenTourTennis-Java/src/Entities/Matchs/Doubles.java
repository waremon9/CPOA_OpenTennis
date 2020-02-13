/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Matchs;

import Entities.Arbitres;
import Entities.Enum.Court;
import Entities.Duo;
import Entities.Enum.Horaire;
import Entities.Enum.Jour;
import Entities.Enum.Type_Match;
import java.util.ArrayList;

/**
 *
 * @author p1805285
 */
public class Doubles extends Match{
    private Duo D1;
    private Duo D2;
    public static ArrayList<Doubles> lesDoubles = new ArrayList();

    public Doubles(Duo D1, Duo D2, Court c, Type_Match tm, Horaire h, Jour j, Arbitres aChaise, ArrayList<Arbitres> aLigne, ArrayList<Arbitres> ramasseur) {
        super(c, tm, h, j, aChaise, aLigne, ramasseur);
        this.D1 = D1;
        this.D2 = D2;
        if(!lesDoubles.contains(this)){
            lesDoubles.add(this);
        }
    }

    public Duo getD1() {
        return D1;
    }

    public Duo getD2() {
        return D2;
    }

    public void setD1(Duo D1) {
        this.D1 = D1;
    }

    public void setD2(Duo D2) {
        this.D2 = D2;
    }
    
    @Override
    public String toString() {
        String toString;
        toString = "<html> "+D1.compositionDuo()+" VS "+D2.compositionDuo()+"  "+this.getTm()+" </html>";
        return toString;
    }
    
    @Override
    public int compareTo(Object o){
        Doubles s = (Doubles) o;
        int res = -1;
        if(super.compareTo(s)==0){
            if(this.getD1().compareTo(s.getD1())==0){
                if(this.getD2().compareTo(s.getD2())==0){
                    res = 0;
                }
            }
        }
        System.out.println("debut : "+res);
        return res;
    }
}
