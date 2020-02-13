/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Enum;

/**
 *
 * @author p1805285
 */
public enum Horaire {
    
    h11,
    h15,
    h18,
    h21;
    
    public static Horaire getHoraire(String Hs){
        Horaire H = Horaire.h11;
        for(Horaire h : Horaire.values()){
            if(h.toString().equals(Hs)){
                H = h;
            }
        }
        return H;
    }
}
