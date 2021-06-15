/// @description Insert description here
// You can write your code in this editor

	var autop = choose(obj_energyBossAuto, obj_lifebossauto)
	
	if(place_free(x, y)){
		instance_create_depth(obj_boss.x, obj_boss.y + 10, -1,autop)
		alarm[1] = choose(60*10, 60*15, 60*20)
	}
