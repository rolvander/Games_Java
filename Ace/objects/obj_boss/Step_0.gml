/// @description Insert description here
// You can write your code in this editor
#region STATE BOSS ATTACK
if(obj_player.gamestate == obj_player.bossbattle){//SE ESTIVER NO MODO BOSS BATTLE
	depth = -1;
	if(stateboss == parado){
		
	}else if(stateboss == atacar){// PRIMEIRO ATAQUE
		if(random(100) < 80){
			mp_potential_step(obj_player.x, obj_player.y,spdBoss,0)
		}
	
	}else if(stateboss == recuar){//RECUANDO PARA O LOCAL DE ORIGEM
		if(x < xstart){
			x+=spdBoss;
			if(x > xstart){
				x = xstart
			}
		}else if(x > xstart){
			x-=spdBoss
			if(x < xstart){
				x = xstart
			}
		}
		
		if(y < ystart){
			y+=spdBoss
			if(y > ystart){
				y = ystart
			}
			
		}else if(y > ystart){
			y-=spdBoss	
			if(y < ystart){
				y = ystart
			}
		}
	
	}else if(stateboss == cima){//INDO PARA CIMA
		//show_debug_message(string(cima))
		if(place_free(x,y-spdBoss)){
			y-=2
		}else {
			stateboss = atacar
		}
	}else if(stateboss == ataque2){//SEGUNDO ATAQUE
		if(place_free(x-4, y)){
			x-=4
		}else{
			stateboss = atacar;
		}
	}
	//ATAQUE AO PLAYER
	if(place_meeting(x, y, obj_player)){//
		//show_debug_message(string(obj_player.lifePlayer))
		audio_play_sound(snd_danoboss, 1, false);
		if(random(100) < 90){
				obj_player.lifePlayer-=choose(3, 5, 10);
			}
		stateboss = recuar
	}
	
}
#endregion

#region WINNER

if(lifeboss <= 0){//WINNER
	instance_destroy()	
	audio_play_sound(snd_winner,0,false);
	if(obj_player.gamestate == obj_player.bossbattle){
		obj_player.gamestate = obj_player.winner
	}
}
#endregion