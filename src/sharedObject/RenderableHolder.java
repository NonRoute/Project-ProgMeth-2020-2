package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	public static Image icon;
	public static Image backgroundSelectGameMode;
	public static Image backgroundSelectDeckPvB;
	public static Image backgroundSelectDeckPvP;
	public static Image backgroundSelectDeckBvB;
	public static Image backgroundGameScreen1;
	public static Image backgroundGameScreen2;
	public static Image backgroundGameScreen3;
	public static Image backgroundGameScreen4;
	public static Image backgroundGameScreen5;
	public static Image cost;
	public static Image attackDamage;
	public static Image attackRange;
	public static Image heart;
	public static Image speed;
	public static Image nextPhase;
	public static Image testDeckNameLeft; // TODO Remove
	public static Image testDeckNameRight;
	public static Image phaseDrawCard;
	public static Image phaseBot;
	public static Image phaseMove;
	public static Image phaseAttack;
	public static Image cardAttack;
	public static Image cardDefense;
	public static Image cardDead;
	public static Image trick;

	static {
		loadResource();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static Image loadImage(String fileName) {
		return new Image(ClassLoader.getSystemResource("picture/" + fileName).toString());
	}

	public static void loadResource() {
		RenderableHolder.icon = loadImage("icon.png");
		RenderableHolder.backgroundSelectGameMode = loadImage("backgroundSelectGameMode.jpg");
		RenderableHolder.backgroundSelectDeckPvB = loadImage("backgroundSelectDeckPvB.png");
		RenderableHolder.backgroundSelectDeckPvP = loadImage("backgroundSelectDeckPvP.png");
		RenderableHolder.backgroundSelectDeckBvB = loadImage("backgroundSelectDeckBvB.png");
		RenderableHolder.backgroundGameScreen1 = loadImage("backgroundGameScreen1.jpg");
		RenderableHolder.backgroundGameScreen2 = loadImage("backgroundGameScreen2.png");
		RenderableHolder.backgroundGameScreen3 = loadImage("backgroundGameScreen3.jpg");
		RenderableHolder.backgroundGameScreen4 = loadImage("backgroundGameScreen4.jpg");
		RenderableHolder.backgroundGameScreen5 = loadImage("backgroundGameScreen5.jpg");

		RenderableHolder.testDeckNameLeft = loadImage("testDeckNameLeft.png");
		RenderableHolder.testDeckNameRight = loadImage("testDeckNameRight.png");

		RenderableHolder.cost = loadImage("cost.png");
		RenderableHolder.attackDamage = loadImage("attackDamage.png");
		RenderableHolder.attackRange = loadImage("attackRange.png");
		RenderableHolder.heart = loadImage("heart.png");
		RenderableHolder.speed = loadImage("speed.png");
		RenderableHolder.nextPhase = loadImage("nextPhase.png");
		RenderableHolder.phaseDrawCard = loadImage("phaseDrawCard.png");
		RenderableHolder.phaseBot = loadImage("phaseBot.png");
		RenderableHolder.phaseMove = loadImage("phaseMove.png");
		RenderableHolder.phaseAttack = loadImage("phaseAttack.png");
		RenderableHolder.cardAttack = loadImage("cardAttack.png");
		RenderableHolder.cardDefense = loadImage("cardDefense.png");
		RenderableHolder.cardDead = loadImage("cardDead.png");
		RenderableHolder.trick = loadImage("trick.png");
	}

	private List<IRenderable> entities;

	private Comparator<IRenderable> comparator;

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (!entities.get(i).isVisible())
				entities.remove(i);
		}
	}

}
