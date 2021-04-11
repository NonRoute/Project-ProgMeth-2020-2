package screen;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen {
	private Pane root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameLogic logic;

	public GameScreen() {
		root = new Pane();
		canvas = new Canvas(1280, 720);
		gc = canvas.getGraphicsContext2D();
		logic = new GameLogic();
		root.getChildren().addAll(canvas);
		Scene scene = new Scene(root);
		GameController.primaryStage.setScene(scene);
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				paintComponent();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
			}
		};
		animation.start();
	}

	public void paintComponent() {
		gc.drawImage(RenderableHolder.backgroundGameScreen, 0, 0, 1280, 720);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible()) {
				entity.draw(gc);
			}
		}
	}

}
