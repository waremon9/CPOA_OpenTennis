/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import Entities.Matchs.Match;
import Entities.Matchs.Planning;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dupor
 */
public interface IMatchDAO {
    public List<Match> findAllByIdPl(int idP) throws SQLException;
    public Match findById(int noJoueurs) throws SQLException;
    public boolean ajouterMatch(Match m, Planning p) throws SQLException;
}
