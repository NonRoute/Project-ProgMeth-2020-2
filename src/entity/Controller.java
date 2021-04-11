package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.Trickable;
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

	public Controller(int heart, int money, Deck deck, int initialNumberOfCardInHand, Direction playingSide) {
		this.heart = Math.max(1, heart);
		this.money = money;
		this.deck = deck;
		this.cardsInHand = new ArrayList<>();
		this.playingSide = playingSide;
		drawCard(initialNumberOfCardInHand);
	}

	public void drawCard(int number) {
		// TODO if card exceed max; not draw
		for (int i = 0; i < number; i++) {
			// random pick 1 card from deck
			Random rand = new Random();
			// .nextInt(int) will random value from 0 to int-1
			// random select cost of card
			int costOfCard;
			int numberOfCard;

			do {
				costOfCard = rand
						.nextInt(Math.min((getMaxCardCostCanDraw() + 1), getDeck().getNumberOfCardsEachCost().size()));
				// random select index of card that have this cost
				numberOfCard = getDeck().getNumberOfCardsEachCost().get(costOfCard);
				System.out.println(costOfCard);
			} while (numberOfCard == 0); // random again if no card with this cost
			int indexOfCard = rand.nextInt(numberOfCard);
			Card card = (Card) getDeck().getListOfCardsbyCost(costOfCard).get(indexOfCard).clone();
			card.setPlayingSide(playingSide);
			cardsInHand.add(card);
			// TODO sleep()
		}
	}

	public abstract int getMaxCardCostCanDraw();

	public void useCard(int index) {
		money -= cardsInHand.get(index).getCost();
		if (cardsInHand.get(index) instanceof Trickable)
			if (((Trickable) cardsInHand.get(index)).getTrick().isActivateWhenUseCard()) {
				((Trickable) cardsInHand.get(index)).activateTrick();
			}
		cardsInHand.remove(index);
	}

	public ArrayList<Integer> getPlayableRow() {
		ArrayList<Integer> rowCanPlay = new ArrayList<>();
		switch (playingSide) {
		case LEFT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, 0)) {
					rowCanPlay.add(i);
				}
			}
		case RIGHT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, Board.NUMBER_OF_COLUMN - 1)) {
					rowCanPlay.add(i);
				}
			}
		}
		return rowCanPlay;
	}

	public int getPlayableColumn() {
		switch (playingSide) {
		case LEFT:
			return 0;
		case RIGHT:
			return (Board.NUMBER_OF_COLUMN - 1);
		}
		return -1;
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
