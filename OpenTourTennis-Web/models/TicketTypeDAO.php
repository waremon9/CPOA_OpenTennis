<?php
require_once(PATH_MODELS.'DAO.php');
require_once(PATH_ENTITY.'Ticket_type.php');


class TicketTypeDAO extends DAO
{
	public function nbTypeTicket(){

		$req = $this->queryRow('SELECT count(*) FROM Ticket_Type');

		return $req;
	} 
	public function ticketName(){
		$ticketName = array();
		$requete = $this->queryAll("SELECT * FROM Ticket_Type");

		foreach($requete as $row){
			$ticketName[] = $row['type'];
		}

        return $ticketName;
	}

	public function ticketPrice(){
		$ticketPrice = array();
		$requete = $this->queryAll("SELECT * FROM Ticket_Type");

		foreach($requete as $row){
			$ticketPrice[] = $row['prix'];
		}

        return $ticketPrice;
	}

	public function remainingTicket(){
		$remainingTicket = array();
		$requete = $this->queryAll("SELECT * FROM Ticket_Type");

		foreach($requete as $row){
			$remainingTicket[] = $row['nb_billet'];
		}

        return $remainingTicket;
	}

	public function getPromoName($name){
		$requete = $this->queryAll('SELECT code_promo FROM Ticket_Type WHERE type = "'.$name.'"');

		return $requete[0][0];
	} 

	public function alterTicket($name, $price, $remaining)
    {
    	$req = 'UPDATE Ticket_Type
			SET prix = "'.$price.'", nb_Billet = "'.$remaining.'" 
			WHERE type = "'.$name.'"';
        $bdd = Connexion::getInstance()->getBdd()->prepare($req);
    	$result = $bdd->execute();
    	return 1;
    }

}