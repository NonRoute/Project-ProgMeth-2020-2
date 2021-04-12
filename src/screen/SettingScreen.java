package screen;

import deck.Deck;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class SettingScreen {
	Pane root;
	BorderPane borderPane;
	Button goBackButton;
	Button playButton;
	ImageView image;
	Text warningText;

	public SettingScreen() {
		root = new Pane();

		borderPane = new BorderPane();
		borderPane.setPrefSize(GameController.SCREEN_WIDTH, GameController.SCREEN_HIGHT);

		goBackButton = getGoBackButton();
		BorderPane.setAlignment(goBackButton, Pos.CENTER_RIGHT);
		BorderPane.setMargin(goBackButton, new Insets(20));
		borderPane.setTop(goBackButton);

		borderPane.setCenter(new SettingButton());

		playButton = getPlayButton();
		BorderPane.setAlignment(playButton, Pos.CENTER);
		BorderPane.setMargin(playButton, new Insets(20));
		borderPane.setBottom(playButton);

		warningText = new Text("Please fill all required fields");
		warningText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		warningText.setFill(Color.RED);
		warningText.setX(508);
		warningText.setY(550);
		warningText.setVisible(false);

		Scene scene = new Scene(root);
		switch (GameController.gameMode) {
		case "PvB":
			image = new ImageView(RenderableHolder.backgroundSelectDeckPvB);
			break;
		case "PvP":
			image = new ImageView(RenderableHolder.backgroundSelectDeckPvP);
			break;
		default: // "BvB"
			image = new ImageView(RenderableHolder.backgroundSelectDeckBvB);
			break;
		}
		image.setFitWidth(GameController.SCREEN_WIDTH);
		image.setFitHeight(GameController.SCREEN_HIGHT);

		root.getChildren().addAll(image, borderPane, warningText);
		GameController.primaryStage.setScene(scene);
	}

	public Button getGoBackButton() {
		Button goBackButton = new Button("Go back");
		goBackButton.setPrefSize(120, 50);
		goBackButton.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
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
					new Background(new BackgroundFill(Color.POWDERBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(new InnerShadow());
		});

		goBackButton.setOnMouseExited((MouseEvent e) -> {
			goBackButton
					.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(2))));
			goBackButton.setEffect(null);
		});
		return goBackButton;
	}

	public Button getPlayButton() {
		Button playButton = new Button("Play!");
		playButton.setPrefSize(250, 100);
		playButton
				.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), new Insets(5))));
		playButton.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		playButton.setTextFill(Color.MEDIUMSEAGREEN);
		playButton.setBorder(new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID,
				new CornerRadii(5), new BorderWidths(10))));
		StackPane.setMargin(playButton, new Insets(20));
		playButton.setOnMouseClicked((MouseEvent e) -> {
			switch (GameController.gameMode) {
			case "PvB":
				if (GameController.leftSideDeck == null || GameController.rightSideDeck == null
						|| GameController.difficultyRight == null) {
					warningText.setVisible(true);
				} else {
					warningText.setVisible(false);
					GameController.playGame();
				}
				break;
			case "PvP":
				if (GameController.leftSideDeck == null || GameController.rightSideDeck == null) {
					warningText.setVisible(true);
				} else {
					warningText.setVisible(false);
					GameController.playGame();
				}
				break;
			case "BvB":
				if (GameController.leftSideDeck == null || GameController.rightSideDeck == null
						|| GameController.difficultyLeft == null || GameController.difficultyRight == null) {
					warningText.setVisible(true);
				} else {
					warningText.setVisible(false);
					GameController.playGame();
				}
				break;
			}
		});
		playButton.setOnMouseEntered((MouseEvent e) -> {
			playButton.setBackground(
					new Background(new BackgroundFill(Color.PALEGREEN, new CornerRadii(5), new Insets(5))));
			playButton.setEffect(new InnerShadow());
		});

		playButton.setOnMouseExited((MouseEvent e) -> {
			playButton.setBackground(
					new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5), new Insets(5))));
			playButton.setEffect(null);
		});
		return playButton;
	}
}
