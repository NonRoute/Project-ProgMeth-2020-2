package screen;

import gui.HandPane;
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
//	private GameLogic logic;
	private HandPane leftCardsInHand;
	private HandPane rightCardsInHand;

	public GameScreen() {
		root = new Pane();
		canvas = new Canvas(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);

		borderPane = new BorderPane();
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
		root.getChildren().addAll(canvas, borderPane, getChangePlayingSideButton(), board);

		Scene scene = new Scene(root);
		GameController.primaryStage.setScene(scene);
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				paintComponent();
//				logic.logicUpdate();
				RenderableHolder.getInstance().update();
			}
		};
		animation.start();
	}

	public Button getChangePlayingSideButton() {
		Button nextTurn = new Button();
		ImageView imageView = new ImageView(RenderableHolder.nextTurn);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(40);
		imageView.setFitHeight(40);
		nextTurn.setGraphic(imageView);
		nextTurn.setLayoutX(607);
		nextTurn.setLayoutY(20);
		nextTurn.setPrefSize(40, 40);
		nextTurn.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), new Insets(2))));
		nextTurn.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
				new CornerRadii(5), new BorderWidths(5))));
		nextTurn.setOnMouseClicked((MouseEvent e) -> {
			GameController.switchPlayingSide();
			GameController.board.unHighlightAllCells();
		});
		nextTurn.setOnMouseEntered((MouseEvent e) -> {
			nextTurn.setBackground(
					new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(5), new Insets(2))));
			nextTurn.setEffect(new InnerShadow());
		});

		nextTurn.setOnMouseExited((MouseEvent e) -> {
			nextTurn.setBackground(
					new Background(new BackgroundFill(Color.MINTCREAM, new CornerRadii(5), new Insets(2))));
			nextTurn.setEffect(null);
		});
		return nextTurn;
	}

	public void paintComponent() {
		gc.drawImage(RenderableHolder.backgroundGameScreen, 0, 0, 1280, 720);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible()) {
				entity.draw(gc);
			}
		}
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

	public void unHighlightHandPane() {
		leftCardsInHand.unHighlight();
		rightCardsInHand.unHighlight();
	}

	public VBox getLeftCardsInHand() {
		return leftCardsInHand;
	}

	public VBox getRightCardsInHand() {
		return rightCardsInHand;
	}

}
