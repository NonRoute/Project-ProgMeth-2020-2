package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import card.Trickable;
import deck.Deck;
import gui.CardInHandPane;
import logic.Direction;
import logic.GameController;

public class BotNormal extends Bot {

	public BotNormal(int health, int money, Deck deck, Direction playingSide) {
		super(health, money, deck, playingSide);
	}

	@Override
	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

	@Override
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

	@Override
	public int selectRow() { // 70% select row that have nearest enemy first
		Random rd = new Random();
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		if (rd.nextInt(9) < 7) {
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
		} else {
			if (rowCanPlay.size() > 0) {
				Collections.shuffle(rowCanPlay);
				return rowCanPlay.get(0);
			} else {
				return -1; // can't play any row
			}
		}
	}
}
