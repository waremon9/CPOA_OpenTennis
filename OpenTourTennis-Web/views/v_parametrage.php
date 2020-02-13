<?php
	require_once(PATH_MODELS.'JoueurDAO.php');
	require_once(PATH_MODELS.'TicketTypeDAO.php');
	require_once(PATH_MODELS.'Connexion.php');

	$JoueurDAO = new JoueurDAO(true);
	$TicketTypeDAO = new TicketTypeDAO(true);
?>

<html>	
	<head>
		<title> PARAMETRAGE </title>
		<link href="<?= PATH_CSS ?>bootstrap.css" rel="stylesheet"> 
		<link href="<?= PATH_CSS ?>css.css" rel="stylesheet">
	</head>
	<body>

		<?php require_once(PATH_VIEWS.'menu.php');?>

		<!--  Zone message d'alerte -->
		<?php require_once(PATH_VIEWS.'alert.php');?>

		<!--  Début de la page -->

		<h1 class="billetterie"> Paramétrages </h1>

		<form action="index.php?page=modificationTicket" method="POST">
			<div class="ticket">
				<?php
					$nbTypeTicket = $TicketTypeDAO->nbTypeTicket();
					$ticketName = $TicketTypeDAO->TicketName();
					$ticketPrice = $TicketTypeDAO->TicketPrice();
					$remainingTicket =$TicketTypeDAO->remainingTicket();

					for($i = 0; $i < $nbTypeTicket[0]; $i++){ 
						echo '<div class="ticketTypePara">';
						echo '<input type="hidden" name ="ticketName" placeholder="'.$ticketName[$i].'" >'.$ticketName[$i].'</br>'; 
						echo '<input type="text" name="ticketPrice" placeholder="'.$ticketPrice[$i].'€"></br>'; 
						echo '<input type="text" name="remainingTicket" placeholder="Restant : '.$remainingTicket[$i].'"></br>'; 
						echo '<input type="submit" value="MODIFIER" required>';	
						echo '</div>';
				} ?>

			</div>
		</form>
		

	</body>


</html>