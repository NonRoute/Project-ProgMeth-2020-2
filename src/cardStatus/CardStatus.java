package cardStatus;

import gui.Board;
import gui.CardOnBoardPane;
import gui.Cell;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import logic.GameController;

public class CardStatus {
	protected int showDuration;
	protected int row;
	protected int column;
	protected ImageView image;

	public int getX() {
		return Board.LAYOUT_X + column * (Board.H_GAP + Cell.CARD_WIDTH);
	}

	public int getY() {
		return Board.LAYOUT_Y + row * (Board.V_GAP + Cell.CARD_HEIGHT);
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
		image.setFitWidth(Cell.CARD_WIDTH - 6);
		image.setFitHeight(Cell.CARD_HEIGHT - 6);
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
