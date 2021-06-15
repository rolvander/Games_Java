
#region INICIO
if(gamestate == entrada){//ENTRADA
	dying = false
	x+=1;
	if(x == 70){
		audio_play_sound(snd_entrada,1,false);
		gamestate = comecando
	}
}
if(gamestate == comecando){//MENSAGEM DE INICIO
		if(keyboard_check(ord("P"))){
			gamestate = jogando
			show_start = false
		}
}
if(gamestate == jogando){//COLOCANDO TILES PARA FECHAR
	audio_pause_sound(snd_menu)
	dying = false
	instance_create_depth(40, 32,-y, obj_gateEntrada);
	
}
#endregion

#region PAUSE

if(key_pause){//CLICAR PARA PAUSAR
		room_goto(rm_menu)
		
}

#endregion
	
#region JOGANDO
if(lifePlayer <= 0 && gamestate == jogando){//MODO GAME OVER JOGANDO
	gamestate = gameover
	dying = true
	instance_create_depth(obj_player.x, obj_player.y, -7, obj_playerDying)
	audio_play_sound(snd_lose,1,false);

}else if(lifePlayer <= 0 && gamestate == bossbattle){//MODO GAME OVER BOSSBATLE
	gamestate = bossGameover
	dying = true
	instance_create_depth(obj_player.x, obj_player.y, -7, obj_playerDying)
	audio_play_sound(snd_lose,1,false);
}
	
#endregion









