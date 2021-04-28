package sharedObject;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class SoundHolder {
	private static final SoundHolder instance;
	public Media gameScreen1;
	public Media gameScreen2;
	public Media gameScreen3;
	public Media gameScreen4;
	public Media gameScreen5;
	public Media gameScreen6;
	public Media gameScreen7;
	public Media gameScreen8;
	public AudioClip cannotSelect;

	static {
		instance = new SoundHolder();
	}

	public static SoundHolder getInstance() {
		return SoundHolder.instance;
	}

	public SoundHolder() {
		gameScreen1 = loadMedia("gameScreen1", "mp3");
		gameScreen2 = loadMedia("gameScreen2", "mp3");
		gameScreen3 = loadMedia("gameScreen3", "mp3");
		gameScreen4 = loadMedia("gameScreen4", "mp3");
		gameScreen5 = loadMedia("gameScreen5", "mp3");
		gameScreen6 = loadMedia("gameScreen6", "mp3");
		gameScreen7 = loadMedia("gameScreen7", "mp3");
		gameScreen8 = loadMedia("gameScreen8", "mp3");
		cannotSelect = loadSound("cannotSelect", "mp3");

	}

	public AudioClip loadSound(String prefixName, String fileType) {
		return new AudioClip(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}

	public Media loadMedia(String prefixName, String fileType) {
		return new Media(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}
}
