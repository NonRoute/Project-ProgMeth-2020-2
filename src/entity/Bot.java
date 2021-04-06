package entity;

import logic.Direction;

public abstract class Bot extends Controller {

	public Bot(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

}
