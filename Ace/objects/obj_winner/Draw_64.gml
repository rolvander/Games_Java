/// @description Insert description here
// You can write your code in this editor

#region Titulo
	draw_set_font(fnt_gameover);
	draw_set_color(c_white);
	draw_text(168, 135, "Vencedor!")
	draw_set_color(make_color_rgb(40,255,25));
	draw_text(167, 136, "Vencedor!")
#endregion

#region Restart
	draw_set_font(fnt_gameText11)
	draw_set_color(c_white)
	draw_text(205, 300, "Parabens, voce VENCEU!")
	draw_text(165, 340, "Tecle          para jogar novamente!")
	
	draw_set_font(fnt_gameText11)
	draw_set_color(c_red)
	draw_text(227, 339, "[ R ]")
	draw_set_color(c_white)
	draw_text(227, 340, "[ R ]")
	
#endregion