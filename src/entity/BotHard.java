package entity;

import logic.Direction;
import logic.GameController;

public class BotHard extends Bot {

	public BotHard(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}
	
	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}
}
