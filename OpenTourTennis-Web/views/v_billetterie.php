<?php
	require_once(PATH_MODELS.'MatchsDAO.php');
	require_once(PATH_MODELS.'JoueurDAO.php');
	require_once(PATH_MODELS.'TicketTypeDAO.php');
	require_once(PATH_MODELS.'Connexion.php');

	$MatchsDAO = new MatchsDAO(true);
	$JoueurDAO = new JoueurDAO(true);
	$TicketTypeDAO = new TicketTypeDAO(true);
?>
<div class="blockbilletterie">
	<form action="index.php?page=achatTicket" method="POST">
		<!-- NOM et PRENOM-->
		<h4> 1) Entrez votre nom et votre prénom </h4> </br>	
		<?php
			echo '<input type="text" placeholder="Entrer votre nom" name="lastname" ';
			if(isset($_SESSION['pageAchat'])){
				echo 'value="'.$_SESSION['lastname'].'"';
			}
			echo ' required>';
			echo '<input type="text" placeholder="Entrer votre prénom" name="firstname" ';
			if(isset($_SESSION['pageAchat'])){
				echo 'value="'.$_SESSION['firstname'].'"';
			}
			echo ' required>';
		?>
		<!-- SELECTION MATCH -->
		<h4> 2) Sélectionnez votre match </h4> </br>
		<div class="custom-select" style="width:200px;">
			<label> Choisissez un match:
				<select name="Matchs"> 
					<?php 
						$idMatch = array();
						$playerName = array();

						$nbMatchs = $MatchsDAO->getNbMatchs();	
						$idMatch = $MatchsDAO->getIdMatch();

						for($i = 0; $i < $nbMatchs[0]; $i++){
							$playerName = $JoueurDAO->getPlayerFromMatch($idMatch[$i]);
							$fullName = $playerName[0][0].' '.$playerName[0][1]
							.' VS '.
							$playerName[1][0].' '.$playerName[1][1];

							echo '<option value="'.$fullName.'" name="idMatch"'; 
							if(isset($_SESSION['pageAchat'])){
								if($_SESSION['Matchs'] == $idMatch){
									echo 'selected';
								}
							}
							echo '>';
							echo $fullName.'</option>';
						}
					?>
				</select>
			</label>
		</div>
		<!-- RADIO BOX (Info Billets) -->
		<h4> 3) Choisissez votre ticket </h4> </br>
	</div>
	<div class="ticket">
		<?php
			$nbTypeTicket = $TicketTypeDAO->nbTypeTicket();
			$ticketName = $TicketTypeDAO->TicketName();
			$ticketPrice = $TicketTypeDAO->TicketPrice();
			$remainingTicket =$TicketTypeDAO->remainingTicket();

			for($i = 0; $i < $nbTypeTicket[0]; $i++){ ?>
			<div class="ticketType">
				<?php echo $ticketName[$i]."</br>" ?>
				<?php echo $ticketPrice[$i]."€ </br>" ?>
				<?php echo "Restant :".$remainingTicket[$i]."</br>" ?>
				<?php echo '<input type="radio" name="ticketName" value="'.$ticketName[$i].'" required>';?>	
			</div>
		<?php } ?>

		
	<input type="submit" value="ACHETER">
	</form>

</div>

