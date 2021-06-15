/// @description Insert description here
// You can write your code in this editor

	if(keyboard_check(ord("C"))){
		if(coins > 110){//SE AS MOEDAS FOREM MAIORES QUE 110
			audio_play_sound(snd_key,1,false);
			key = true;
			semsaldo = false
			with(other){
				instance_destroy()
			}
		}else {
			//SALDO INSULFICIENTE
			semsaldo = true
			audio_play_sound(snd_nokeys, 1,false)
		}
	}
