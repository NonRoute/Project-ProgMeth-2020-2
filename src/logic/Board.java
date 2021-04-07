package logic;

import card.Card;

public class Board {
	private static final int NUMBER_OF_ROW = 5;
	private static final int NUMBER_OF_COLUMN = 9;
	private Cell[][] board;

	public Board() {
		board = new Cell[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
		for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				board[r][c] = new Cell();
			}
		}

	}

	public void setCardOnMap(Card card, int row, int column) {
		board[row][column].setCardOnCell(card);
	}

	public void removeCardOnMap(int row, int column) {
		board[row][column].removeCardOnCell();
	}

	public boolean isEmpty(int row, int column) {
		if (!isOutOfBoard(row, column)) {
			return board[row][column].isEmpty();
		} else {
			return false;
		}
	}

	public boolean isOutOfBoard(int row, int column) {
		if (column < 0 || column >= NUMBER_OF_COLUMN) {
			return true;
		} else if (row < 0 || row >= NUMBER_OF_ROW) {
			return true;
		} else {
			return false;
		}
	}

	public Direction getDirectionOutOfBoard(int column) {
		if (column >= NUMBER_OF_COLUMN) {
			return Direction.RIGHT;
		} else {
			return Direction.LEFT;
		}
	}
}
