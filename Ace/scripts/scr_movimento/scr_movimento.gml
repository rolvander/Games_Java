#region JOGANDO 
if(gamestate == jogando || gamestate == bossbattle || dying == false){// MODO JOGANDO ou BOSS
	depth = -1;
		if(key_right || keyboard_check(vk_right)){// INDO PARA DIREITA
			if(place_free(x + 4, y)){
				x+=spdPlayer
				right = true
				left = false
				up = false
				down = false
				sprite_index = spr_playerdireita
				if(key_right || keyboard_check(vk_right) && key_fast){//SE TIVER INDO RÁPIDO
					x+=spdPlayerFast
					sprite_index = spr_ghostDireita
					audio_play_sound(snd_ghost,0,false);
				}
			}
		}else if(key_left || keyboard_check(vk_left)){// INDO PARA ESQUERDA
			if(place_free(x - 4, y)){
				x-=spdPlayer
				right = false
				left = true
				up = false
				down = false
				sprite_index = spr_playeresq
				if(key_left || keyboard_check(vk_left) && key_fast){//SE TIVER INDO RÁPIDO
					x-=spdPlayerFast
					sprite_index = spr_ghostLeft
					audio_play_sound(snd_ghost,0,false);
				}
			}
		}else{//SE NÃO ESTIVER ANDANDO
				if(right){//FICA PARADO PARA DIREITA
					sprite_index = spr_player_right_parado
				}else if(left){// E ESQUERDA
					sprite_index = spr_player_left_parado
				}
		}
		if(key_up || keyboard_check(vk_up)){//INDO PARA CIMA
			if(place_free(x, y - 4)){
				y-=spdPlayer
				right = false
				left = false
				up = true
				down = false
				sprite_index = spr_playerUp
				if(key_up || keyboard_check(vk_up) && key_fast){//SE TIVER INDO RÁPIDO
					y-=spdPlayerFast
					sprite_index = spr_ghostUp
					audio_play_sound(snd_ghost,0,false);
				}
			}
		}else if(key_down || keyboard_check(vk_down)){//INDO PARA BAIXO
			if(place_free(x, y + 4)){
				y+=spdPlayer
				right = false
				left = false
				up = false
				down = true
				sprite_index = spr_playerDown
				if(key_down || keyboard_check(vk_down) && key_fast){//SE TIVER INDO RÁPIDO
					y+=spdPlayerFast
					sprite_index = spr_ghostDown
					audio_play_sound(snd_ghost,0,false);
				}
			}
		}else {//SE ESTIVER PARADO
			 if(up){
				sprite_index = spr_player_up_parado
			}else if(down){
				sprite_index = spr_player_down_parado
			}

		}
}//final do estado Jogando
#endregion

#region WINNER
	if(gamestate == winner){
		sprite_index = spr_player_Smile
	}
#endregion