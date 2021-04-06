package entity;

import logic.Direction;
import logic.GameController;

public class BotNormal extends Bot {

	public BotNormal(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}
	
	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}
}
