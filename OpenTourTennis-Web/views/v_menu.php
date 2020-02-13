<!-- Menu du site -->

<nav class="menu_nav">
    <ul>
		<li>
			<a href="./index.php">
				Billetterie
			</a>
		</li>
		<?php if(isset($_SESSION['pageAchat'])){
			if($_SESSION['pageAchat'] == 1){ ?>
		<li>
			<a href="./index.php?page=achatTicket">
				Achat du Billet
			</a>
		</li>
		<?php }} ?>

		<?php if(isset($_SESSION['type']) && $_SESSION['type'] == "admin"){ ?>
		<li>
			<a href="./index.php?page=parametrage">
				Paramétrage
			</a>
		</li>
		<?php } ?>
		<li style="float:right">

			<?php 
			if(!isset($_SESSION['email'])){ ?>
				<a href="./index.php?page=login" >
					Login
				</a>
			<?php 
			}else{ 
			?>
				<a href="./index.php?page=deconnexion" id="deconnexion">
					Déconnexion
				</a>
				<div id="userRef">
					<?php 
					if(isset($_SESSION['email'])){
						echo $_SESSION['email'];
					}?>
				</div>
			
			<?php } ?> 
		</li>

    </ul>
</nav>


