package logic;

import card.Card;

public class GameMap {
	private int numberOfRow = 5;
	private int numberOfColumn = 9;
	private Cell[][] cellMap;

	public GameMap() {
		cellMap = new Cell[numberOfRow][numberOfColumn];
	}

	public boolean setCardOnMap(Cell[][] cellMap, Card card, int row, int column) {
		return cellMap[row][column].setCardOnCell(card);
	}
	
	public boolean removeCardOnMap(Cell[][] cellMap, Card card, int row, int column) {
		return cellMap[row][column].removeCardOnCell(card);
	}
}
