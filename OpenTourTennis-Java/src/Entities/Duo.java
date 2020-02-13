/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import ConnexionBD.DbDuoDAO;
import java.util.ArrayList;

/**
 *
 * @author p1805285
 */
public class Duo implements Comparable{
    private static int count =0;
    private final int id;
    private final Joueurs J1;
    private final Joueurs J2;
    public static ArrayList<Duo> lesDuos = new ArrayList();

    public Duo(Joueurs J1, Joueurs J2) {
        this.id = count;
        count++;
        this.J1 = J1;
        this.J2 = J2;
        lesDuos.add(this);
    }

    public int getId() {
        return id;
    }

    public Joueurs getJ1() {
        return J1;
    }

    public Joueurs getJ2() {
        return J2;
    }
    
    public String compositionDuo(){
        return J1.getNom()+"/"+J2.getNom();
    }
    
    public static Duo getDuo(String Ds){
        Duo D = null;
        String[] lesS = Ds.split("/");
        for(Duo d : DbDuoDAO.lesDuos){
            if(d.getJ1().getNom().equals(lesS[0])){
                if(d.getJ2().getNom().equals(lesS[1])){
                    D = d;
                }
            }
        }
        return D;
    }

    @Override
    public int compareTo(Object o) {
        Duo d = (Duo) o;
        int res = -1;
        if(this.getId()==d.getId()){
            if(this.getJ1().compareTo(d.getJ1())==0){
                if(this.getJ2().compareTo(d.getJ2())==0){
                    res = 0;
                }
            }
        }
        return res;
    }
}
