package entity;

import java.util.ArrayList;

import card.Card;
import logic.Direction;

public abstract class Controller extends Entity {

	private int heart;
	private int money;
	private ArrayList<Card> cardsInHand;
	private Direction playingSide;

}
