package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public double vida_enemy1 = 100;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		path = AStar.findPath(Game.world, new Vector2i(World.xINITIAL, World.yINITIAL), new Vector2i(World.xFINAL, World.yFINAL));
		
	}
	
	public void tick() {
		
		
		followPath(path);
		if(x >= Game.WIDTH) {//PLAYER PERDENDO VIDA
			Game.life-=Entity.rand.nextDouble();
			System.out.println("Removido");
			Game.entities.remove(this);
			
			return;
		}
		
		if(vida_enemy1 <= 0) {//ENEMY DESTRUIDO
			Sound.raio2.play();
			Game.entities.remove(this);
			Game.money+=3;
			Game.pontos+=5;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0, 255,0));
		
		g.fillRect((int)(x + 2), (int)(y -1), (int)(vida_enemy1 / 9), 1);
		
		if(vida_enemy1 < 30) {
			g.setColor(new Color(255, 0,0));
			g.fillRect((int)(x + 2), (int)(y -1), (int)(vida_enemy1 / 9), 1);
		}
	}

}
