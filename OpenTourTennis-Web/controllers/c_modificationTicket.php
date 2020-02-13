
<?php
	require_once(PATH_MODELS.'TicketTypeDAO.php');
	require_once(PATH_MODELS.'Connexion.php');

	if(isset($_POST['ticketName'], $_POST['ticketPrice'], $_POST['remainingTicket'])){
		if($_POST['ticketPrice'] != " " || $_POST['remainingTicket'] != " "){

			$TicketTypeDAO = new TicketTypeDAO(true);

			$name = $_POST['ticketName'];
			$price = $_POST['ticketPrice'];
			$remaining = $_POST['remainingTicket'];
			echo '<script>console.log("DOOONE")</script>';
			
			$TicketTypeDAO->alterTicket($name, $price, $remaining);

			REQUIRE_ONCE(PATH_VIEWS.'parametrage.php');
		}else{
			REQUIRE_ONCE(PATH_VIEWS.'parametrage.php');
			echo '<script>console.log("valeurs nulles")</script>';
		}
	}else{
		REQUIRE_ONCE(PATH_VIEWS.'accueil.php');
		echo '<script>console.log("pas de isset")</script>';

	}



?>