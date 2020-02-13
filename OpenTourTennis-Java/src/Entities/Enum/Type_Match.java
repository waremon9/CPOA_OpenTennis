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
public enum Type_Match {
    
    Qualification,
    PreTour,
    HuitEFinal,
    QuartFinale,
    DeuEFinal,
    Final;
    
    public static Type_Match getTypeMatch(String TMs){
        Type_Match TM = Type_Match.Qualification;
        for(Type_Match tm : Type_Match.values()){
            if(tm.toString().equals(TMs)){
                TM = tm;
            }
        }
        return TM;
    }
}
