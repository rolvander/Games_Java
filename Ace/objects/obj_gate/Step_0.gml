/// @description Insert description here
// You can write your code in this editor
if(distance_to_object(obj_player) < 8){
	if(keyboard_check_pressed(ord("K"))){
		//show_debug_message("CLICOU PARA ABRIR O PORTÃO")
		if(obj_player.key){
			instance_destroy()
			instance_create_depth(x,y,-1,obj_gateOpen)
			//show_debug_message("\n ABERTO \n")
			audio_play_sound(snd_door,0,false);
		}else {
			
		}
	}
}