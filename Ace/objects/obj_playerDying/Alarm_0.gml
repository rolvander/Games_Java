/// @description Insert description here
// You can write your code in this editor

if(obj_player.gamestate == obj_player.gameover){
	room_goto(rm_gameover)
	//show_debug_message("\n MORREU NORMAL \n")
}else if(obj_player.gamestate == obj_player.bossGameover){
	//show_debug_message("\n VOLTOU AO INICIO DO BOSSBATTLE \n")
	game_load("BossInitial.dat")
}