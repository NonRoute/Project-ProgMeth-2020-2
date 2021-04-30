package entity;

import gui.CardOnBoardPane;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Board;
import logic.GameController;
import screen.GameScreen;

public abstract class CardStatus {
	protected int row;
	protected int column;
	protected ImageView image;

	public void showImage() {
		image.setX(getX() + indentX());
		image.setY(getY() + indentY());
		image.setPreserveRatio(true);
		image.setFitWidth(CardOnBoardPane.CARD_WIDTH);
		image.setFitHeight(CardOnBoardPane.CARD_HEIGHT);
		GameController.gameScreen.getCardStatusPane().getChildren().add(image);
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				public void run() {
					GameController.gameScreen.getCardStatusPane().getChildren().remove(image);
				}
			});

		});
		thread.start();
	}

	public abstract int indentX();

	public abstract int indentY();

	public int getX() {
		return Board.LAYOUT_X + column * (Board.H_GAP + CardOnBoardPane.CARD_WIDTH);
	}

	public int getY() {
		return Board.LAYOUT_Y + row * (Board.V_GAP + CardOnBoardPane.CARD_HEIGHT);
	}
}
