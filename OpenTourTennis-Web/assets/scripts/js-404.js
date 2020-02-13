
		function fleeMouse(event) {
			var x = event.clientX;
			var y = event.clientY;
			var x1 = x;
			var y1 = y;
			while(x1 == x)
			{
				x1 = getRandomInt(window.innerWidth-100);
			}
			while(y1 == y)
			{
				y1 = getRandomInt(window.innerHeight-100);
			}
			myMove();
			function myMove() {
				var elem = document.getElementById("crous");
				var id = setInterval(frame, 5);
				function frame() {
					elem.style.top = y1+"px";
					elem.style.left = x1+"px";
					clearInterval(id);
				}
			}
		}
		function getRandomInt(max) {
			return Math.floor(Math.random() * Math.floor(max));
		}
		function playAudio() {
			var x = document.getElementById("myAudio"); 
			x.play();
		}