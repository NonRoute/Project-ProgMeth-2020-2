package screen;

import deck.Deck;
import gui.ButtonSelectDeck;
import gui.ButtonSelectGameMode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class SelectDeckScreen {

	public SelectDeckScreen(Stage primaryStage) {
		StackPane root = new StackPane();

		Scene scene = new Scene(root);

		ImageView image = RenderableHolder.backgroundSelectDeck;
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);

		root.getChildren().addAll(image, new ButtonSelectDeck());
		primaryStage.setTitle("Angel vs. Devil");
		primaryStage.setScene(scene);
	}

}
