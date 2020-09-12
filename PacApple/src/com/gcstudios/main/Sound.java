package com.gcstudios.main;

import java.io.*;
import javax.sound.sampled.*;

import com.gcstudios.entities.Player;


public class Sound {
	
	public static class Clips{

		public Clip[] clips;
		private int p, count;

	public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			if(buffer == null) return;
			
			clips = new Clip[count];
			this.count = count;
		
		for(int i = 0; i < count; i++) {
			clips[i] = (Clip) AudioSystem.getClip();
			((javax.sound.sampled.Clip) clips[i]).open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
		}
		public void play() {
			if(clips == null) return;
			
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;
			if(p >= count) p = 0;
		}
		
		public void loop() {
			if(clips == null) return;
				
				if(Game.estado_cena == Game.entrada) {
					clips[p].loop(0);
					//System.out.println("Rodando Loop de 0");
				}
				if(Game.estado_cena == Game.jogando){
					//System.out.println("Rodando Loop de 300!!");
					clips[p].loop(300);
				}
				if(Game.gameover == true) {
					clips[p].loop(0);
					//System.out.println("Rodando Loop de Game Over");
				}
				if(Player.winTime < Player.winMaxTime) {
					clips[p].loop(0);
				}
				//if(Game.game_State == "NORMAL") {
					//clips[p].loop(20);
				//}
		}	
	}
	public static Clips music = load("/pacman_beginning.wav",1);
	public static Clips eat_apple = load("/credit.wav",1);
	public static Clips game_over = load("/death_1.wav",1);
	public static Clips eat_orange = load("/pacman_eatfruit.wav",1);
	public static Clips sirene = load("/siren_1.wav",1);
	public static Clips sirene2 = load("/siren_3.wav",1);
	public static Clips winner = load("/win.wav",1);
	
	private static Clips load(String name, int count) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream(name));
			
			byte[] buffer = new byte [1024];
			int read = 0;
			while((read = dis.read(buffer)) >= 0) {
				baos.write(buffer, 0, read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data, count);
		}catch(Exception e) {
			try {
				return new Clips(null, 0);
			}catch (Exception ee) {
				return null;
			}
		}
		
	
	}
	
}
