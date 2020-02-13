<?php 
	require_once(PATH_MODELS.'TicketTypeDAO.php');

	$TicketTypeDAO = new TicketTypeDAO(true);

	if(isset($_POST['codePromo'])){
		if($_POST['zone'] == NULL){
			require_once(PATH_CONTROLLERS."achatTicket.php");
		}

		$promoByName = $TicketTypeDAO->getPromoName($_SESSION['ticketName']);

		if($promoByName == $_POST['codePromo'] and $_POST['zone'] != "--"){
			require_once(PATH_VIEWS.$page.".php");
		}else{
			
			echo '<input type = "hidden" name = "lastname" value = "'.$_SESSION['lastname'].'" />';
			echo '<input type = "hidden" name = "firstname" value = "'.$_SESSION['firstname'].'" />';
			echo '<input type = "hidden" name = "idMatch" value = "'.$_SESSION['idMatch'].'" />';
			echo '<input type = "hidden" name = "ticketName" value = "'.$_SESSION['ticketName'].'" />';

			header('Location: index.php?page=achatTicket');
		}
	}else{
		require_once(PATH_VIEWS.$page.".php");
	}
	

	
?>