package entity;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
import logic.Direction;
import logic.GameController;

public abstract class Controller extends Entity {

	protected int heart;
	protected int money;
	private Deck deck;
	private ArrayList<Card> cardsInHand;
	protected Direction playingSide;

	public Controller(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		this.heart = Math.max(1, heart);
		this.money = money;
		drawCard(initialNumberOfCardInHand);
		this.playingSide = playingSide;
	}

	public abstract void drawCard(int number);
//		for (int i = 0; i < number; i++) {
//			// random pick 1 card from deck
//			cardsInHand.add(card);

	public void useCard(int index) {
	}
}
