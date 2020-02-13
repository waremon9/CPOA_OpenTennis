/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import java.sql.SQLException;
import java.util.List;
import Entities.Joueurs;

/**
 *
 * @author goncalve
 */
public interface IJoueurDAO {
    public List<Joueurs> findAll() throws SQLException;
    public Joueurs findById(int noJoueurs) throws SQLException;
}
