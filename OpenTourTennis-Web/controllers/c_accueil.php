<?php
	require_once(PATH_MODELS.'UserDAO.php');
	require_once(PATH_MODELS.'Connexion.php');

	$UserDAO = new UserDAO(true);

	if(isset($_POST['deconnexion'], $_SESSION)){
		if($_POST['deconnexion'] == 1){
			session_destroy(); 
			$_POST['deconnexion'] = 0;
			REQUIRE_ONCE(PATH_VIEWS.'accueil.php');
		}
	}

	if(isset($_POST['email']) && isset($_POST['password']))
	{

	   $result = $UserDAO->connectUser($_POST['email'], $_POST['password']);
	   if(isset($result)){
	   		if($result != null){
		   		$_SESSION['email'] = $result[0];
		   		$_SESSION['deconnexion'] = 0;
		   		$_SESSION['type'] = $UserDAO->getUserType($result[0]);
		   		REQUIRE_ONCE(PATH_VIEWS.'accueil.php');
		   	}else{
	  	 		REQUIRE_ONCE(PATH_VIEWS.'login.php');
	  	 	}
	   }
	}
	else{
		REQUIRE_ONCE(PATH_VIEWS.$page.".php");
	}
?>