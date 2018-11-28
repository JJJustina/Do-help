// 代码整理：懒人之家 www.lanrenzhijia.com
var VELOCITY = 1;
			var PARTICLES = 200;  // 颗粒物的密度
			
			var mouse = {x:0, y:0};
			var particles = [];
			var colors = [ "white","#80ffff" ];
			var canvas = document.getElementById('projector');
	
			var width=$("#projector").width();
			var  height=$("#projector").height();
			var context;
			
			if (canvas && canvas.getContext) {
				context = canvas.getContext('2d');
				
				for( var i = 0; i < PARTICLES; i++ ) {
					particles.push( { 
						x: Math.random()*width, 
						y: Math.random()*height, 
						vx: ((Math.random()*(VELOCITY*2))-VELOCITY),
						vy: ((Math.random()*(VELOCITY*2))-VELOCITY),
						size: 1+Math.random()*1,  // 颗粒物大小
						color: colors[ Math.floor( Math.random() * colors.length ) ]
					} );
				}
				
				Initialize();
			}
			
			function Initialize() {
				window.addEventListener('resize', ResizeCanvas, false);
     			setInterval( TimeUpdate, 50); //速度
				
				ResizeCanvas();
			}
			
			function TimeUpdate(e) {
				
				context.clearRect(0, 0, width, height);
				
				var len = particles.length;
				var particle;
				
				for( var i = 0; i < len; i++ ) {
					particle = particles[i];
					
					if (!particle.frozen) {
						particle.x += particle.vx;
						particle.y += particle.vy;
						
						if (particle.x > width) {
							particle.vx = -VELOCITY - Math.random();
						}
						else if (particle.x < 0) {
							particle.vx = VELOCITY + Math.random();
						}
						else {
							particle.vx *= 1 + (Math.random() * 0.005);
						}
						
						if (particle.y > height) {
							particle.vy = -VELOCITY - Math.random();
						}
						else if (particle.y < 0) {
							particle.vy = VELOCITY + Math.random();
						}
						else {
							particle.vy *= 1 + (Math.random() * 0.0005);
						}
						
						var distanceFactor = DistanceBetween( mouse, particle );
						distanceFactor = Math.max( Math.min( 15 - ( distanceFactor / 10 ), 10 ), 1 );
						
						particle.currentSize = particle.size*distanceFactor;
					}
					
					context.fillStyle = particle.color;
					context.beginPath();
					context.arc(particle.x,particle.y,particle.currentSize,0,Math.PI*1,true); //颗粒物形状
					context.closePath();
					context.fill();
					
				}
			}
			
			function MouseMove(e) {
				mouse.x = e.layerX;
				mouse.y = e.layerY;
			}
			
			function MouseDown(e) {
				var len = particles.length;
				
				var closestIndex = 0;
				var closestDistance = 1000;
				
				for( var i = 0; i < len; i++ ) {
					var thisDistance = DistanceBetween( particles[i], mouse );
					
					if( thisDistance < closestDistance ) {
						closestDistance = thisDistance;
						closestIndex = i;
					}
					
				}
				
				if (closestDistance < particles[closestIndex].currentSize) {
					particles[closestIndex].frozen = true;
				}
			}
			
			function ResizeCanvas(e) {
				canvas.width = width;
				canvas.height = height;
			}
			
			function DistanceBetween(p1,p2) {
				var dx = p2.x-p1.x;
				var dy = p2.y-p1.y;
				return Math.sqrt(dx*dx + dy*dy);
			}