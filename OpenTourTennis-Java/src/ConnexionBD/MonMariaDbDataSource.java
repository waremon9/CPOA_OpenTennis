/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.mariadb.jdbc.MariaDbDataSource;

/**
 *
 * @author p1805285
 */
public class MonMariaDbDataSource extends MariaDbDataSource{
    private static MonMariaDbDataSource mds;
/**
* constructeur privé vide, utilisé dans getMdbDataSource(), c’est juste pour dire qu’il est privé
*/
private MonMariaDbDataSource () {
}
    /**
    * Méthode statique qui renvoie l'unique instance de MonMariaDbDataSource construite à partir du fichier de proprietes
    * @return une instance de MariaDbDataSource:
     * @throws java.sql.SQLException
    */
    public static MonMariaDbDataSource getMdbDataSource() throws SQLException {
        // contrôle si un datasource n'existe pas deja
        if (mds == null) {
            Properties prop = new Properties();
            FileInputStream fichier = null;
            
        try {
            fichier = new FileInputStream("src/ConnexionBD/connexionMariaIUT.properties");
        } catch (FileNotFoundException ex1) {
            System.out.println("Fichier de proprietes non trouvé");
        }
        
        try {
            prop.load(fichier);
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement du fichier de proprietes mySQL");
            
        } finally {
            
            try {
                fichier.close();
            } catch (IOException ex) {
                System.out.print("Problème d'entree/sortie" + ex.getMessage());
            }
            
        }
        
        mds = new MonMariaDbDataSource ();
        mds.setPortNumber(new Integer(prop.getProperty("port")));
        mds.setServerName(prop.getProperty("serveur"));
        mds.setDatabaseName(prop.getProperty("base"));
        mds.setUser(prop.getProperty("user"));
        mds.setPassword(prop.getProperty("pwd"));
        // pas de service à définir pour MariaDB
        } else System.out.println("---(la source de data existe deja)") ;
        return mds;
        
    } // de getMdbDataSource()
}
