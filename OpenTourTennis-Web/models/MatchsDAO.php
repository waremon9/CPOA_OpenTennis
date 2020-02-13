<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Matchs.php');

class MatchsDAO extends DAO
{
	public function getNbMatchs()
	{
		$requete = $this->queryRow("SELECT count(*) FROM Matchs");
                
        return $requete[0];
	}

	public function getIdMatch()
	{
		$idMatch = array();
		$requete = $this->queryAll("SELECT * FROM Matchs");

		foreach($requete as $row){
			$idMatch[] = $row['idM'];
		}

        return $idMatch;
	}

	public function getNoCourt($idMatch)
	{
		$requete = $this->queryAll('SELECT noCourt FROM Matchs WHERE idM = "'.$idMatch.'"');

		return $requete[0][0];
	}
}
?>