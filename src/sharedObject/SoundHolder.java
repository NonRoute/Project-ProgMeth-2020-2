package sharedObject;

import javafx.scene.media.AudioClip;

public class SoundHolder {
	private static final SoundHolder instance;
	public AudioClip button;
	public AudioClip gameScreen1;
	
	static {
		instance = new SoundHolder();
	}

	public static SoundHolder getInstance() {
		return SoundHolder.instance;
	}

	public SoundHolder() {
		gameScreen1 = loadSound("gameScreen1", "mp3");

	}

	public AudioClip loadSound(String prefixName, String fileType) {
		return new AudioClip(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}
}
