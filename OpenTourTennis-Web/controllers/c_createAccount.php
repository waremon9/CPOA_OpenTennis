<?php
	require_once(PATH_MODELS.'UserDAO.php');
	require_once(PATH_MODELS.'Connexion.php');

	$UserDAO = new UserDAO(true);

	if(isset($_POST['lastname'], $_POST['firstname'], $_POST['email'], 
		$_POST['password'])){
		$exist = $UserDAO->userExist($_POST['email']);
		
		if($exist != 0){
			$_POST['mailExist'] = 1;
			REQUIRE_ONCE(PATH_VIEWS.'signin.php');
		}else{
			$lastN = $_POST['lastname'];
			$firstN =  $_POST['firstname'];
			$eMail = $_POST['email'];
			$pass = $_POST['password'];
			$type = "visiteur";

			$_SESSION['email'] = $eMail;
				
			$UserDAO->createUser($lastN, $firstN, $eMail, $pass, $type);
			REQUIRE_ONCE(PATH_VIEWS.'accueil.php');
			
		}

	}
	
?>