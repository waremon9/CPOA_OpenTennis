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
public enum Jour {
    j15,
    j14,
    j13,
    j12,
    j11,
    j10,
    j9,
    j8,
    j7,
    j6,
    j5,
    j4,
    j3,
    j2,
    j1;
    
    public static Jour getJour(String Js){
        Jour J = Jour.j1;
        for(Jour j : Jour.values()){
            if(j.toString().equals(Js)){
                J = j;
            }
        }
        return J;
    }
}
