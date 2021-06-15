if(place_meeting(x, y, obj_enemy1)){
	audio_play_sound(snd_dano, 1,false);
	if(random(100) < 80){
		lifePlayer-=choose(0.5,0.6,0.7);
	}
}

if(place_meeting(x, y, obj_enemy2)){
	audio_play_sound(snd_dano, 1,false);
	if(random(100) < 70){
		lifePlayer-=choose(0.2,0.4,0.5);
	}
	
}
