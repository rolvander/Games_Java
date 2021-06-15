package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;

public class Enemy2 extends Entity{
	
	public boolean right = true,left = false;
	
	public double vida_enemy2 = 200;

	public Enemy2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		path = AStar.findPath(Game.world, new Vector2i(World.xINITIAL, World.yINITIAL), new Vector2i(World.xFINAL, World.yFINAL));
		
	}
	
	public void tick() {
		
		
		followPath(path);
		if(x >= Game.WIDTH) {//PLAYER PERDENDO VIDA
			
			Game.life-=Entity.rand.nextDouble();
			Game.entities.remove(this);
			Sound.damage.play();
			return;
		}
		
		if(vida_enemy2 <= 0) {//ENEMY2 MORRE
			Sound.raio.play();
			Game.entities.remove(this);
			Game.money+=1;
			Game.pontos+=10;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0, 255,0));
		g.fillRect((int)(x + 1), (int)(y -1), (int)(vida_enemy2 / 13), 1);
		
		if(vida_enemy2 < 30) {
			g.setColor(new Color(255, 0,0));
			g.fillRect((int)(x + 1), (int)(y -1), (int)(vida_enemy2 / 13), 1);
		}
	}

}
