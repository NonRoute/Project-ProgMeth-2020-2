package logic;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.FighterCard;
import card.MagicianCard;
import card.TrickCard;
import card.Trickable;
import gui.CardInHandPane;
import gui.CardOnBoardPane;
import gui.CardPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board extends GridPane {
	public static final int NUMBER_OF_ROW = 5;
	public static final int NUMBER_OF_COLUMN = 9;
	private ObservableList<ObservableList<Cell>> boardCells = FXCollections.observableArrayList();

	public Board() {
		this.setPrefWidth(840);
		this.setPrefHeight(610);
		this.setAlignment(Pos.CENTER);
		this.setLayoutX(220);
		this.setLayoutY(90);
		this.setVgap(5);
		this.setHgap(5);
		this.setPadding(new Insets(5));
		this.setBackground(new Background(new BackgroundFill(Color.PERU, CornerRadii.EMPTY, Insets.EMPTY)));
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			boardCells.add(FXCollections.observableArrayList());
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				Cell cell = new Cell(r, c);
				boardCells.get(r).add(cell);
				this.add(cell, c, r);
			}
		}
	}

	public void allCardAttack() {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (!isEmpty(r, c)) {
					boardCells.get(r).get(c).getCardOnBoardPane().attack();
				}
			}
		}
	}

	public void attackCard(int row, int column, int attackDamage) {
		boardCells.get(row).get(column).getCard().reduceHeart(attackDamage);
	}

	public ObservableList<ObservableList<Cell>> getBoard() {
		return boardCells;
	}

	public ObservableList<Cell> getBoardAtColumn(int column) {
		ObservableList<Cell> boardAtColumn = FXCollections.observableArrayList();
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			boardAtColumn.add(boardCells.get(r).get(column));
		}
		return boardAtColumn;
	}

	public ObservableList<Cell> getBoardAtRow(int row) {
		return boardCells.get(row);
	}

	public ArrayList<FighterCard> getEnemy(Direction playingSide) {
		ArrayList<FighterCard> enemy = new ArrayList<>();
		for (int r = 0; r < NUMBER_OF_ROW; r++) { // loop all cell
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isEnemy(r, c, playingSide)) {
					enemy.add(boardCells.get(r).get(c).getCard());
				}
			}
		}
		return enemy;
	}

	public ArrayList<FighterCard> getFriendly(Direction playingSide) {
		ArrayList<FighterCard> friendly = new ArrayList<>();
		for (int r = 0; r < NUMBER_OF_ROW; r++) { // loop all cell
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isFriendly(r, c, playingSide)) {
					friendly.add(boardCells.get(r).get(c).getCard());
				}
			}
		}
		return friendly;
	}

	public int getNearestEnemyColumn(int row, Direction playingSide) { // return -1 if no enemy
		// find the first column that is enemy
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

	public int getNearestEnemyRow(Direction playingSide, ArrayList<Integer> excludedRow) { // return -1 if no enemy
		// find a row that enemy is nearest
		switch (playingSide) {
		case LEFT:
			int column = NUMBER_OF_COLUMN;
			int row = -1;
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				if (!excludedRow.contains(r)) {
					if (getNearestEnemyColumn(r, playingSide) < column && getNearestEnemyColumn(r, playingSide) != -1) {
						// find nearer row
						column = getNearestEnemyColumn(r, playingSide);
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
						column1 = getNearestEnemyColumn(r, playingSide);
						row1 = r;
					}
				}
			}
			return row1;
		}
		return -1;
	}

	public FighterCard getRandomEnemy(Direction playingSide) {
		Random rand = new Random();
		ArrayList<FighterCard> enemy = getEnemy(playingSide);
		if (enemy.size() == 0) {
			return null;
		} else {
			int index = rand.nextInt(enemy.size());
			return enemy.get(index);
		}
	}

	public FighterCard getRandomFriendly(Direction playingSide) {
		Random rand = new Random();
		ArrayList<FighterCard> friendly = getFriendly(playingSide);
		if (friendly.size() == 0) {
			return null;
		} else {
			int index = rand.nextInt(friendly.size());
			return friendly.get(index);
		}
	}

	public boolean haveEnemy(Direction playingSide) {
		return getEnemy(playingSide).size() != 0;
	}

	public boolean haveFriendly(Direction playingSide) {
		return getFriendly(playingSide).size() != 0;
	}

	public void highlight(int row, int column) {
		boardCells.get(row).get(column).highlight();
	}

	public void highlightAllCell() {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				boardCells.get(r).get(c).highlight();
			}
		}
	}

	public void highlightCellCanPlay(CardInHandPane selectedCardPane) {
		if (selectedCardPane.getCard() instanceof FighterCard) {// if not trickCard
			for (int r = 0; r < NUMBER_OF_ROW; r++) {
				switch (GameController.selectedCardPane.getCard().getPlayingSide()) {
				case LEFT:
					if (isEmpty(r, 0)) {
						boardCells.get(r).get(0).highlight();
					}
					break;
				case RIGHT:
					if (isEmpty(r, NUMBER_OF_COLUMN - 1)) {
						boardCells.get(r).get(NUMBER_OF_COLUMN - 1).highlight();
					}
					break;
				}
			}
		} else {
			switch (((TrickCard) selectedCardPane.getCard()).getTrick().getFirstParameter()) {
			case 'A': // random friendly
				if (haveFriendly(selectedCardPane.getCard().getPlayingSide())) {
					highlightAllCell();
				}
				break;
			case 'B': // random enemy
				if (haveEnemy(selectedCardPane.getCard().getPlayingSide())) {
					highlightAllCell();
				}
				break;
			case 'C': // select friendly
				hightlightFriendly(selectedCardPane.getCard().getPlayingSide());
				break;
			case 'D': // select enemy
				hightlightEnemy(selectedCardPane.getCard().getPlayingSide());
				break;
			case 'T':
			case 'E':
			case 'S': // highlight all
				highlightAllCell();
				break;
			}
		}
	}

	public boolean canPlayTrickCard(TrickCard trickCard) {
		switch (trickCard.getTrick().getFirstParameter()) {
		case 'A':
		case 'C': // trick to friendly
			if (haveFriendly(trickCard.getPlayingSide())) {
				return true;
			}
			break;
		case 'B':
		case 'D': // trick to enemy
			if (haveEnemy(trickCard.getPlayingSide())) {
				return true;
			}
			break;
		case 'T':
		case 'E':
		case 'S':
			return true;
		}
		return false;
	}

	public void hightlightEnemy(Direction playingSide) {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isEnemy(r, c, playingSide)) {
					highlight(r, c);
				}
			}
		}
	}

	public void hightlightFriendly(Direction playingSide) {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (isFriendly(r, c, playingSide)) {
					highlight(r, c);
				}
			}
		}
	}

	public boolean isEmpty(int row, int column) { // also return false if out of board
		if (!isOutOfBoard(row, column)) {
			return boardCells.get(row).get(column).isEmpty();
		} else {
			return false;
		}
	}

	public boolean isEnemy(int row, int column, Direction playingSide) { // if OutOfBoard return false
		if (!isEmpty(row, column) && !isOutOfBoard(row, column)) {
			return !boardCells.get(row).get(column).getCard().getPlayingSide().equals(playingSide);
		} else
			return false;
	}

	public boolean isFriendly(int row, int column, Direction playingSide) { // if OutOfBoard return false
		if (!isEmpty(row, column) && !isOutOfBoard(row, column)) {
			return boardCells.get(row).get(column).getCard().getPlayingSide().equals(playingSide);
		} else
			return false;
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

	public void moveAllCard(Direction playingsideMoveFirst) {
		Thread thread = new Thread(() -> {
			try {
				for (int r = 0; r < NUMBER_OF_ROW; r++) {
					switch (playingsideMoveFirst) {
					case LEFT: // each row, move card left playing side first
						moveLeftPlayingSideCard(r);
						moveRightPlayingSideCard(r);
						break;
					case RIGHT: // each row, move card right playing side first
						moveRightPlayingSideCard(r);
						moveLeftPlayingSideCard(r);
						break;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		GameController.threadAllCardMove = thread;
		thread.start();
	}

	public void moveLeftPlayingSideCard(int r) throws InterruptedException {
		for (int c = NUMBER_OF_COLUMN - 1; c >= 0; c--) { // move card left playing side, move from right to left
			if (!isEmpty(r, c)) {
				if (boardCells.get(r).get(c).getCardOnBoardPane().getCard().getPlayingSide() == Direction.LEFT) {
					boardCells.get(r).get(c).getCardOnBoardPane().move();
					GameController.threadCardMove.join();
				}
			}
		}
	}

	public void moveRightPlayingSideCard(int r) throws InterruptedException {
		for (int c = 0; c < NUMBER_OF_COLUMN; c++) { // move card right playing side, from left to right
			if (!isEmpty(r, c)) {
				if (boardCells.get(r).get(c).getCardOnBoardPane().getCard().getPlayingSide() == Direction.RIGHT) {
					boardCells.get(r).get(c).getCardOnBoardPane().move();
					GameController.threadCardMove.join();
				}
			}
		}
	}

	public void removeCardOnMap(int row, int column) {
		boardCells.get(row).get(column).removeCard();
	}

	public void removeDeadCards() {
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (!isEmpty(r, c)) {
					if (boardCells.get(r).get(c).getCard().getHeart() <= 0) {
						removeCardOnMap(r, c);
					}
				}
			}
		}
	}

	public void setCardOnMap(CardPane cardPane, int row, int column) {
		boardCells.get(row).get(column).setCard(cardPane);
		unHighlightAllCells();
	}

	public void unHighlightAllCells() { // called when click next turn and play card
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				boardCells.get(r).get(c).unhighlight();
			}
		}
	}

	public void update() { // update GUI of card if card ability change
		for (int r = 0; r < NUMBER_OF_ROW; r++) {
			for (int c = 0; c < NUMBER_OF_COLUMN; c++) {
				if (!isEmpty(r, c)) {
					// recreate cardOnBoardPane
					CardOnBoardPane cardPane = new CardOnBoardPane(boardCells.get(r).get(c).getCard());
					removeCardOnMap(r, c);
					setCardOnMap(cardPane, r, c);
				}
			}
		}
	}

}
