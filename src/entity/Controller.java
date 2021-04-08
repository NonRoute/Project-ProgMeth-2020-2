package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import deck.Deck;
import logic.Board;
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
		// TODO if card exceed max; not draw
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

	public ArrayList<Integer> getAllRowCanPlay() {
		ArrayList<Integer> RowCanPlay = new ArrayList<>();
		switch (playingSide) {
		case LEFT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, 0)) {
					RowCanPlay.add(i);
				}
			}
		case RIGHT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, Board.NUMBER_OF_COLUMN - 1)) {
					RowCanPlay.add(i);
				}
			}
		}
		return RowCanPlay;
	}

	public int getHeart() {
		return heart;
	}

	public void reduceHeart(int number) {
		if (heart - number <= 0) {
			GameController.isGameEnd = true;
		} else {
			heart -= number;
		}
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
