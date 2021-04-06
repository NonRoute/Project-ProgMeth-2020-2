package logic;

import card.Card;

public class Board {
	private static final int NUMBER_OF_ROW = 5;
	private static final int NUMBER_OF_COLUMN = 9;
	public static Cell[][] board;

	public Board() {
		board = new Cell[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
		for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				board[r][c] = new Cell();
			}
		}

	}

	public boolean setCardOnMap(Cell[][] board, Card card, int row, int column) {
		return board[row][column].setCardOnCell(card);
	}

	public boolean removeCardOnMap(Cell[][] board, Card card, int row, int column) {
		return board[row][column].removeCardOnCell(card);
	}
}
