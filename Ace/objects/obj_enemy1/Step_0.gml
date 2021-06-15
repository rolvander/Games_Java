/// @description Insert description here
// You can write your code in this editor
#region //MOVIMENTO DO INIMIGO
if(obj_player.gamestate == obj_player.jogando){
	
	if(distance_to_object(obj_player) < 110){
		state = perseguindo
	
	}else {
		state = livre
	}


	if(state == livre){//FORA DE ALCANCE
		if(dir == right){
			if(place_free(x+spd_enemy,y)){
				x+=spd_enemy;
			}
		}else if(dir == left){
			if(place_free(x-spd_enemy,y)){
				x-=spd_enemy
			}
		}else if(dir == up){
			if(place_free(x,y-spd_enemy)){
				y-=spd_enemy;
			}
		}else if(dir == down){
			if(place_free(x,y+spd_enemy)){
				y+=spd_enemy
			}
		}
	
		if(random(100) < 10){
			dir = choose(right, left, down, up)
		}
	}else if(state == perseguindo){//DENTRO DE ALCANCE
		mp_potential_step(obj_player.x,obj_player.y, 1, false)
		depth = -y;
	
	}
}

#endregion

#region LIFE
if(life_enemy <= 0){
	instance_destroy()
	instance_create_depth(x, y,1,obj_enemyDamage)
}

if(life_enemy == 0){
	life_enemy = 0
}
if(life_enemy > 100){
	life_enemy = 100;
}
#endregion


	
