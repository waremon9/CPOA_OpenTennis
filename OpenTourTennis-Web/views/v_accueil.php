
<html>	
	<head>
		<title> Billetterie </title>
		<link href="<?= PATH_CSS ?>bootstrap.css" rel="stylesheet"> 
		<link href="<?= PATH_CSS ?>css.css" rel="stylesheet">
	</head>
	<body>
		<?php require_once(PATH_VIEWS.'menu.php');?>

		<!--  Zone message d'alerte -->
		<?php require_once(PATH_VIEWS.'alert.php');?>

		<!--  Début de la page -->
		<?php if(isset($_SESSION['email'])){ ?>
			<h1 class="billetterie"><?= TITRE_PAGE_ACCUEIL_TOUS ?></h1>
			<?php require_once(PATH_VIEWS.'billetterie.php');?>
		<?php }else{ ?>
			<h1 align="center"> <a href="./index.php?page=login"> Veuillez vous connecter </a></h1>
		<?php } ?>

		

	</body>


</html>