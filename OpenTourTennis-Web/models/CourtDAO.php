<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Court.php');

class CourtDAO extends DAO
{
    public function getNbCourt()
	{
		$requete = $this->queryRow("SELECT count(*) FROM Court");
                
        return $requete[0];
	}

	public function getNoCourt()
	{
		$noCourt = array();
		$requete = $this->queryAll("SELECT * FROM Court");

		foreach($requete as $row){
			$noCourt[] = $row['noCourt'];
		}

        return $noCourt;
	}
}
?>