package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
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
	public static Image nextTurn;
	
	public static Image testDeckNameLeft; //TODO Remove
	public static Image testDeckNameRight;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		String p = "picture/";
		RenderableHolder.backgroundSelectGameMode = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectGameMode.png").toString());
		RenderableHolder.backgroundSelectDeckPvB = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectDeckPvB.png").toString());
		RenderableHolder.backgroundSelectDeckPvP = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectDeckPvP.png").toString());
		RenderableHolder.backgroundSelectDeckBvB = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectDeckBvB.png").toString());
		RenderableHolder.backgroundGameScreen = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundGameScreen.png").toString());
		RenderableHolder.backgroundEndGame = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundEndGame.png").toString());
		
		RenderableHolder.testDeckNameLeft = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "testDeckNameLeft.png").toString());
		RenderableHolder.testDeckNameRight = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "testDeckNameRight.png").toString());
		
		RenderableHolder.cost = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "cost.png").toString());
		RenderableHolder.attackDamage = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "attackDamage.png").toString());
		RenderableHolder.attackRange = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "attackRange.png").toString());
		RenderableHolder.heart = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "heart.png").toString());
		RenderableHolder.speed = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "speed.png").toString());
		RenderableHolder.nextTurn = new Image(
				ClassLoader.getSystemResource(String.valueOf(p) + "nextTurn.png").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (!entities.get(i).isVisible())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

}
