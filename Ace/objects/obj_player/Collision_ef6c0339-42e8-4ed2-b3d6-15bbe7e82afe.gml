/// @description Insert description here
// You can write your code in this editor
lifePlayer += 15
	audio_play_sound(snd_life,1,false);
with(other){
	instance_destroy();
}