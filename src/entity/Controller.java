package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import deck.Deck;
import logic.Direction;
import logic.GameController;

public abstract class Controller extends Entity {

	protected int heart;
	protected int money;
	private Deck deck;
	protected ArrayList<Card> cardsInHand;
	protected Direction playingSide;

	public Controller(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		this.heart = Math.max(1, heart);
		this.money = money;
		drawCard(initialNumberOfCardInHand);
		this.playingSide = playingSide;
	}

	public void drawCard(int number) {
		for (int i = 0; i < number; i++) {
			// random pick 1 card from deck
			Random rand = new Random();
			// .nextInt(int) will random value from 0 to int-1
			// random select cost of card
			int costOfCard = rand.nextInt(getMaxCardCostCanDraw() + 1);
			// random select index of card that have this cost
			int indexOfCard = rand.nextInt((getDeck().getNumberOfCardsEachCost()).get(costOfCard));
			Card card = (Card) getDeck().getListOfCardsbyCost(costOfCard).get(indexOfCard).clone();
			card.setPlayingSide(playingSide);
			cardsInHand.add(card);
			// TODO sleep()
		}
	}

	public abstract int getMaxCardCostCanDraw();

	public void useCard(int index) {
		if (cardsInHand.get(index).getEffect().isActivateWhenUseCard()) {
			cardsInHand.get(index).activateEffect();
		}
		cardsInHand.remove(index);
	}

	public int getHeart() {
		return heart;
	}

	public void reduceHeart(int number) {
		//TODO heart <= 0 ; game end
		heart -= number;
	}

	public int getMoney() {
		return money;
	}

	public Deck getDeck() {
		return deck;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}
}
