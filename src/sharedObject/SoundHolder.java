package sharedObject;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class SoundHolder {
	private static final SoundHolder instance;
	static {
		instance = new SoundHolder();
	}
	public static SoundHolder getInstance() {
		return SoundHolder.instance;
	}
	public Media gameScreen1;
	public Media gameScreen2;
	public Media gameScreen3;
	public Media gameScreen4;
	public Media gameScreen5;
	public Media gameScreen6;
	public Media gameScreen7;
	public Media gameScreen8;
	public Media gameScreen9;
	public Media gameScreen10;
	public AudioClip cannotSelect;
	public AudioClip selectCard;
	public AudioClip placeCard1;
	public AudioClip placeCard2;
	public AudioClip placeCard3;
	public AudioClip click;
	public AudioClip drawCard;
	public AudioClip move1;
	public AudioClip move2;
	public AudioClip move3;
	public AudioClip move4;
	public AudioClip move5;
	public AudioClip move6;
	public AudioClip move7;
	public AudioClip attack1;
	public AudioClip attack2;
	public AudioClip attack3;
	public AudioClip attack4;
	public AudioClip attackController;
	public AudioClip trick;

	public SoundHolder() {
		click = loadSound("click", "mp3");
		gameScreen1 = loadMedia("gameScreen1", "mp3");
		gameScreen2 = loadMedia("gameScreen2", "mp3");
		gameScreen3 = loadMedia("gameScreen3", "mp3");
		gameScreen4 = loadMedia("gameScreen4", "mp3");
		gameScreen5 = loadMedia("gameScreen5", "mp3");
		gameScreen6 = loadMedia("gameScreen6", "mp3");
		gameScreen7 = loadMedia("gameScreen7", "mp3");
		gameScreen8 = loadMedia("gameScreen8", "mp3");
		gameScreen8 = loadMedia("gameScreen9", "mp3");
		gameScreen8 = loadMedia("gameScreen10", "mp3");
		cannotSelect = loadSound("cannotSelect", "mp3");
		selectCard = loadSound("selectCard", "mp3");
		placeCard1 = loadSound("placeCard1", "mp3");
		placeCard2 = loadSound("placeCard2", "mp3");
		placeCard3 = loadSound("placeCard3", "mp3");
		click = loadSound("click", "mp3");
		drawCard = loadSound("drawCard", "mp3");
		move1 = loadSound("move1", "mp3");
		move2 = loadSound("move2", "mp3");
		move3 = loadSound("move3", "mp3");
		move4 = loadSound("move4", "mp3");
		move5 = loadSound("move5", "mp3");
		move6 = loadSound("move6", "mp3");
		move7 = loadSound("move7", "mp3");
		attack1 = loadSound("attack1", "mp3");
		attack2 = loadSound("attack2", "mp3");
		attack3 = loadSound("attack3", "mp3");
		attack4 = loadSound("attack4", "mp3");
		attackController = loadSound("attackController", "mp3");
		trick = loadSound("trick", "mp3");
	}

	public Media loadMedia(String prefixName, String fileType) {
		return new Media(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}

	public AudioClip loadSound(String prefixName, String fileType) {
		return new AudioClip(ClassLoader.getSystemResource("sound/" + prefixName + '.' + fileType).toString());
	}
}
