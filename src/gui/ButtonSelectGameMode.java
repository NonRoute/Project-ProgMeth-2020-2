package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

public class ButtonSelectGameMode extends GridPane {
	private Button exitButton;
	private Button pvpButton;
	private Button pvbButton;
	private Button bvbButton;
	private Button howToPlay;

	public ButtonSelectGameMode() {
		this.setHgap(100);
		this.setVgap(60);
		this.setPrefWidth(1000.0);
		this.setPrefHeight(150.0);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setPadding(new Insets(20));

		this.pvpButton = new Button("Player vs. Player");
		setUpButton(pvpButton);
		this.add(pvpButton, 0, 0);
		this.pvbButton = new Button("Player vs. Bot");
		setUpButton(pvbButton);
		this.add(pvbButton, 1, 0);
		this.bvbButton = new Button("Bot vs. Bot");
		setUpButton(bvbButton);
		this.add(bvbButton, 2, 0);
		this.howToPlay = new Button("How To Play");
		setUpButton(howToPlay);
		this.add(howToPlay, 1, 1);
	}

	public void setUpButton(Button button) {
		button.setPrefSize(300, 100);
		button.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		button.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
		button.setTextFill(Color.WHITE);
	}

}
