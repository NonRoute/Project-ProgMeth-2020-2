package gui;

import card.Card;
import javafx.scene.layout.GridPane;

public abstract class CardPane extends GridPane {
	private Card card;

	public Card getCard() {
		return card;
	}

}
