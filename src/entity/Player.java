package entity;

import logic.Direction;

public class Player extends Controller {

	public Player(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

}
