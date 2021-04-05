package logic;

import card.Card;

public class Board {
	private static final int NUMBER_OF_ROW = 5;
	private static final int NUMBER_OF_COLUMN = 9;
	private Cell[][] cellMap;

	public Board() {
		cellMap = new Cell[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
	}

	public boolean setCardOnMap(Cell[][] cellMap, Card card, int row, int column) {
		return cellMap[row][column].setCardOnCell(card);
	}
	
	public boolean removeCardOnMap(Cell[][] cellMap, Card card, int row, int column) {
		return cellMap[row][column].removeCardOnCell(card);
	}
}
