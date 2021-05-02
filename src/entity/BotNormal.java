package entity;

import java.util.ArrayList;
import java.util.Collections;

import card.Trickable;
import deck.Deck;
import gui.CardInHandPane;
import logic.Direction;
import logic.GameController;

public class BotNormal extends Bot {

	public BotNormal(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

	public CardInHandPane selectCard() { // select Trickable card first
		ArrayList<CardInHandPane> cardsCanPlay = getAllCardsCanPlay();
		if (cardsCanPlay.size() > 0) {
			Collections.shuffle(cardsCanPlay);
			for (CardInHandPane e : cardsCanPlay) {
				if (e.getCard() instanceof Trickable) {
					return e;
				}
			}
			return cardsCanPlay.get(0); // no Trickable card, select any card
		} else {
			return null; // can't play any card
		}
	}

	public int selectRow() { // select row that have nearest enemy first
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		ArrayList<Integer> excludedRow = new ArrayList<>();
		if (rowCanPlay.size() > 0) {
			int row = GameController.board.getNearestEnemyRow(playingSide, excludedRow);
			while (row != -1) { // found enemy
				if (rowCanPlay.contains(row)) {
					return row;
				} else { // can't play -> find new row
					excludedRow.add(row);
					row = GameController.board.getNearestEnemyRow(playingSide, excludedRow);
				}
			}
			Collections.shuffle(rowCanPlay);
			return rowCanPlay.get(0);
		} else {
			return -1; // can't play any row
		}
	}
}
