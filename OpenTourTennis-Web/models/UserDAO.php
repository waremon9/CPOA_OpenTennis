<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'User.php');

class UserDAO extends DAO
{
    public function connectUser($userEmail, $userPassword)
    {
            
        if($userEmail !== "" && $userPassword !== "") 
        {
            $requete = $this->queryRow("SELECT email FROM utilisateur where 
                    email = '".$userEmail."' and password = '".$userPassword."' ");
                
            return $requete;
        }
        
    }

    public function userExist($userEmail)
    {
    	$requete = $this->queryRow("SELECT * FROM utilisateur where
    		LOWER(email) = LOWER(?)",array($userEmail));
    	if($requete[0] != null){
    		return 1;
    	} else {
    		return 0;
    	}
    }
    //
    public function createUser($userLastname, $userFirstname, $userEmail, $userPassword, $type)
    {
    	$req = 'INSERT INTO utilisateur(lastname, firstname, email, password, userType) VALUES(?,?,?,?,?)';
        $bdd = Connexion::getInstance()->getBdd()->prepare($req);
    	$result = $bdd->execute(array($userLastname, $userFirstname, $userEmail, $userPassword, $type));
    	return 1;
    }

    public function getUserType($userEmail)
    {
        $req = $this->queryRow("SELECT userType FROM utilisateur WHERE email = ?", array($userEmail));

        return $req[0];
    }
}
?>