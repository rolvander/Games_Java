/// @description Insert description here
// You can write your code in this editor
#region Titulo
draw_set_font(fnt_instruTitulo);
draw_set_color(c_white);
draw_text(200, 35, "Instruções")
#endregion

#region Sub titulos
	/*Controle*/
	draw_set_font(fnt_instruText1);
	draw_set_color(c_white);
	draw_text(100, 100, "Controles")
	/*InGame*/
	draw_set_font(fnt_instruText1);
	draw_set_color(c_white);
	draw_text(470, 100, "InGame")
	/*About*/
	draw_set_font(fnt_instruText1);
	draw_set_color(c_white);
	draw_text(470, 295, "About")
#endregion

#region Caixas
	/*caixa Left*/
	draw_set_color(make_color_rgb(5,230,254));
	draw_rectangle(30,150,290, 400, 1);
	draw_set_color(make_color_rgb(43,71,71));
	draw_rectangle(31,151,289, 399, 0);
	/*caixa right*/
	draw_set_color(make_color_rgb(5,230,254));
	draw_rectangle(380,150,640, 287, 1);
	draw_set_color(make_color_rgb(43,71,71));
	draw_rectangle(381,151,639, 286, 0);
	/*caixa ABOUT*/
	draw_set_color(make_color_rgb(5,230,254));
	draw_rectangle(380,330,662, 433, 1);
	draw_set_color(make_color_rgb(43,71,71));
	draw_rectangle(381,331,661, 432, 0);
#endregion

#region CONTROLES
	draw_set_font(fnt_instruText2);
	draw_set_color(c_white);
	draw_text(50, 160, "Cima: ")
	draw_text(50, 190, "Baixo: ")
	draw_text(50, 220, "Direita: ")
	draw_text(50, 250, "Esquerda: ")
	draw_text(50, 280, "Tiro: ")
	draw_text(50, 310, "Modo rápido: ")
	draw_text(50, 340, "Pause: ")
	
	draw_set_color(c_yellow)
	draw_text(150, 157, "[w] [seta cima]")
	draw_text(150, 187, "[s] [seta baixo]")
	draw_text(150, 217, "[d] [>]")
	draw_text(150, 247, "[a] [<]")
	draw_text(150, 277, "[x]")
	draw_text(185, 307, "[espace]")
	draw_text(185, 337, "[esc]")

	

#endregion

#region Ingame
	draw_set_font(fnt_instruText2);
	draw_set_color(c_white);
	draw_text(400, 160, "Poder: ")
	draw_sprite(spr_power,1,470 ,172)
	draw_text(400, 190, "Saúde: ")
	draw_sprite(spr_coracao,2,470 ,200)
	draw_text(400, 220, "Moedas: ")
	draw_sprite(spr_coin,0,480 ,232)
	draw_text(400, 250, "Inimigos: ")
	draw_sprite(spr_enemy1,0,500 ,260)
	draw_sprite(spr_enemy2,0,530 ,260)
	draw_sprite(spr_enemy4,0,560 ,270)
#endregion

#region About
	draw_set_color(c_white);
	draw_set_font(fnt_instruText2)
	draw_text(390,338,"Você está num campo de gelo. \nCongele os inimigos para chegar \nao final. \nMas, cuidado com o CHEFE!")


	
#endregion


