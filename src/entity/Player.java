package entity;

import java.util.Random;

import card.Card;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;

public class Player extends Controller {
	public Player(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

}
