package screen;

import java.util.Collections;
import java.util.Random;

import entity.Bot;
import entity.LastUsedCard;
import entity.Phase;
import entity.Turn;
import gui.Board;
import gui.HandPane;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class GameScreen {
	private Pane root;
	private BorderPane borderPane;
	private Pane cardStatusPane;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image background;
	private Turn turn;
	private Phase phase;
	private LastUsedCard lastUsedCard;
	private Button nextPhaseButton;
	private HandPane leftCardsInHand;
	private HandPane rightCardsInHand;
	private ObservableList<Media> mediaList;
	private MediaPlayer mediaplayer;

	public GameScreen() {
		root = new Pane();
		canvas = new Canvas(GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);
		setBackground();
		nextPhaseButton = getStartNextPhaseButton();
		borderPane = new BorderPane();
		turn = new Turn();
		phase = new Phase();
		lastUsedCard = new LastUsedCard();
		borderPane.setPrefSize(GameController.SCREEN_WIDTH, GameController.SCREEN_HEIGHT);
		leftCardsInHand = GameController.leftSideController.getCardsInHandPane();
		rightCardsInHand = GameController.rightSideController.getCardsInHandPane();
		BorderPane.setMargin(leftCardsInHand, new Insets(20));
		BorderPane.setMargin(rightCardsInHand, new Insets(20));
		borderPane.setLeft(leftCardsInHand);
		borderPane.setRight(rightCardsInHand);

		gc = canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0, 1280, 720);

		Board board = new Board();
		GameController.board = board;

		Pane cardStatusPane = new Pane();
		cardStatusPane.setPickOnBounds(false);
		this.cardStatusPane = cardStatusPane;

		root.getChildren().addAll(canvas, borderPane, nextPhaseButton, board, cardStatusPane);

		Scene scene = new Scene(root);
		GameController.primaryStage.setScene(scene);
		getSoundList();
		playSound();
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				paintComponent();
				updateStartNextPhaseButton();
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
		if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) {
			return false;
		}
		if (GameController.threadMoveAllCard != null && GameController.threadMoveAllCard.isAlive()) {
			return false;
		}
		if (GameController.threadBotPlay != null && GameController.threadBotPlay.isAlive()) {
			return false;
		}
		if (GameController.threadMoveAllCard != null && GameController.threadMoveAllCard.isAlive()) {
			return false;
		}
		if (GameController.threadStartAttackCard != null && GameController.threadStartAttackCard.isAlive()) {
			return false;
		}
		return true;
	}

	public Image getBackground() {
		return background;
	}

	public Pane getCardStatusPane() {
		return cardStatusPane;
	}

	public HandPane getLeftCardsInHand() {
		return leftCardsInHand;
	}

	public HandPane getRightCardsInHand() {
		return rightCardsInHand;
	}

	public void getSoundList() {
		ObservableList<Media> mediaList = FXCollections.observableArrayList();
		// add all sound for play
		mediaList.addAll(SoundHolder.gameScreen1, SoundHolder.gameScreen2,
				SoundHolder.gameScreen3, SoundHolder.gameScreen4,
				SoundHolder.gameScreen5, SoundHolder.gameScreen6,
				SoundHolder.gameScreen7, SoundHolder.gameScreen8,
				SoundHolder.gameScreen9, SoundHolder.gameScreen10);
		Collections.shuffle(mediaList);
		this.mediaList = mediaList;
	}

	public Button getStartNextPhaseButton() {
		Button nextPhaseButton = new Button();
		ImageView imageView = new ImageView(RenderableHolder.nextPhase);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(120);
		imageView.setFitHeight(60);
		nextPhaseButton.setGraphic(imageView);
		nextPhaseButton.setLayoutX(580);
		nextPhaseButton.setLayoutY(10);
		nextPhaseButton.setPrefSize(120, 60);
		nextPhaseButton
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, new CornerRadii(30), new Insets(2))));
		nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
				new CornerRadii(30), new BorderWidths(3))));

		nextPhaseButton.setOnMouseClicked((MouseEvent e) -> {
			if (canClickStartNextPhaseButton()) {
				GameController.startNextPhase();
				GameController.board.unHighlightAllCells();
			}
		});
		nextPhaseButton.setOnMouseMoved((MouseEvent e) -> {
			if (canClickStartNextPhaseButton()) {
				nextPhaseButton.setBackground(
						new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(30), new Insets(2))));
				nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
						new CornerRadii(30), new BorderWidths(3))));
				nextPhaseButton.setEffect(new InnerShadow());
			}
		});
		nextPhaseButton.setOnMouseExited((MouseEvent e) -> {
			nextPhaseButton.setBackground(
					new Background(new BackgroundFill(Color.HONEYDEW, new CornerRadii(30), new Insets(2))));
			nextPhaseButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
					new CornerRadii(30), new BorderWidths(3))));
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
		gc.drawImage(background, 0, 0, 1280, 720);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible()) {
				entity.draw(gc);
			}
		}
	}

	public void playSound() {
		if (mediaList.size() == 0) { // if no sound left, getSoundList again
			getSoundList();
		}
		MediaPlayer mediaplayer = new MediaPlayer(mediaList.remove(0)); // play a sound in list and remove it
		this.mediaplayer = mediaplayer;
		mediaplayer.setVolume(5);
		mediaplayer.play();
		mediaplayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				playSound(); // play next sound
			}
		});
	}

	public void setBackground() {
		Random rd = new Random();
		switch (rd.nextInt(5)) {
		case 1:
			this.background = RenderableHolder.backgroundGameScreen1;
			break;
		case 2:
			this.background = RenderableHolder.backgroundGameScreen2;
			break;
		case 3:
			this.background = RenderableHolder.backgroundGameScreen3;
			break;
		case 4:
			this.background = RenderableHolder.backgroundGameScreen4;
			break;
		case 0:
			this.background = RenderableHolder.backgroundGameScreen5;
			break;
		}
	}

	public void stopSound() {
		mediaplayer.stop();
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
