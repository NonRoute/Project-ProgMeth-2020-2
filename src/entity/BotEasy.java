package entity;

import java.util.ArrayList;
import java.util.Collections;
import card.Card;
import card.Movable;
import deck.Deck;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;

public class BotEasy extends Bot {

	public BotEasy(int heart, int money, Deck deck, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, deck, initialNumberOfCardInHand, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn;
	}

	public Card selectCard() { // select by random
		ArrayList<Card> cardsCanPlay = getAllCardsCanPlay();
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

	public void play() {
		// BotEasy will play card until can't play
		while (getAllCardsCanPlay().size() > 0 && selectRow() != -1) { // have card can play and have row can play
			Card selectCard = selectCard();
			useCard(cardsInHandPane.indexOf(selectCard));
			if (selectCard instanceof Movable) {
				GameController.board.setCardOnMap(selectCard, selectRow(), getPlayableColumn());
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
}
