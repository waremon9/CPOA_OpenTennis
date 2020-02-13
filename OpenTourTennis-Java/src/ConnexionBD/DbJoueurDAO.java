/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entities.Joueurs;
import java.util.ArrayList;

/**
 *
 * @author goncalve
 */
public class DbJoueurDAO implements IJoueurDAO {

    private final Connection connexionBD;
    public static ArrayList<Joueurs> lesJoueurs = new ArrayList();

    public DbJoueurDAO(Connection c) throws SQLException {
        this.connexionBD = c;
        if(lesJoueurs.isEmpty()){
            lesJoueurs = this.findAll();
        }
    }
    
    @Override
    public ArrayList<Joueurs> findAll() throws SQLException /*throws SQLException*/ {
        PreparedStatement pst = null;
        ResultSet rset;
        ArrayList<Joueurs> lm = new ArrayList();
        Joueurs m;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM Joueur ORDER BY Nom");
            rset = pst.executeQuery();
            while (rset.next()) {
                m = new Joueurs(rset.getString(2),rset.getString(3),rset.getInt(4),rset.getString(5));
                lm.add(m);
            }
            if(lm == null)
            {
                throw new SQLException ("Table vide");
            }

        } catch (SQLException exc) {
            throw exc;
        } finally {
            try {
                // la clause finally est toujours executée, quoi qu'il arrive
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException exc) {
                throw exc;
            }
        }
        return lm;
    }
    
    @Override
    public Joueurs findById(int noJoueurs){
        return lesJoueurs.get(noJoueurs-1);
    }
    
    public ArrayList<Joueurs> findAllCBJ(String Js) throws SQLException {
        ArrayList<Joueurs> listeJoueurs = lesJoueurs;
        try{
            
            String[] lesS = {"",""};
            if(Js!=null){
                lesS = Js.split(" ");
            }
            for(Joueurs j:listeJoueurs){
                if(!j.getNom().equals(lesS[0]) && j.getPrenom().equals(lesS[1])){
                    listeJoueurs.remove(j);
                }
            }
        } catch (java.util.ConcurrentModificationException exception) {
            
        }
        return listeJoueurs;
    }
   
    public Joueurs findByNameFirstname(String nj, String pj) throws SQLException{
        ArrayList<Joueurs> listeJoueurs = lesJoueurs;
        Joueurs J = null;
        for(Joueurs j : listeJoueurs){
            if(j.getNom().equals(nj) && j.getPrenom().equals(pj)){
                J = j;
            }
        }
        return J;
    }
}
