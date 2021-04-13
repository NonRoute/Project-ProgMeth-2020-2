package screen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.RenderableHolder;

public class EndGame extends StackPane {

	public EndGame() {
		this.setAlignment(Pos.CENTER);

		ImageView image = new ImageView(RenderableHolder.backgroundEndGame);
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);

		Text text = new Text("The winner is " + GameController.winner + " !!");
		text.setFont(Font.font("Arial", FontWeight.BOLD, 50));

		this.getChildren().addAll(image, text);
		Scene scene = new Scene(this);
		GameController.primaryStage.setScene(scene);
	}

}
