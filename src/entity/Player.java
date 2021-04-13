package entity;

import java.util.Random;

import card.Card;
import deck.Deck;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;

public class Player extends Controller {
	public Player(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

}
