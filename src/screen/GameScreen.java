package screen;

import entity.Bot;
import gui.HandPane;
import gui.TurnText;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Board;
import logic.Direction;
import logic.GameController;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen {
	private Pane root;
	private BorderPane borderPane;
	private Canvas canvas;
	private GraphicsContext gc;
	private TurnText turnText;
	private Button nextPhaseButton;
//	private GameLogic logic;
	private HandPane leftCardsInHand;
	private HandPane rightCardsInHand;

	public GameScreen() {
		root = new Pane();
		canvas = new Canvas(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);
		nextPhaseButton = getStartNextPhaseButton();
		borderPane = new BorderPane();
		turnText = new TurnText();
		borderPane.setPrefSize(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);
		leftCardsInHand = GameController.leftSideController.getCardsInHandPane();
		rightCardsInHand = GameController.rightSideController.getCardsInHandPane();
		BorderPane.setMargin(leftCardsInHand, new Insets(20));
		BorderPane.setMargin(rightCardsInHand, new Insets(20));
		borderPane.setLeft(leftCardsInHand);
		borderPane.setRight(rightCardsInHand);

		gc = canvas.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.backgroundGameScreen, 0, 0, 1280, 720);
//		logic = new GameLogic();

		Board board = new Board();
		GameController.board = board;
		root.getChildren().addAll(canvas, borderPane, nextPhaseButton, board);

		Scene scene = new Scene(root);
		GameController.primaryStage.setScene(scene);
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				paintComponent();
//				logic.logicUpdate();
				updateStartNextPhaseButton();
//				GameController.board.update();
				RenderableHolder.getInstance().update();
			}
		};
		animation.start();
	}

	public boolean canClickStartNextPhaseButton() {
		if (GameController.currentPlayingSide == Direction.LEFT && GameController.leftSideController instanceof Bot) {
			return false;
		}
		if (GameController.currentPlayingSide == Direction.RIGHT && GameController.rightSideController instanceof Bot) {
			return false;
		}
		if (GameController.threadDrawCard != null) {
			if (GameController.threadDrawCard.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAllCardMove != null) {
			if (GameController.threadAllCardMove.isAlive()) {
				return false;
			}
		}
		if (GameController.threadBotPlay != null) {
			if (GameController.threadBotPlay.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAllCardMove != null) {
			if (GameController.threadAllCardMove.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAttackCard != null) {
			if (GameController.threadAttackCard.isAlive()) {
				return false;
			}
		}
		return true;
	}

	public HandPane getLeftCardsInHand() {
		return leftCardsInHand;
	}

	public HandPane getRightCardsInHand() {
		return rightCardsInHand;
	}

	public Button getStartNextPhaseButton() {
		Button nextPhaseButton = new Button();
		ImageView imageView = new ImageView(RenderableHolder.nextTurn);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(40);
		imageView.setFitHeight(40);
		nextPhaseButton.setGraphic(imageView);
		nextPhaseButton.setLayoutX(607);
		nextPhaseButton.setLayoutY(20);
		nextPhaseButton.setPrefSize(40, 40);
		nextPhaseButton
				.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), new Insets(2))));
		nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
				new CornerRadii(5), new BorderWidths(5))));

		nextPhaseButton.setOnMouseClicked((MouseEvent e) -> {
			if (canClickStartNextPhaseButton()) {
				System.out.println("CLICK");
				GameController.startNextPhase();
				GameController.board.unHighlightAllCells();
			}
		});
		nextPhaseButton.setOnMouseMoved((MouseEvent e) -> {
			if (canClickStartNextPhaseButton()) {
				nextPhaseButton.setBackground(
						new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(5), new Insets(2))));
				nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
						new CornerRadii(5), new BorderWidths(5))));
				nextPhaseButton.setEffect(new InnerShadow());
			}
		});
		nextPhaseButton.setOnMouseExited((MouseEvent e) -> {
			nextPhaseButton.setBackground(
					new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), new Insets(2))));
			nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
					new CornerRadii(5), new BorderWidths(5))));
			nextPhaseButton.setEffect(null);
		});
		return nextPhaseButton;
	}

	public void highlightHandPane(Direction direction) {
		switch (direction) {
		case LEFT:
			leftCardsInHand.highlight();
			break;
		case RIGHT:
			rightCardsInHand.highlight();
			break;
		}
	}

	public void paintComponent() {
		gc.drawImage(RenderableHolder.backgroundGameScreen, 0, 0, 1280, 720);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible()) {
				entity.draw(gc);
			}
		}
	}

	public void unHighlightHandPane() {
		leftCardsInHand.unHighlight();
		rightCardsInHand.unHighlight();
	}

	public void updateStartNextPhaseButton() {
		if (!canClickStartNextPhaseButton()) {
			nextPhaseButton.setVisible(false);
		} else {
			nextPhaseButton.setVisible(true);
		}
	}

}
