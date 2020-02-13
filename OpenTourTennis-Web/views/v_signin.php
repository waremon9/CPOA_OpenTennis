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
            <form action="index.php?page=createAccount" method="POST">
                <h1>Inscription</h1>
                
                <!-- NOM -->
                <label><b>Nom</b></label> </br>
                <input type="text" placeholder="Entrer votre nom" name="lastname" required>
                </br>
                <!-- PRENOM -->
                <label><b>Prénom</b></label> </br>
                <input type="text" placeholder="Entrer votre prénom" name="firstname" required>
                </br>
                <!-- EMAIL-->
                <label><b>Adresse e-mail</b></label> </br>
                <?php
                if(isset($_POST['mailExist'])){
                    $err = $_POST['mailExist'];
                    if($err==1){
                        echo "<p style='color:red'>E-mail déjà utilisée</p>";
                    }
                }
                ?>
                <input type="email" placeholder="Entrer votre e-mail" name="email" required>
                </br>
                <!-- MDP -->
                <label><b>Mot de passe</b></label> </br>
                <input type="password" placeholder="Entrer votre mot de passe" name="password" required>

                <input type="submit" id='submit' value='SIGN IN' >
                <a href="./index.php?page=login" align="center"> J'ai déjà un compte </a>
                
            </form>
        </div>
    </body>
</html>