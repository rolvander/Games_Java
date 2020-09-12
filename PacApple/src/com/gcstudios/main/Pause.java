package com.gcstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pause {
	
	public int framesPause = 0;
	public int maxFramesPause = 30;
	public boolean showMessagePause = false;
	
	public void tick() {
		this.framesPause++;
		if(this.framesPause == this.maxFramesPause) {
			this.framesPause = 0;
			if(this.showMessagePause) {
				this.showMessagePause = false;
			}else {
				this.showMessagePause = true;
			}
		}
	}
	public void render(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;		
		g2.setColor(new Color(0,0,0, 210));
		g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
		
		if(this.showMessagePause) {
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("arial", Font.BOLD, 27));
			g.drawString("pressione ESC para continuar", 160, 210);
		}
		
	}
}
