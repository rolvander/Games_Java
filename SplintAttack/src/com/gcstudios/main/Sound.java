package com.gcstudios.main;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {

	public static class Clips {
		public Clip[] clips;
		private int p;
		private int count;

		public Clips(byte[] buffer, int count)
				throws LineUnavailableException, IOException, UnsupportedAudioFileException {
			if (buffer == null)
				return;

			clips = new Clip[count];
			this.count = count;

			for (int i = 0; i < count; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
		}

		public void play() {
			if (clips == null)
				return;
			clips[p].stop();
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;
			if (p >= count)
				p = 0;
		}

		public void loop() {
			if (clips == null)
				return;

			if (Game.gamestate == "NORMAL") {
				clips[p].loop(4);
			}
			if (Game.gamestate == "WIN") {
				clips[p].loop(0);
			}
			if (Game.gamestate == "GAMEOVER") {
				clips[p].loop(0);
			}
			
		}

	}

	public static final Clips hurt1 = load("/hurt.wav", 1);
	public static final Clips hurt2 = load("/hurt2.wav", 1);
	public static final Clips shield = load("/shield.wav", 1);
	public static final Clips firearrow = load("/tail.wav", 1);
	public static final Clips pedra = load("/fire.wav", 1);
	public static final Clips noammo = load("/noammo.wav", 1);
	public static final Clips noammopedra = load("/noammopedra.wav", 1);
	public static final Clips hurtA = load("/hurtA.wav", 1);
	public static final Clips packArrow = load("/shootarrow.wav", 1);
	public static final Clips move = load("/move.wav", 1);
	public static final Clips select = load("/select.wav", 1);
	public static final Clips select2 = load("/select1.wav", 1);
	public static final Clips pause = load("/pause.wav", 1);
	public static final Clips low = load("/vidabaixa.wav", 1);
	public static final Clips levelup = load("/powerup.wav", 1);
	public static final Clips dano = load("/damage1.wav", 1);
	public static final Clips eagle = load("/eagle2.wav", 1);
	public static final Clips eagle4 = load("/eagle4.wav", 1);
	public static final Clips life = load("/life.wav", 1);
	public static final Clips win = load("/win.wav", 1);
	public static final Clips gameover = load("/gameover.wav", 1);
	public static final Clips theme = load("/themes.wav", 1);
	public static final Clips themebaixo = load("/themeBAIXO.wav", 1);
	public static final Clips menu = load("/menu.wav", 1);
	public static final Clips left = load("/left.wav", 1);
	public static final Clips rigth = load("/rigth.wav", 1);

	private static Clips load(String name, int count) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream(name));

			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = dis.read(buffer)) >= 0) {
				baos.write(buffer, 0, read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data, count);
		} catch (Exception e) {
			try {
				return new Clips(null, 0);
			} catch (Exception ee) {
				return null;
			}
		}
	}

}
