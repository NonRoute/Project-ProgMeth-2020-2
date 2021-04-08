package logic;

import card.Card;
import card.FighterCard;
import card.TrickCard;

public class Board {
	public static final int NUMBER_OF_ROW = 5;
	public static final int NUMBER_OF_COLUMN = 9;
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

	public void moveAllCard(Direction sideMoveFirst) {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			switch (sideMoveFirst) {
			case LEFT:
				for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
					if (board[r][c].getCardOnCell() instanceof FighterCard) {
						((FighterCard) board[r][c].getCardOnCell()).move();
					}
				}
			case RIGHT:
				for (int c = NUMBER_OF_COLUMN - 1; c >= 0; c--) {
					if (board[r][c].getCardOnCell() instanceof FighterCard) {
						((FighterCard) board[r][c].getCardOnCell()).move();
					}
				}
			}
		}
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

}
