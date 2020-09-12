package com.gcstudios.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.World;

public class TowerController extends Entity{

	
	public boolean isPressed = false;
	public int xTarget, yTarget;
	public boolean permission = true;
	
	
	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		if(isPressed) {
			isPressed = false;
			permission = true;
			int xx = (xTarget / 16) * 16;
			int yy = (yTarget / 16) * 16;
			
			Player player = new Player(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(32, 16, 16, 16));
			for(int i = 0; i < Game.entities.size();i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player){
					if(Entity.isColidding(e, player)) {
						permission = false;
						Sound.noammo.play();
						//System.out.println("Não permitido");
					}
				}
			}
			if(World.isFree(xx, yy)) {
				permission = false;
				Sound.noammo.play();
				
			}
			if(permission) {
				if(Game.money >= Game.price) {
					Sound.insert.play();
					Game.entities.add(player);
					Game.money-=Game.price;
					Game.towerCount++;
					
				}else {
					permission = false;
					Sound.noammo.play();	
				}
			}
		}
	}
	public void render(Graphics g) {
		
	}
}
