package logic;

import card.Card;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Cell extends GridPane {
	private Card cardOnCell;
	private boolean isEmpty = true;
	private int cardWidth = 88;
	private int cardHight = 116;
	private int insets = 2;

	public Cell() {
		this.setPrefWidth(cardWidth);
		this.setPrefHeight(cardHight);
		this.setPadding(new Insets(insets));
	}

	public void setCard(Card card) {
		if (isEmpty) {
			cardOnCell = card;
			isEmpty = false;
		}
	}

	public void removeCard() {
		cardOnCell = null;
		isEmpty = true;

	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Card getCard() {
		return cardOnCell;
	}

}
