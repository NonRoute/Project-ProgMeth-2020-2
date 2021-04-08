package entity;

import deck.Deck;
import logic.Direction;
import logic.GameController;

public class BotNormal extends Bot {

	public BotNormal(int heart, int money, Deck deck, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, deck, initialNumberOfCardInHand, playingSide);
	}
	
	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}
}
