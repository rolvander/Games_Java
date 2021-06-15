if(gamestate == jogando || gamestate == bossbattle){
	if(energy > 0){//SE TIVER ENERGIA
		semenergia = false;
		if(keyboard_check(ord("X"))){// SE ATIRAR
			audio_play_sound(snd_tiro,1,false);
			energy -= choose(0.5,0.4,0.3)
			if(right){//ATIRANDO PARA DIREITA
				sprite_index = spr_player_attack_right
				var tiro = instance_create_depth(x + 9,y + 5,1,obj_bullet)
				tiro.hspeed = 3;
			}else if(left){//ATIRANDO PARA ESQUERDA
				sprite_index = spr_player_atack_left
				var tiro = instance_create_depth(x - 11,y - 1,1,obj_bullet)
				tiro.hspeed = -3;
				tiro.image_xscale = -1;
			}else if(down){//ATIRANDO PARA BAIXO
				sprite_index = spr_player_atack_down
				var tiro = instance_create_depth(x - 9, y + 10,1,obj_bullet)
				tiro.sprite_index = spr_bulletdown
				tiro.vspeed = 5
			}else if(up){//ATIRANDO PARA CIMA
				sprite_index = spr_player_atack_up
				var tiro = instance_create_depth(x + 9, y - 8,1,obj_bullet)
				tiro.sprite_index = spr_bulletdown
				tiro.vspeed = -5
				tiro.image_yscale = -1;
			}
			
		}
	}else if(energy <= 0){
			semenergia = true;
			
	}
}//estiver jgoando