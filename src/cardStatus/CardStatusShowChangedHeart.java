package cardStatus;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class CardStatusShowChangedHeart extends CardStatus {
	protected int attackDamage;
	protected ImageView heart = new ImageView(RenderableHolder.heart);
	protected StackPane heartPane;

	public int indentHeartX() {
		return 55;
	}

	public int indentHeartY() {
		return 5;
	}

	public void setUpHeart() {
		heartPane = new StackPane();
		heartPane.setLayoutX(getX() + indentHeartX());
		heartPane.setLayoutY(getY() + indentHeartY());
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font21);
		text.setFill(Color.WHITE);
		text.setText("-" + attackDamage);
		heart.setPreserveRatio(true);
		heart.setFitHeight(40);
		heart.setFitWidth(40);
		heartPane.getChildren().addAll(heart, text);
	}

	public void showImage() {
		setUpImage();
		setUpHeart();
		GameController.gameScreen.getCardStatusPane().getChildren().addAll(image, heartPane);
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(showDuration);
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
}
