package logic;

import card.Card;

public class Cell {
	private Card cardOnCell;
	private boolean isEmpty = true;

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
