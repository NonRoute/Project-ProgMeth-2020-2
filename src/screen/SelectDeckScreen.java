package screen;

import deck.Deck;
import gui.ButtonSelectDeck;
import gui.ButtonSelectGameMode;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class SelectDeckScreen {

	public SelectDeckScreen() {
		StackPane root = new StackPane();

		Scene scene = new Scene(root);

		ImageView image = RenderableHolder.backgroundSelectDeck;
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);
		
		root.setAlignment(Pos.TOP_RIGHT);
		root.getChildren().addAll(image, new ButtonSelectDeck(), getGoBackButton());
		GameController.primaryStage.setScene(scene);
	}

	public Button getGoBackButton() {
		Button goBackButton = new Button("Go back");
		goBackButton.setPrefSize(120, 50);
		goBackButton.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), Insets.EMPTY)));
		goBackButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		goBackButton.setTextFill(Color.NAVY);
		goBackButton.setBorder(new Border(
				new BorderStroke(Color.NAVY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		StackPane.setMargin(goBackButton, new Insets(20));
		goBackButton.setOnMouseClicked((MouseEvent e) -> {
			new SelectGameModeScreen();
		});
		goBackButton.setOnMouseEntered((MouseEvent e) -> {
			goBackButton.setBackground(
					new Background(new BackgroundFill(Color.POWDERBLUE, new CornerRadii(5), Insets.EMPTY)));
		});

		goBackButton.setOnMouseExited((MouseEvent e) -> {
			goBackButton.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), Insets.EMPTY)));
		});
		return goBackButton;
	}
}
