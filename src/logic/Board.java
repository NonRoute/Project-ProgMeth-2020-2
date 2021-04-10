package logic;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.MagicianCard;
import card.Movable;
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
		board[row][column].setCard(card);
	}

	public void removeCardOnMap(int row, int column) {
		board[row][column].removeCard();
	}

	public void moveAllCard(Direction sideMoveFirst) {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			switch (sideMoveFirst) {
			case LEFT:
				for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
					if (board[r][c].getCard() instanceof Movable) {
						((MagicianCard) board[r][c].getCard()).move();
					}
				}
			case RIGHT:
				for (int c = NUMBER_OF_COLUMN - 1; c >= 0; c--) {
					if (board[r][c].getCard() instanceof Movable) {
						((MagicianCard) board[r][c].getCard()).move();
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

	public boolean isEnemy(int row, int column, Direction playingSide) {
		if (!isEmpty(row, column)) {
			return board[row][column].getCard().getPlayingSide().equals(playingSide);
		} else
			return false;
	}

	public boolean haveEnemy(Direction playingSide) {
		return getEnemy(playingSide).size() != 0;
	}

	public ArrayList<Card> getEnemy(Direction playingSide) {
		ArrayList<Card> enemy = new ArrayList<>();
		for (int r = 0; r < NUMBER_OF_ROW; r++) { // loop all cell
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isEnemy(r, c, playingSide)) {
					enemy.add(board[r][c].getCard());
				}
			}
		}
		return enemy;
	}

	public Card getRandomEnemy(Direction playingSide) {
		Random rand = new Random();
		ArrayList<Card> enemy = getEnemy(playingSide);
		if (enemy.size() == 0) {
			return null;
		} else {
			int index = rand.nextInt(enemy.size());
			return enemy.get(index);
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

	public int getNearestEnemyColumn(int row, Direction playingSide) { // return -1 if no enemy
		switch (playingSide) {
		case LEFT:
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isEnemy(row, c, playingSide)) {
					return c;
				}
			}
			return -1;
		case RIGHT:
			for (int c = NUMBER_OF_COLUMN - 1; c >= 0; c--) {
				if (isEnemy(row, c, playingSide)) {
					return c;
				}
			}
			return -1;
		}
		return -1;
	}

	public int getnearestEnemyRow(Direction playingSide, ArrayList<Integer> excludedRow) { // return -1 if no enemy
		switch (playingSide) {
		case LEFT:
			int column = NUMBER_OF_COLUMN;
			int row = -1;
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				if (!excludedRow.contains(r)) {
					if (getNearestEnemyColumn(r, playingSide) < column && getNearestEnemyColumn(r, playingSide) != -1) {
						column = getNearestEnemyColumn(row, playingSide);
						row = r;
					}
				}
			}
			return row;
		case RIGHT:
			int column1 = 0;
			int row1 = -1;
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				if (!excludedRow.contains(r)) {
					if (getNearestEnemyColumn(r, playingSide) > column1
							&& getNearestEnemyColumn(r, playingSide) != -1) {
						column1 = getNearestEnemyColumn(row1, playingSide);
						row1 = r;
					}
				}
			}
			return row1;
		}
		return -1;
	}

	public Cell[] getBoardAtRow(int row) {
		return board[row];
	}

	public Cell[] getBoardAtColumn(int column) {
		Cell[] boardAtColumn = new Cell[NUMBER_OF_COLUMN];
		for (int i = 0; i < NUMBER_OF_ROW; i++) {
			boardAtColumn[i] = board[i][column];
		}
		return boardAtColumn;
	}

	public Cell[][] getBoard() {
		return board;
	}

}
