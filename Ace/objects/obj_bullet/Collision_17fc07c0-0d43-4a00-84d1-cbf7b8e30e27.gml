/// @description Insert description here
// You can write your code in this editor
instance_destroy();
with(other){
	life_enemy-=choose(4,7,9)
	
	audio_play_sound(snd_hit, 1, false);
}
#region EXPLOSION

instance_create_depth(x,y,-y,obj_explosionEnemy)

#endregion