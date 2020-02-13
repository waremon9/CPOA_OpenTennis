/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import Entities.Arbitres;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author goncalve
 */
public interface IArbitreDAO {
    public List<Arbitres> findAll() throws SQLException;
    public Arbitres findById(int noJoueurs) throws SQLException;
}
