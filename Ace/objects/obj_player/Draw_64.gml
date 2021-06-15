/// @description Insert description here
// You can write your code in this editor
#region SEM SALDO
	if(obj_player.semsaldo){
		if(keyboard_check(ord("C"))){
			if(distance_to_object(obj_key) < 10){
				draw_set_font(fnt_saldo)
				draw_set_color(make_color_rgb(180,0,0))
				draw_text(380, 10, "SALDO INSUFICIENTE")
			}
		}
	
	}
#endregion

#region SEM ENERGIA
	if(semenergia){
		if(keyboard_check(ord("X"))){
			audio_play_sound(snd_noenergy, 1,false)
				draw_set_font(fnt_saldo)
				draw_set_color(make_color_rgb(180,0,0))
				draw_text(410, 10, "SEM ENERGIA")
		}
	}
	
#endregion

#region CUTSCENE
if(gamestate == comecando){
		draw_set_font(fnt_cutsceneInicio)
		draw_set_color(make_color_rgb(133,215,228))
		draw_rectangle(160, 70, 510, 270,0)
		draw_set_color(make_color_rgb(0,0,120))
		draw_rectangle(159, 69, 511, 271,1)
		draw_rectangle(158, 68, 512, 272,1)
		draw_rectangle(156, 66, 514, 274,1)
		draw_text(206, 80, "VOCE ENTROU NUMA FRIA!")
		draw_text(166, 105, "DERROTE OS INIMIGOS E O CHEFAO")
		draw_text(250, 130, "PARA SAIR DAQUI")
		draw_text(215, 215, "TECLE [ P ] PARA INICIAR")
	
}
#endregion	

#region DIALOGO
if(gamestate == jogando){
	if(caixa){
		
		draw_set_font(fnt_messageBox)
		draw_set_color(make_color_rgb(133,215,228))
		draw_rectangle(265, 37, 400, 57,0)
		draw_set_color(make_color_rgb(0,0,155))
		draw_rectangle(264, 36, 401, 58,1)
		draw_rectangle(263, 35, 402, 59,1)
		draw_text(272, 40, string(textoCaixa))
		//show_debug_message("CAIXA")
	
	}else if(gate){
		
		draw_set_font(fnt_messageBox)
		draw_set_color(make_color_rgb(133,215,228))
		draw_rectangle(220, 37, 450, 57,0)
		draw_set_color(make_color_rgb(0,0,155))
		draw_rectangle(219, 36, 451, 58,1)
		draw_rectangle(218, 35, 452, 59,1)
		draw_text(227, 40, string(textogate))
	
	}else if(keyy){
		
		draw_set_font(fnt_messageBox)
		draw_set_color(make_color_rgb(133,215,228))
		draw_rectangle(220, 37, 456, 74,0)
		draw_set_color(make_color_rgb(0,0,155))
		draw_rectangle(219, 36, 457, 75,1)
		draw_rectangle(218, 35, 458, 76,1)
		draw_text(227, 40, string(textoKey))
		draw_set_font(fnt_messageBox2)
		draw_text(257, 56, string(texto_key))
	}
}
#endregion 

#region PORTAO TRANCADO
if(key == false){
	
	if(keyboard_check(ord("K"))){
		audio_play_sound(snd_signal, 1,false)
			if(distance_to_object(obj_gate) < 10){
				draw_set_font(fnt_saldo)
				draw_set_color(make_color_rgb(180,0,0))
				draw_text(380, 10, "PORTAO TRANCADO")
			}
		}
}
#endregion

