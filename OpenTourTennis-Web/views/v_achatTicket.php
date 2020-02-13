<?php
	require_once(PATH_MODELS.'TicketTypeDAO.php');
	require_once(PATH_MODELS.'Connexion.php');
	require_once(PATH_MODELS.'MatchsDAO.php');

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
			$_SESSION['pageAchat'] = 1;
			
			require_once(PATH_VIEWS.'menu.php');
			?>

			<div class="blockbilletterie">
				<!--  Zone message d'alerte -->
				<?php require_once(PATH_VIEWS.'alert.php');?>

				<!--  Début de la page -->
				<h1 class="billetterie">Achat Ticket</h1>

				<?php 
					if(isset($_POST['lastname'], $_POST['firstname'], $_POST['Matchs'], $_POST['ticketName'])){
						$_SESSION['lastname'] = $_POST['lastname'];
						$_SESSION['firstname'] = $_POST['firstname'];
						$_SESSION['Matchs'] = $_POST['Matchs'];
						$_SESSION['ticketName'] = $_POST['ticketName'];
					}else{
						$_POST['lastname'] = $_SESSION['lastname'];
						$_POST['firstname'] = $_SESSION['firstname'];
						$_POST['Matchs'] = $_SESSION['Matchs'];
						$_POST['ticketName'] = $_SESSION['ticketName'];
					}

					if(isset($_POST['ticketName'])){
						$ticketPromo = $TicketTypeDAO->getPromoName($_POST['ticketName']);
						echo '<h2>Ticket : '.$_POST['ticketName'].'</h2>';

						if($ticketPromo != null){
							echo "<h3> 1) Code promotion : </h3>";
							echo "<input type='text' placeholder='Entrer votre code de promotion' name='codePromo' ";
							if(isset($_SESSION['pageAchat']) and isset($_SESSION['codePromo'])){
								if($_SESSION['codePromo'] == $ticketPromo){
									echo 'value="'.$_SESSION['codePromo'].'"';
								}
							}
							echo "required>";
							echo "<h3> 2) Selectionner la zone </h3>";
						}else{
							echo "<h3> 1) Selectionner la zone </h3>";
						}		
				}
				?>

				<?php
					$listeZone = array("--", "Placement libre", "A", "B", "C", "E", "F", "G", "H", "I", "J", "K", "L", "N", "Rez-de-chaussee");
				?>

						<select id="zone" name="zone">

							<?php

							for($i = 0; $i < sizeof($listeZone); $i++){
								echo '<option value="'.$listeZone[$i].'" ';
								if(isset($_SESSION['zone'])){
									if($_SESSION['zone'] == $listeZone[$i]){
										echo ' selected ';
									}
								}
								echo '>'.$listeZone[$i].'</option>';
							}
							?>
						</select>
				
				<input type="submit" value="Acheter">
			</body>
		</div>
	</form>

	<script src="./assets/scripts/selectShow.js"></script>

</html>