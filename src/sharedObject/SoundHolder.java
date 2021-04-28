package sharedObject;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class SoundHolder {
	private static final SoundHolder instance;
	public Media gameScreen1;
	public Media gameScreen2;
	public Media gameScreen3;
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
		cannotSelect = loadSound("cannotSelect", "mp3");

	}

	public AudioClip loadSound(String prefixName, String fileType) {
		return new AudioClip(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}

	public Media loadMedia(String prefixName, String fileType) {
		return new Media(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}
}
