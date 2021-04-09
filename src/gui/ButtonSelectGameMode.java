package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
		button.setTextFill(Color.WHITE);
		button.setOnAction((ActionEvent e) -> {
			switch (name) {

			}
		});
		button.setOnMouseMoved((MouseEvent e) -> {
			if (e != null)
				button.setBackground(
						new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		});

		button.setOnMouseExited((MouseEvent e) -> {
			if (e != null)
				button.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		return button;
	}

}
