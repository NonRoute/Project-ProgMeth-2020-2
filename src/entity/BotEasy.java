package entity;

import java.util.ArrayList;
import java.util.Collections;

import deck.Deck;
import gui.CardInHandPane;
import logic.Direction;

public class BotEasy extends Bot {

	public BotEasy(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public CardInHandPane selectCard() { // select by random
		ArrayList<CardInHandPane> cardsCanPlay = getAllCardsCanPlay();
		if (cardsCanPlay.size() > 0) {
			Collections.shuffle(cardsCanPlay);
			return cardsCanPlay.get(0);
		} else {
			return null; // can't play any card
		}
	}

	public int selectRow() { // select by random
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		if (rowCanPlay.size() > 0) {
			Collections.shuffle(rowCanPlay);
			return rowCanPlay.get(0);
		} else {
			return -1; // can't play any row
		}
	}
}
