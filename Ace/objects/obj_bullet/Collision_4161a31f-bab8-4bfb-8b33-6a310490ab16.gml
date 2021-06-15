/// @description Insert description here
// You can write your code in this editor
if(random(100) < 70){
	obj_boss.lifeboss-=choose(0.2,0.4,0.5)
	audio_play_sound(snd_hitboss, 1, false)
}
instance_destroy()