/// @description Insert description here
// You can write your code in this editor

with(other){
	life_enemy4-=50;
	audio_play_sound(snd_hit, 1, false);
}

#region EXPLOSION

instance_create_depth(x,y,-y,obj_explosionEnemy)


#endregion
instance_destroy();