/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*ALTER TABLE Arbitre_Match ADD CONSTRAINT FK_ID_Arbitre PRIMARY KEY (Arbitre.ID);*/

package ConnexionBD;

import Entities.Arbitres;
import Entities.Duo;
import Entities.Enum.Court;
import Entities.Enum.Horaire;
import Entities.Enum.Jour;
import Entities.Enum.Type_Match;
import Entities.Joueurs;
import Entities.Matchs.Doubles;
import Entities.Matchs.Match;
import Entities.Matchs.Planning;
import Entities.Matchs.Simple;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dupor
 */
public final class DbMatchDAO implements IMatchDAO {

    Connection connexionBD;
    public static ArrayList<Match> lesMatchs = new ArrayList();
    public static ArrayList<Simple> lesSimples = new ArrayList();
    public static ArrayList<Doubles> lesDoubles = new ArrayList();
    
    public DbMatchDAO(Connection c, int idP) throws SQLException{
            this.connexionBD = c;
            if(lesMatchs.isEmpty()){
                lesMatchs = this.findAllByIdPl(idP);
            }
            for(Match m : lesMatchs){
                if(m instanceof Simple){
                    lesSimples.add((Simple) m);
                } else if(m instanceof Doubles){
                    lesDoubles.add((Doubles) m);
                }
            }
    }
    
    @Override
    public ArrayList<Match> findAllByIdPl(int idP) throws SQLException {
        ArrayList<Arbitres> lesRamasseurs = new ArrayList();
        ArrayList<Arbitres> lesALignes = new ArrayList();
        ArrayList<Match> listeMatchs = new ArrayList();
        PreparedStatement pst = null;
        ResultSet rsetRelation;
        ResultSet rsetMatch;
        Arbitres aChaise;
        Type_Match tm;
        Arbitres A;
        Horaire h;
        Court c ;
        Match m;
        Jour j;
        try {
            pst = connexionBD.prepareStatement("SELECT * FROM MatchsT WHERE idPlanning = ? ORDER BY idM");
            pst.setInt(1,idP);
            rsetMatch = pst.executeQuery();
            
            while (rsetMatch.next()) {
                
                c = Court.getCourt(rsetMatch.getString(2));
                j = Jour.getJour(rsetMatch.getString(4));;
                h = Horaire.getHoraire(rsetMatch.getString(5));
                tm = Type_Match.getTypeMatch(rsetMatch.getString(6));
                
                //Recuperer l'arbitre de chaise
                pst = connexionBD.prepareStatement("SELECT Nom, Prenom FROM ArbitreChaise_Match, Arbitre where idM = ? and idAC = ID");
                pst.setInt(1,rsetMatch.getInt(1));
                rsetRelation = pst.executeQuery();
                rsetRelation.next();
                aChaise = Arbitres.getArbitre(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                
                //Récupérer la liste des arbitres de ligne
                pst = connexionBD.prepareStatement("SELECT Nom, Prenom FROM ArbitreLigne_Match, Arbitre where idM = ? and idAL = ID");
                pst.setInt(1,rsetMatch.getInt(1));
                rsetRelation = pst.executeQuery();
                while (rsetRelation.next()) {
                    A = Arbitres.getArbitre(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                    lesALignes.add(A);
                }
                
                //Recuperer la liste des Ramasseurs
                pst = connexionBD.prepareStatement("SELECT Nom, Prenom FROM Ramasseur_Match, Arbitre where idM = ? and idR = ID");
                pst.setInt(1,rsetMatch.getInt(1));
                rsetRelation = pst.executeQuery();
                while (rsetRelation.next()) {
                    A = Arbitres.getArbitre(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                    lesRamasseurs.add(A);
                }
                //Enregistrer le Match
                if("Simple".equals(rsetMatch.getString(3))){
                    
                    //Recuperer les deux joueurs
                    pst = connexionBD.prepareStatement("SELECT Nom, Prenom FROM Participation_MatchsSimple, Joueur where idM = ? and idJ = ID");
                    pst.setInt(1,rsetMatch.getInt(1));
                    rsetRelation = pst.executeQuery();
                    rsetRelation.next();
                    Joueurs J1 = Joueurs.getJoueur(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                    rsetRelation.next();
                    Joueurs J2 = Joueurs.getJoueur(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                    Simple s = new Simple(J1, J2, c, tm, h, j, aChaise, lesALignes, lesRamasseurs);
                    listeMatchs.add(s);
                } else {
                    //Recuperer les deux duos
                    pst = connexionBD.prepareStatement("SELECT Nom, Prenom FROM Participation_MatchsDuo, Duo, Joueur where idM = ? and (idJoueur1 = ID or idJoueur2 = ID)");
                    pst.setInt(1,rsetMatch.getInt(1));
                    rsetRelation = pst.executeQuery();
                    rsetRelation.next();
                    Duo D1 = Duo.getDuo(rsetRelation.getString(1)+" "+rsetRelation.getString(2));
                    rsetRelation.next();
                    Duo D2 = Duo.getDuo(rsetRelation.getString(3)+" "+rsetRelation.getString(4));
                    
                    Doubles d = new Doubles(D1, D2, c, tm, h, j, aChaise, lesALignes, lesRamasseurs);
                    listeMatchs.add(d);
                }
            }
            if(listeMatchs == null)
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
        return listeMatchs;
    }

    @Override
    public Match findById(int noMatch) throws SQLException {
        if(!lesMatchs.isEmpty()){
            return lesMatchs.get(noMatch-1);
        } else {
            return null;
        }
    }

    @Override
    public boolean ajouterMatch(Match m, Planning p) throws SQLException {
        
        PreparedStatement pstMatchsT = null;
        PreparedStatement pstArbiChaise = null;
        PreparedStatement pstArbiLigne = null;
        PreparedStatement pstRamasseur = null;
                
            pstMatchsT = connexionBD.prepareStatement("INSERT INTO MatchsT VALUES(?,?,?,?,?,?,?)");
            
            pstArbiChaise = connexionBD.prepareStatement("INSERT INTO ArbitreChaise_Match VALUES(?,?)");
            pstArbiChaise.setInt(1, m.getId());
            pstArbiChaise.setInt(2, m.getId());
            pstArbiChaise.executeQuery();
            
            pstArbiLigne = connexionBD.prepareStatement("INSERT INTO ArbitreLigne_Match VALUES(?,?)");
            pstArbiLigne.setInt(2, m.getId());
            for(Arbitres a : m.getaLigne()){
                pstArbiLigne.setInt(1, a.getId());
                pstArbiLigne.executeQuery();
            }
            
            pstRamasseur = connexionBD.prepareStatement("INSERT INTO Ramasseur_Match VALUES(?,?)");
            pstRamasseur.setInt(2, m.getId());
            for(Arbitres a : m.getRamasseur()){
                pstRamasseur.setInt(1, a.getId());
                pstRamasseur.executeQuery();
            }
            
            pstMatchsT.setInt(1, m.getId());
            pstMatchsT.setInt(2, p.getId());
            pstMatchsT.setString(3, m.getC().toString());
            if(m instanceof Simple){
                lesSimples.add((Simple) m);
                this.insererJoueurs(((Simple) m).getJ1(), m);
                this.insererJoueurs(((Simple) m).getJ2(), m);
                pstMatchsT.setString(4, "Simple");
            } else if(m instanceof Doubles){
                lesDoubles.add((Doubles) m);
                this.insererDuos(((Doubles) m).getD1(), m);
                this.insererDuos(((Doubles) m).getD2(), m);
                pstMatchsT.setString(4, "Double");
            }
            pstMatchsT.setString(5, m.getJ().toString().split("j")[1]);
            pstMatchsT.setString(6, m.getH().toString().split("h")[1]);
            pstMatchsT.setString(7, m.getTm().toString());
            pstMatchsT.executeQuery();
            
            if (pstMatchsT != null) {
                pstMatchsT.close();
            }
            if (pstArbiChaise != null) {
                pstArbiChaise.close();
            }
            if (pstArbiLigne != null) {
                pstArbiLigne.close();
            }
            if (pstRamasseur != null) {
                pstRamasseur.close();
            }
            return true;
    }
    
    public void insererJoueurs(Joueurs j, Match m) throws SQLException{
        PreparedStatement pstParti_Duo = null;
        try{
            pstParti_Duo = connexionBD.prepareStatement("INSERT INTO Participation_MatchsSimple VALUES(?,?)");
            pstParti_Duo.setInt(1, j.getId());
            pstParti_Duo.setInt(2, m.getId());
            pstParti_Duo.executeQuery();
        } finally {
            if(pstParti_Duo!=null){
                pstParti_Duo.close();
            }
        }
    }
    
    public void insererDuos(Duo d, Match m) throws SQLException{
        PreparedStatement pstParti_Duo = null;
        try{
            pstParti_Duo = connexionBD.prepareStatement("INSERT INTO Participation_MatchsDuo VALUES(?,?)");
            pstParti_Duo.setInt(1, d.getId());
            pstParti_Duo.setInt(2, m.getId());
            pstParti_Duo.executeQuery();
        } finally {
            if(pstParti_Duo!=null){
                pstParti_Duo.close();
            }
        }
    }
    
    public Simple findSimpleByPlayer(String p1nom, String p1prenom, String p2nom, String p2prenom){
        Simple renvoie = null;
        for(Match m:lesMatchs){
            if(m instanceof Simple){
                if(((Simple) m).getJ1().getNom().equals(p1nom) && ((Simple) m).getJ1().getPrenom().equals(p1prenom) && 
                   ((Simple) m).getJ2().getNom().equals(p2nom) && ((Simple) m).getJ2().getPrenom().equals(p2prenom)){
                    renvoie = (Simple) m;
                }
            }
        }
        return renvoie;
    }
        
    public Doubles findDoubleByDuos(String duo1, String duo2){
        Doubles renvoie = null;
        for(Match m:lesMatchs){
            if(m instanceof Doubles){
                if(((Doubles) m).getD1().compositionDuo().equals(duo1) && ((Doubles) m).getD2().compositionDuo().equals(duo2)){
                    renvoie = (Doubles) m;
                }
            }
        }
        return renvoie;
    }
    
    public void supprimerMatch(Match m, Planning p) throws SQLException {
        
        PreparedStatement pstMatchsT = null;;
        PreparedStatement pstArbiChaise = null;
        PreparedStatement pstArbiLigne = null;
        PreparedStatement pstRamasseur = null;
        PreparedStatement pstMatchsSimple = null;
        
            lesMatchs.remove(m);
                
            pstMatchsT = connexionBD.prepareStatement("DELETE FROM MatchsT where idM = ?");
            pstMatchsT.setInt(1, m.getId());
            
            pstArbiChaise = connexionBD.prepareStatement("DELETE FROM ArbitreChaise_Match where idM = ?");
            pstArbiChaise.setInt(1, m.getId());
            
            pstArbiLigne = connexionBD.prepareStatement("DELETE FROM ArbitreLigne_Match where idM = ?");
            pstArbiLigne.setInt(1, m.getId());
            
            pstRamasseur = connexionBD.prepareStatement("DELETE FROM Ramasseur_Match where idM = ?");
            pstRamasseur.setInt(1, m.getId());
            
            pstMatchsSimple = connexionBD.prepareStatement("DELETE FROM Participation_MatchsSimple where idM = ?");
            pstMatchsSimple.setInt(1, m.getId());
            
            pstArbiChaise.executeQuery();
            pstArbiLigne.executeQuery();
            pstRamasseur.executeQuery();
            pstMatchsT.executeQuery();
            
            if (pstMatchsT != null) {
                pstMatchsT.close();
            }
            if (pstArbiChaise != null) {
                pstArbiChaise.close();
            }
            if (pstArbiLigne != null) {
                pstArbiLigne.close();
            }
            if (pstRamasseur != null) {
                pstRamasseur.close();
            }
        }    
    
    public void modifierMatch(Match m, Planning p) throws SQLException {
        
        PreparedStatement pstMatchsT = null;;
        PreparedStatement pstArbiChaise = null;
        PreparedStatement pstArbiLigne = null;
        PreparedStatement pstRamasseur = null;
        
        
        pstMatchsT = connexionBD.prepareStatement("UPDATE MatchsT SET noCourt = ?, categorieMatch = ?, jourTournoi = ?, horaire = ?, rangTournoi = ? WHERE idM = ? AND idPlanning = ?");

        pstArbiChaise = connexionBD.prepareStatement("UPDATE ArbitreChaise_Match SET idAC = ? WHERE idAC = ?");
        pstArbiChaise.setInt(1, m.getId());
        pstArbiChaise.setInt(2, m.getId());
        pstArbiChaise.executeQuery();

        pstArbiLigne = connexionBD.prepareStatement("UPDATE ArbitreLigne_Match SET idAL = ? WHERE idAL = ?");
        pstArbiLigne.setInt(2, m.getId());
        for(Arbitres a : m.getaLigne()){
            pstArbiLigne.setInt(1, a.getId());
            pstArbiLigne.executeQuery();
        }

        pstRamasseur = connexionBD.prepareStatement("UPDATE Ramasseur_Match SET idR = ? WHERE idR = ?");
        pstRamasseur.setInt(2, m.getId());
        for(Arbitres a : m.getRamasseur()){
            pstRamasseur.setInt(1, a.getId());
            pstRamasseur.executeQuery();
        }

        pstMatchsT.setInt(6, m.getId());
        pstMatchsT.setString(1, m.getC().toString());
        if(m instanceof Simple){
            pstMatchsT.setString(2, "Simple");
        } else if(m instanceof Doubles){
            pstMatchsT.setString(2, "Double");
        }
        pstMatchsT.setString(3, m.getJ().toString().split("j")[1]);
        pstMatchsT.setString(4, m.getH().toString().split("h")[1]);
        pstMatchsT.setString(5, m.getTm().toString());
        pstMatchsT.setInt(7, p.getId());
        pstMatchsT.executeQuery();

        if (pstMatchsT != null) {
            pstMatchsT.close();
        }
        if (pstArbiChaise != null) {
            pstArbiChaise.close();
        }
        if (pstArbiLigne != null) {
            pstArbiLigne.close();
        }
        if (pstRamasseur != null) {
            pstRamasseur.close();
        } 
    }
    
    public int getNbMa() throws SQLException {
        PreparedStatement pst = null;
        ResultSet rset;
        Planning lp;
        try {
            pst = connexionBD.prepareStatement("SELECT COUNT(*) FROM MatchsT");
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
