/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import Entities.Duo;
import Entities.Joueurs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author verhi
 */
public final class DbDuoDAO {
    
    private final Connection connexionBD;
    DbJoueurDAO BDJ;
    public static ArrayList<Duo> lesDuos = new ArrayList();

    public DbDuoDAO(Connection c, DbJoueurDAO BDJ) throws SQLException {
        this.connexionBD = c;
        this.BDJ = BDJ;
        if(lesDuos.isEmpty()){
            lesDuos = this.findAll();
        }
    }

    /**
     *
     * @param noDuo
     * @return
     * @throws SQLException
     */
    
    public Duo findById(int noDuo) throws SQLException{
        Duo d = null;
        for(Duo tmp : lesDuos){
            if(tmp.getId()==noDuo){
                d= tmp;
            }
        }
        return d;
    }

    public ArrayList<Duo> findAll() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rset;
        ArrayList<Duo> lm = new ArrayList();
        Duo d = null;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM Duo ORDER BY IdDuo");
            rset = pst.executeQuery();
            while (rset.next()) {
                d = new Duo(BDJ.findById(Integer.parseInt(rset.getString(2))), BDJ.findById(Integer.parseInt(rset.getString(3))));
                lm.add(d);
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
    
    public ArrayList<Duo> findAllButOne(String Ds) throws SQLException {
        ArrayList<Duo> lm = new ArrayList();
        Duo d = Duo.getDuo(Ds);
        for(Duo tmp : lesDuos){
            if(tmp.compareTo(d)!=0){
                lm.add(tmp);
            }
        }
        return lm;
    }  
}
