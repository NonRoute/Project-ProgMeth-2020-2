package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	public static Image backgroundSelectGameMode;
	public static Image backgroundSelectDeckPvB;
	public static Image backgroundSelectDeckPvP;
	public static Image backgroundSelectDeckBvB;
	public static Image backgroundGameScreen;
	public static Image backgroundEndGame;
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
		RenderableHolder.backgroundSelectGameMode = loadImage("backgroundSelectGameMode.jpg");
		RenderableHolder.backgroundSelectDeckPvB = loadImage("backgroundSelectDeckPvB.png");
		RenderableHolder.backgroundSelectDeckPvP = loadImage("backgroundSelectDeckPvP.png");
		RenderableHolder.backgroundSelectDeckBvB = loadImage("backgroundSelectDeckBvB.png");
		RenderableHolder.backgroundGameScreen = loadImage("backgroundGameScreen.jpg");
		RenderableHolder.backgroundEndGame = loadImage("backgroundEndGame.png");

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
