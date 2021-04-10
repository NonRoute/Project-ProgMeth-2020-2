package gui;

import deck.Deck;
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
		LeftSideDeck = getSelectDeckMenuButton(Direction.LEFT);
		RightSideDeck = getSelectDeckMenuButton(Direction.RIGHT);
		LeftSideDifficulty = getSelectDifficulty(Direction.LEFT);
		RightSideDifficulty = getSelectDifficulty(Direction.RIGHT);
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
