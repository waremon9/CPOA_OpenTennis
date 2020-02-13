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
public enum Court {
    
    Principale,
    Secondaire1,
    Secondaire2,
    Secondaire3;
    
    public static Court getCourt(String Cs){
        Court C = Court.Principale;
        for(Court c : Court.values()){
            if(c.toString().equals(Cs)){
                C = c;
            }
        }
        return C;
    }
}
