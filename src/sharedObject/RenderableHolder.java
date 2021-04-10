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
	public static ImageView backgroundSelectGameMode;
	public static ImageView backgroundSelectDeck;

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
		RenderableHolder.backgroundSelectGameMode = new ImageView(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectGameMode.png").toString());
		RenderableHolder.backgroundSelectDeck = new ImageView(
				ClassLoader.getSystemResource(String.valueOf(p) + "backgroundSelectDeck.png").toString());
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
