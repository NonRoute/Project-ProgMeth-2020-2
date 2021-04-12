package screen;

import card.Card;
import gui.BoardPane;
import gui.CardsInHandPane;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen {
	private Pane root;
	private BorderPane borderPane;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameLogic logic;
	private VBox leftCardsInHand;
	private VBox rightCardsInHand;

	public GameScreen() {
		root = new Pane();
		canvas = new Canvas(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);

		borderPane = new BorderPane();
		borderPane.setPrefSize(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);
		leftCardsInHand = new CardsInHandPane();
		rightCardsInHand = new CardsInHandPane();
		BorderPane.setMargin(leftCardsInHand, new Insets(20));
		BorderPane.setMargin(rightCardsInHand, new Insets(20));
		borderPane.setLeft(leftCardsInHand);
		borderPane.setRight(rightCardsInHand);

		gc = canvas.getGraphicsContext2D();
		logic = new GameLogic();

		root.getChildren().addAll(canvas, new BoardPane(), borderPane);

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

	public void addCardsInHands(String deckName, Card card) {
		switch (card.getPlayingSide()) {
		case LEFT:
			((CardsInHandPane) leftCardsInHand).addCard(deckName, card);
			break;
		case RIGHT:
			((CardsInHandPane) rightCardsInHand).addCard(deckName, card);
			break;
		}
	}

	public void removeCardsInHands(int index, Direction playingSide) {
		switch (playingSide) {
		case LEFT:
			((CardsInHandPane) leftCardsInHand).removeCard(index);
			break;
		case RIGHT:
			((CardsInHandPane) rightCardsInHand).removeCard(index);
			break;
		}
	}

	public VBox getLeftCardsInHand() {
		return leftCardsInHand;
	}

	public VBox getRightCardsInHand() {
		return rightCardsInHand;
	}

}
