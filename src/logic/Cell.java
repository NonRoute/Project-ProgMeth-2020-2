package logic;

import card.Card;

public class Cell {
	private Card cardOnCell;
	private boolean isEmpty = true;

	public void setCardOnCell(Card card) {
		if (isEmpty) {
			cardOnCell = card;
			isEmpty = false;
		}
	}

	public void removeCardOnCell() {
		cardOnCell = null;
		isEmpty = true;

	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Card getCardOnCell() {
		return cardOnCell;
	}

}
