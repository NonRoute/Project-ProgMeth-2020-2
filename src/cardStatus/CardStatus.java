package cardStatus;

import gui.CardOnBoardPane;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.Board;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class CardStatus {
	protected int showDuration;
	protected int row;
	protected int column;
	protected ImageView image;

	public int getX() {
		return Board.LAYOUT_X + column * (Board.H_GAP + CardOnBoardPane.CARD_WIDTH);
	}

	public int getY() {
		return Board.LAYOUT_Y + row * (Board.V_GAP + CardOnBoardPane.CARD_HEIGHT);
	}

	public int indentImageX() {
		return 8;
	}

	public int indentImageY() {
		return 22;
	}

	public void setUpImage() {
		image.setX(getX() + indentImageX());
		image.setY(getY() + indentImageY());
		image.setPreserveRatio(true);
		image.setFitWidth(CardOnBoardPane.CARD_WIDTH - 6);
		image.setFitHeight(CardOnBoardPane.CARD_HEIGHT - 6);
	}

	public void showImage() {
		setUpImage();
		GameController.gameScreen.getCardStatusPane().getChildren().addAll(image);
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(showDuration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				public void run() {
					GameController.gameScreen.getCardStatusPane().getChildren().removeAll(image);
				}
			});

		});
		thread.start();
	}
}
