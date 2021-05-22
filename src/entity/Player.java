package entity;

import deck.Deck;
import logic.Direction;
import logic.GameController;

public class Player extends Controller {
	public Player(int health, int money, Deck deck, Direction playingSide) {
		super(health, money, deck, playingSide);
	}

	@Override
	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

}
