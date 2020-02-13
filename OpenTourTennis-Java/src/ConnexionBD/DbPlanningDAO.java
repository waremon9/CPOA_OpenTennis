/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import Entities.Matchs.Planning;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dupor
 */
public class DbPlanningDAO implements IPlanningDAO{

    private final Connection connexionBD;
    public static ArrayList<Planning> lesPlannings = new ArrayList();

    public DbPlanningDAO(Connection c) throws SQLException {
        this.connexionBD = c;
        lesPlannings = this.findAll();
    }
    
    @Override
    public ArrayList<Planning> findAll() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rset;
        ArrayList<Planning> lp = new ArrayList();
        Planning p;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM Planning ORDER BY ID");
            rset = pst.executeQuery();
            while (rset.next()) {
                p = new Planning(rset.getString(2),rset.getString(3),rset.getInt(4),rset.getInt(5),rset.getBoolean(6),rset.getBoolean(7),rset.getBoolean(8));
                lp.add(p);
            }
            if(lp == null)
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
        return lp;
    }

    @Override
    public Planning findById(int noPlanning) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rset;
        Planning lp;
        Planning p;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM Planning WHERE ID = ? ORDER BY ID");
            pst.setInt(1, noPlanning);
            rset = pst.executeQuery();
            rset.next();
                p = new Planning(rset.getString(2),rset.getString(3),rset.getInt(4),rset.getInt(5),rset.getBoolean(6),rset.getBoolean(7),rset.getBoolean(8));
                lp = p;
            if(lp == null)
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
        return lp;
    }

    @Override
    public void ajouterPlanning(Planning p) throws SQLException {
        PreparedStatement pst = null;
        try {
            pst = connexionBD.prepareStatement("INSERT INTO Planning (Nom,DateDebut,Duree,NbJoueur,Simple,Doubles,Effectue) VALUES(?,?,?,?,?,?,?)");
            
            pst.setString(1, p.getNom());
            pst.setString(2, p.getDateDeb());
            pst.setInt(3, p.getDuree());
            pst.setInt(4, p.getNbJoueur());
            pst.setBoolean(5, p.isSimple());
            pst.setBoolean(6, p.isDouble());
            pst.setBoolean(7, p.isEffectue());
            pst.executeQuery();

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
    }
    
    public int getNbPl() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rset;
        Planning lp;
        try {
            pst = connexionBD.prepareStatement("SELECT COUNT(*) FROM Planning");
            rset = pst.executeQuery();
            rset.next();
            return rset.getInt(1);

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
    }
    
}
