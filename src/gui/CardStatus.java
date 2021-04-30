package gui;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.Board;
import logic.GameController;
import screen.GameScreen;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public abstract class CardStatus {
	protected int row;
	protected int column;
	protected ImageView image;
	protected int attackDamage;
	protected ImageView heart = new ImageView(RenderableHolder.heart);
	protected StackPane heartPane;

	public void showImage() {
		setUpImage();
		setUpHeart();
		GameController.gameScreen.getCardStatusPane().getChildren().addAll(image, heartPane);
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				public void run() {
					GameController.gameScreen.getCardStatusPane().getChildren().removeAll(image, heartPane);
				}
			});

		});
		thread.start();
	}

	public abstract int indentImageX();

	public abstract int indentImageY();

	public int indentHeartX() {
		return 60;
	}

	public int indentHeartY() {
		return 5;
	}

	public int getX() {
		return Board.LAYOUT_X + column * (Board.H_GAP + CardOnBoardPane.CARD_WIDTH);
	}

	public int getY() {
		return Board.LAYOUT_Y + row * (Board.V_GAP + CardOnBoardPane.CARD_HEIGHT);
	}

	public void setUpImage() {
		image.setX(getX() + indentImageX());
		image.setY(getY() + indentImageY());
		image.setPreserveRatio(true);
		image.setFitWidth(CardOnBoardPane.CARD_WIDTH);
		image.setFitHeight(CardOnBoardPane.CARD_HEIGHT);
	}

	public void setUpHeart() {
		heartPane = new StackPane();
		heartPane.setLayoutX(getX() + indentHeartX());
		heartPane.setLayoutY(getY() + indentHeartY());
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font18);
		text.setFill(Color.WHITE);
		text.setText("-" + attackDamage);
		heart.setPreserveRatio(true);
		heart.setFitHeight(40);
		heart.setFitWidth(40);
		heartPane.getChildren().addAll(heart, text);
	}
}
