package logic;

import card.Card;

public class Cell {
	private Card cardOnCell;
	private boolean isEmpty = true;

	public boolean setCardOnCell(Card card) {
		if (isEmpty) {
			cardOnCell = card;
			isEmpty = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean removeCardOnCell(Card card) {
		if (isEmpty) {
			return false;
		} else {
			cardOnCell = null;
			isEmpty = true;
			return true;
		}
	}

}
