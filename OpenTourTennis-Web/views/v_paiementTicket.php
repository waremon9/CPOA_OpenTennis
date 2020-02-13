<?php
	require_once(PATH_MODELS.'Connexion.php');
	require_once(PATH_MODELS.'MatchsDAO.php');
	require_once(PATH_MODELS.'TicketTypeDAO.php');

	$MatchsDAO = new MatchsDAO(true);
	$TicketTypeDAO = new TicketTypeDAO(true);
?>

<html>	
	<head>
		<title> Achat Billet </title>
		<link href="<?= PATH_CSS ?>bootstrap.css" rel="stylesheet"> 
		<link href="<?= PATH_CSS ?>css.css" rel="stylesheet">
	</head>
	<body>
		<form action="index.php?page=paiementTicket" method="POST">
			<?php
			require_once(PATH_VIEWS.'menu.php');
			if(isset($_POST['codePromo'])){	
				$_SESSION['codePromo'] = $_POST['codePromo'];
			}
			$_SESSION['zone'] = $_POST['zone'];
			?>

			<div class="achatTicket">
				<h2> Recapitulatif de l'achat : </h2><br>
				<h4>
					<?php
					echo 'NOM : '.$_SESSION['lastname'].'<br>';
					echo 'PRENOM : '.$_SESSION['firstname'].'<br>';
					echo 'ADRESSE EMAIL : '.$_SESSION['email'].'<br><br>';

					echo 'MATCH : '.$_SESSION['Matchs'].'<br>';
					echo 'ZONE : '.$_SESSION['zone'].'<br>';
					echo 'TICKET : '.$_SESSION['ticketName'].'<br>';
					echo 'CODE PROMOTION : ';
					if(isset($_SESSION['codePromo'])){
						echo $_SESSION['codePromo'];
					}
					
					?>
				</h4>
				<input type="submit" value="ACHETER">
			</div>


</html>