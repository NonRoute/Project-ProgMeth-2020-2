package screen;

import deck.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Direction;
import logic.GameController;

public class SettingButton extends GridPane {
	private MenuButton LeftSideDeck;
	private MenuButton RightSideDeck;
	private MenuButton LeftSideDifficulty;
	private MenuButton RightSideDifficulty;

	public SettingButton() {
		this.setHgap(200);
		this.setVgap(200);
		this.setPrefWidth(1000);
		this.setPrefHeight(600);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));

		LeftSideDeck = getSelectDeckButton(Direction.LEFT);
		this.add(LeftSideDeck, 0, 0);
		RightSideDeck = getSelectDeckButton(Direction.RIGHT);
		this.add(RightSideDeck, 1, 0);
		switch (GameController.gameMode) {
		case "PvB":
			RightSideDifficulty = getSelectDifficultyButton(Direction.RIGHT);
			this.add(RightSideDifficulty, 1, 1);
			break;
		case "PvP":
			break;
		case "BvB":
			LeftSideDifficulty = getSelectDifficultyButton(Direction.LEFT);
			this.add(LeftSideDifficulty, 0, 1);
			RightSideDifficulty = getSelectDifficultyButton(Direction.RIGHT);
			this.add(RightSideDifficulty, 1, 1);
			break;
		}
	}

	public MenuButton getSelectDeckButton(Direction direction) {
		MenuButton menuButton = new MenuButton("Select " + direction.toString().toLowerCase() + " side deck");
		for (Deck e : GameController.Decks) { // Add decks to menuButton
			MenuItem menuItem = new MenuItem(e.getName());
			menuButton.getItems().add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					switch (direction) {
					case LEFT:
						GameController.leftSideDeck = e;
						break;
					case RIGHT:
						GameController.rightSideDeck = e;
						break;
					}
					menuButton.setText(e.getName());
				}
			});
		}
		menuButton.setPrefSize(400, 40);
		menuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return menuButton;
	}

	public MenuButton getSelectDifficultyButton(Direction direction) {
		MenuButton menuButton = new MenuButton("Select " + direction.toString().toLowerCase() + " side bot difficulty");
		menuButton.setPrefSize(400, 40);
		MenuItem menuItem1 = new MenuItem("Easy");
		menuItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				menuButton.setText("Easy");
				switch (direction) {
				case LEFT:
					GameController.difficultyLeft = "Easy";
					break;
				case RIGHT:
					GameController.difficultyRight = "Easy";
					break;
				}
			}
		});
		MenuItem menuItem2 = new MenuItem("Normal");
		menuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				menuButton.setText("Normal");
				switch (direction) {
				case LEFT:
					GameController.difficultyLeft = "Normal";
					break;
				case RIGHT:
					GameController.difficultyRight = "Normal";
					break;
				}
			}
		});
		MenuItem menuItem3 = new MenuItem("Hard");
		menuItem3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				menuButton.setText("Hard");
				switch (direction) {
				case LEFT:
					GameController.difficultyLeft = "Hard";
					break;
				case RIGHT:
					GameController.difficultyRight = "Hard";
					break;
				}
			}
		});
		menuButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
		menuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return menuButton;
	}

}
