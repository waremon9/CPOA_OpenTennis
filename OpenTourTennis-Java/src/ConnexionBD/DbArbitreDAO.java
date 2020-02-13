/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import static ConnexionBD.DbJoueurDAO.lesJoueurs;
import Entities.Arbitres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author goncalve
 */
public final class DbArbitreDAO implements IArbitreDAO {

    private final Connection connexionBD;
    public static ArrayList<Arbitres> lesArbitres = new ArrayList();
    public static ArrayList<Arbitres> lesArbiChaises= new ArrayList();
    public static ArrayList<Arbitres> lesArbiLignes = new ArrayList();

    public DbArbitreDAO(Connection c) {
        this.connexionBD = c;
        try {
            ArrayList<String> lesNats = new ArrayList();
            lesNats.add("");
            lesNats.add("");
            
            if(lesArbitres.isEmpty()){
                this.findAll();
            }
            this.findAllChaise("ITT1", lesNats);
            this.findAllLigne();
        } catch (SQLException ex) {
            Logger.getLogger(DbArbitreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param noArbitre
     * @return
     */
    @Override
    public Arbitres findById(int noArbitre){
        Arbitres A = null;
        ArrayList<Arbitres> ListeTmp = lesArbitres;
        for(Arbitres a : ListeTmp){
            if(a.getId()==noArbitre){
                A = a;
            }
        }
        return A;
    }

    @Override
    public ArrayList<Arbitres> findAll() throws SQLException {
        lesArbitres.removeAll(lesArbitres);
        PreparedStatement pst = null;
        ResultSet rset;
        Arbitres m;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM Arbitre ORDER BY Nom");
            rset = pst.executeQuery();
            while (rset.next()) {
                m = new Arbitres( rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7));
                lesArbitres.add(m);
            }
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException exc) {
                throw exc;
            }
        }
        return lesArbitres;
    }
    
    public ArrayList<Arbitres> findAllChaise(String cat, ArrayList<String> nats){
        lesArbiChaises.removeAll(lesArbiChaises);
        ArrayList<Arbitres> ListeTmp = DbArbitreDAO.lesArbitres;
        for(Arbitres a : ListeTmp){
            if("ITT1".equals(a.getCategorie())){
                lesArbiChaises.add(a);
            }
        }
        return lesArbiChaises;
        
    }
    
    public ArrayList<Arbitres> findAllLigne(){
        lesArbiLignes.removeAll(lesArbiLignes);
        ArrayList<Arbitres> ListeTmp = DbArbitreDAO.lesArbitres;
        for(Arbitres a : ListeTmp) {
            if(a.getCategorie()!=null){
                lesArbiLignes.add(a);
            }
        }
        return lesArbiLignes;
    }
}
