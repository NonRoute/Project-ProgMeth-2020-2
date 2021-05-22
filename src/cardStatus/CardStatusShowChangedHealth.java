package cardStatus;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class CardStatusShowChangedHealth extends CardStatus {
	protected int attackDamage;
	protected ImageView health = new ImageView(RenderableHolder.health);
	protected StackPane healthPane;

	public int indentHealthX() {
		return 55;
	}

	public int indentHealthY() {
		return 5;
	}

	public void setUpHealth() {
		healthPane = new StackPane();
		healthPane.setLayoutX(getX() + indentHealthX());
		healthPane.setLayoutY(getY() + indentHealthY());
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font21);
		text.setFill(Color.WHITE);
		text.setText("-" + attackDamage);
		health.setPreserveRatio(true);
		health.setFitHeight(40);
		health.setFitWidth(40);
		healthPane.getChildren().addAll(health, text);
	}

	@Override
	public void showImage() {
		setUpImage();
		setUpHealth();
		GameController.gameScreen.getCardStatusPane().getChildren().addAll(image, healthPane);
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(showDuration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					GameController.gameScreen.getCardStatusPane().getChildren().removeAll(image, healthPane);
				}
			});

		});
		thread.start();
	}
}
