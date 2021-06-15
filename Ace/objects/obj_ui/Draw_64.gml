/// @description Insert description here
// You can write your code in this editor
#region LIFE

	//draw_sprite_ext(spr_coracao,3,43,15,2,2,1,-1,1)
	draw_healthbar(50,11,120,21,obj_player.lifePlayer,c_black,c_red,c_green,0,1,1)
	
#endregion

#region COIN
	draw_set_color(make_color_rgb(120,120,120))
	draw_rectangle(300,6,370,30,0)
	draw_set_color(make_color_rgb(0,0,0))
	draw_rectangle(300,6,370,30,1)
	draw_sprite(spr_coin,0,315,18)
	
	var coin = obj_player.coins
	
	draw_set_font(fnt_ui)
	draw_set_color(make_color_rgb(255,255,255))
	draw_text(327,9,string(coin))
	
	
#endregion

#region ENERGY
	
	//draw_sprite(spr_energy,1, 560,15)
	draw_healthbar(555,10,625,20,obj_player.energy,make_color_rgb(17,75,114),make_color_rgb(175, 175, 175),c_aqua,0,1,1)
	
#endregion

#region KEY

if(obj_player.key){
	draw_sprite(spr_key,0,400,10)
	
}	
#endregion

#region LIFEBOSS
if(obj_player.gamestate == obj_player.bossbattle){
	var vida = obj_boss.lifeboss
	draw_healthbar(220,410,460,430,vida,make_color_rgb(171,172,176),c_red,make_color_rgb(0,71,234),0,1,1)
	
}
#endregion










