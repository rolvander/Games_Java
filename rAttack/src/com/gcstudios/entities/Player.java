package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Player extends Entity{
	public boolean right, left;
	public boolean fire;
	public int life = 5;
	public int pontos = 0;
		
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick(){
		depth = 1;
		if(right) {
			Sound.right.play();
			x+=speed;
			
		}
		if(left) {
			x-=speed;
			Sound.left.play();
		}
		if(x > Game.WIDTH -10) {
			x = Game.WIDTH -10;
		}
		if(x <= -5) {
			x = -5;
		}
		
		if(fire) {
			fire = false;
			BulletShoot bullet = new BulletShoot(this.getX() + 5, this.getY(), 3, 3, 2, null);
			Game.bullets.add(bullet);
		}
		
		if(life <= 0) {
			Game.gameover = 1;
			
			life = 0;
		}
		
		
	}
	public void render(Graphics g) {
		super.render(g);
	}
}
