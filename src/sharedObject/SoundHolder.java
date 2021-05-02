package sharedObject;

import java.net.URL;

import exception.AudioNotFoundException;
import exception.ImageNotFoundException;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class SoundHolder {
	private static final SoundHolder instance = new SoundHolder();

	public static Media gameScreen1;
	public static Media gameScreen2;
	public static Media gameScreen3;
	public static Media gameScreen4;
	public static Media gameScreen5;
	public static Media gameScreen6;
	public static Media gameScreen7;
	public static Media gameScreen8;
	public static Media gameScreen9;
	public static Media gameScreen10;
	public static AudioClip cannotSelect;
	public static AudioClip selectCard;
	public static AudioClip placeCard1;
	public static AudioClip placeCard2;
	public static AudioClip placeCard3;
	public static AudioClip click;
	public static AudioClip drawCard;
	public static AudioClip move1;
	public static AudioClip move2;
	public static AudioClip move3;
	public static AudioClip move4;
	public static AudioClip move5;
	public static AudioClip move6;
	public static AudioClip move7;
	public static AudioClip attack1;
	public static AudioClip attack2;
	public static AudioClip attack3;
	public static AudioClip attack4;
	public static AudioClip attackController;
	public static AudioClip trick;

	static {
		loadResource();
	}

	public static SoundHolder getInstance() {
		return SoundHolder.instance;
	}

	public static void loadResource() {
		click = loadSound("click.mp3");
		gameScreen1 = loadMedia("gameScreen1.mp3");
		gameScreen2 = loadMedia("gameScreen2.mp3");
		gameScreen3 = loadMedia("gameScreen3.mp3");
		gameScreen4 = loadMedia("gameScreen4.mp3");
		gameScreen5 = loadMedia("gameScreen5.mp3");
		gameScreen6 = loadMedia("gameScreen6.mp3");
		gameScreen7 = loadMedia("gameScreen7.mp3");
		gameScreen8 = loadMedia("gameScreen8.mp3");
		gameScreen9 = loadMedia("gameScreen9.mp3");
		gameScreen10 = loadMedia("gameScreen10.mp3");
		cannotSelect = loadSound("cannotSelect.mp3");
		selectCard = loadSound("selectCard.mp3");
		placeCard1 = loadSound("placeCard1.mp3");
		placeCard2 = loadSound("placeCard2.mp3");
		placeCard3 = loadSound("placeCard3.mp3");
		drawCard = loadSound("drawCard.mp3");
		move1 = loadSound("move1.mp3");
		move2 = loadSound("move2.mp3");
		move3 = loadSound("move3.mp3");
		move4 = loadSound("move4.mp3");
		move5 = loadSound("move5.mp3");
		move6 = loadSound("move6.mp3");
		move7 = loadSound("move7.mp3");
		attack1 = loadSound("attack1.mp3");
		attack2 = loadSound("attack2.mp3");
		attack3 = loadSound("attack3.mp3");
		attack4 = loadSound("attack4.mp3");
		attackController = loadSound("attackController.mp3");
		trick = loadSound("trick.mp3");
	}

	public static Media loadMedia(String fileName) {
		try {
			URL imgUrl = ClassLoader.getSystemResource("sound/" + fileName);
			if (imgUrl == null) {
				throw new AudioNotFoundException(fileName);
			}
			return new Media(ClassLoader.getSystemResource("sound/" + fileName).toString());
		} catch (AudioNotFoundException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
		return null;
	}

	public static AudioClip loadSound(String fileName) {
		try {
			URL imgUrl = ClassLoader.getSystemResource("sound/" + fileName);
			if (imgUrl == null) {
				throw new AudioNotFoundException(fileName);
			}
			return new AudioClip(ClassLoader.getSystemResource("sound/" + fileName).toString());
		} catch (AudioNotFoundException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
		return null;
	}
}
