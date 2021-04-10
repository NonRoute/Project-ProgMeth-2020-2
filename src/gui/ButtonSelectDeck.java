package gui;

import deck.Deck;
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

public class ButtonSelectDeck extends GridPane {
	private MenuButton LeftSideDeck;
	private MenuButton RightSideDeck;
	private MenuButton LeftSideDifficulty;
	private MenuButton RightSideDifficulty;

	public ButtonSelectDeck() {
		this.setHgap(200);
		this.setVgap(300);
		this.setPrefWidth(1000);
		this.setPrefHeight(600);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
		
		LeftSideDeck = getSelectDeckMenuButton(Direction.LEFT);
		this.add(LeftSideDeck, 0, 0);
		RightSideDeck = getSelectDeckMenuButton(Direction.RIGHT);
		this.add(RightSideDeck, 1, 0);
		LeftSideDifficulty = getSelectDifficulty(Direction.LEFT);
		this.add(LeftSideDifficulty, 0, 1);
		RightSideDifficulty = getSelectDifficulty(Direction.RIGHT);
		this.add(RightSideDifficulty, 1, 1);
	}

	public MenuButton getSelectDeckMenuButton(Direction direction) {
		MenuButton menuButton = new MenuButton("Select " + direction.toString().toLowerCase() + " side deck");
		for (Deck e : GameController.Decks) { // Add decks to menuButton
			MenuItem menuItem = new MenuItem(e.getName());
			menuButton.getItems().add(menuItem);
		}
		menuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return menuButton;
	}

	public MenuButton getSelectDifficulty(Direction direction) {
		MenuButton menuButton = new MenuButton("Select " + direction.toString().toLowerCase() + " side bot difficulty");
		MenuItem menuItem1 = new MenuItem("Easy");
		MenuItem menuItem2 = new MenuItem("Normal");
		MenuItem menuItem3 = new MenuItem("Hard");
		menuButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
		menuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		return menuButton;
	}

}
