package entity;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
import logic.Direction;
import logic.GameController;

public abstract class Controller extends Entity {

	private int heart;
	private int money;
	private Deck deck;
	private ArrayList<Card> cardsInHand;
	private Direction playingSide;

	public Controller(int heart, int money, ArrayList<Card> cardsInHand, Direction playingSide) {
		this.heart = Math.max(1, heart);
		this.money = GameController.initialMoney;
		drawCard(GameController.initialNumberOfCardInHand);
		this.playingSide = playingSide;
	}

	public void drawCard(int number) {
		for (int i = 0; i < number; i++) {
			// random pick 1 card from deck
			cardsInHand.add(card);
		}
	}

	public void useCard(int index) {
	}
}
