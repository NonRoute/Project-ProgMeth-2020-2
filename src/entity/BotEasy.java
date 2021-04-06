package entity;

import logic.Direction;
import logic.GameController;

public class BotEasy extends Bot {

	public BotEasy(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn;
	}
}
