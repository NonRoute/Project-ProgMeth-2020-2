package screen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class SelectGameModeButton extends GridPane {
	private Button exitButton;
	private Button pvpButton;
	private Button pvbButton;
	private Button bvbButton;
	private Button howToPlay;

	public SelectGameModeButton() {
		this.setHgap(100);
		this.setVgap(60);
		this.setPrefWidth(1000);
		this.setPrefHeight(150);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setPadding(new Insets(20));

		this.pvpButton = setUpButton("Player vs. Player");
		this.add(pvpButton, 0, 0);
		this.pvbButton = setUpButton("Player vs. Bot");
		this.add(pvbButton, 1, 0);
		this.bvbButton = setUpButton("Bot vs. Bot");
		this.add(bvbButton, 2, 0);
		this.howToPlay = setUpButton("How To Play");
		this.add(howToPlay, 1, 1);
	}

	public Button setUpButton(String name) {
		Button button = new Button(name);
		button.setPrefSize(300, 100);
		button.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		button.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
		button.setBorder(new Border(
				new BorderStroke(Color.SADDLEBROWN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		button.setTextFill(Color.SEASHELL);
		button.setOnAction((ActionEvent e) -> {
			switch (name) {
			case "Player vs. Player":
				GameController.gameMode = "PvP";
				new SettingScreen();
				break;
			case "Player vs. Bot":
				GameController.gameMode = "PvB";
				new SettingScreen();
				break;
			case "Bot vs. Bot":
				GameController.gameMode = "BvB";
				new SettingScreen();
				break;
			case "How To Play":
				// TODO:sTurn on how to play screen
			}
		});
		button.setOnMouseEntered((MouseEvent e) -> {
			button.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
			button.setEffect(new InnerShadow());
		});

		button.setOnMouseExited((MouseEvent e) -> {
			button.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
			button.setEffect(null);
		});
		return button;
	}

}
