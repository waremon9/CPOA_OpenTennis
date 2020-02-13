<html>
    <head>
        <meta charset="utf-8">
        <link href="<?= PATH_CSS ?>bootstrap.css" rel="stylesheet"> 
        <link href="<?= PATH_CSS ?>css.css" rel="stylesheet">
    </head>
    <body id="coloredbg">
        <!-- Retour acceuil -->
        <a href="index.php">
            <img
                id="imgAcceuil"
                src="assets/images/home.png" 
                alt="Retour acceuil"
                height="40px" 
                width="40px" 
            />
        </a>

        <div id="container">
            <!-- zone de connexion -->
            <form action="./index.php" method="POST">
                <h1>Connexion</h1>
                
                <label><b>E-mail</b></label> </br>
                <input type="email" placeholder="Entrer l'adresse e-mail" name="email" required>
                </br>
                <label><b>Mot de passe</b></label> </br>
                <input type="password" placeholder="Entrer le mot de passe" name="password" required>

                <input type="submit" id='submit' value='LOGIN' >
                <a href="./index.php?page=signin" align="center"> Je n'ai pas de compte </a>
                <?php
                if(isset($_GET['erreur'])){
                    $err = $_GET['erreur'];
                    if($err==1 || $err==2)
                        echo "<p style='color:red'>Utilisateur ou mot de passe incorrect</p>";
                }
                ?>
            </form>
        </div>
    </body>
</html>