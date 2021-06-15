/// @description Insert description here
// You can write your code in this editor
game_save("BossInitial.dat")

	audio_play_sound(snd_life, 1, false);
lifePlayer = 100;
gamestate = bossbattle
//show_debug_message("\nSALVOU!\n")
instance_create_depth(929, 640,-y,obj_boss)
instance_create_depth(obj_gateOpen2.x, obj_gateOpen2.y,-y,obj_gateClose)
with(other){
	instance_destroy();
}