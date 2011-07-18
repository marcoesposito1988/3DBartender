package de.tum.in.far.threedui.bartender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.audio.AudioPlayer;

public class SoundPlayer {
	private final static String BASE_PATH = "sounds"+File.separator;
	
	private final static String SUCCESS_SOUND = "success.wav";
	private final static String FAIL_SOUND = "fail.wav";
	private final static String POUR_SOUND = "pour2.wav";
	
	private static InputStream in = null;
	
	public enum SoundType { FAIL, SUCCESS, POUR };
	
	public static void playSound(SoundType type) throws FileNotFoundException {
		switch (type) {
		case POUR:
			in = new FileInputStream(BASE_PATH+POUR_SOUND);
			break;
		case SUCCESS:
			in = new FileInputStream(BASE_PATH+SUCCESS_SOUND);
			break;
		case FAIL:
			in = new FileInputStream(BASE_PATH+FAIL_SOUND);
			break;
		}
		AudioPlayer.player.start(in);
	}
	
	public static void stopSound(SoundType type) throws FileNotFoundException {
		switch (type) {
		case POUR:
			in = new FileInputStream(BASE_PATH+POUR_SOUND);
			break;
		case SUCCESS:
			in = new FileInputStream(BASE_PATH+SUCCESS_SOUND);
			break;
		case FAIL:
			in = new FileInputStream(BASE_PATH+FAIL_SOUND);
			break;
		}
		AudioPlayer.player.stop(in);
	}

}
