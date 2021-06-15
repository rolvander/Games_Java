/// @description Insert description here
// You can write your code in this editor

#region Titulo
	draw_set_font(fnt_gameover);
	draw_set_color(c_white);
	draw_text(168, 135, "Game Over!")
	draw_set_color(c_red);
	draw_text(167, 136, "Game Over!")
#endregion

#region Restart
	draw_set_font(fnt_gameText11)
	draw_set_color(c_white)
	draw_text(260, 300, "Nao foi dessa vez!")
	draw_text(195, 340, "Tecle          para REINICIAR!")
	
	draw_set_font(fnt_gameText11)
	draw_set_color(c_red)
	draw_text(257, 339, "[ R ]")
	draw_set_color(c_white)
	draw_text(257, 340, "[ R ]")
	
#endregion