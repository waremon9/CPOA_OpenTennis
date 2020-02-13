<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Joueur.php');

class JoueurDAO extends DAO
{
    public function getPlayerFromMatch($idMatch)
    {
    	$requete = $this->queryAll("SELECT Nom, Prenom FROM Participation_Matchs, Joueur WHERE idM = ".$idMatch." and idJ = ID");

    	return $requete;
    }
}
?>