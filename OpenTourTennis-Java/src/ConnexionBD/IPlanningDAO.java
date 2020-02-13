/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import Entities.Matchs.Planning;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dupor
 */
public interface IPlanningDAO {
    public List<Planning> findAll() throws SQLException;
    public Planning findById(int noPlanning) throws SQLException;
    public void ajouterPlanning(Planning p) throws SQLException;
}
