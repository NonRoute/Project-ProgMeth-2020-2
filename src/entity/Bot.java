package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import logic.Board;
import logic.Direction;
import logic.GameController;

public abstract class Bot extends Controller {

	public Bot(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

	public int randomRow() {
		Random rand = new Random();
		return rand.nextInt(Board.NUMBER_OF_ROW);
	}

	public int randomIndexCardInHand() {
		Random rand = new Random();
		return rand.nextInt(cardsInHand.size());
	}

	public boolean canPlay(Card card) {
		return card.getCost() > money;
	}

	public ArrayList<Card> getAllCardsCanPlay() {
		ArrayList<Card> CardsCanPlay = new ArrayList<>();
		for (Card c : cardsInHand) {
			if (!canPlay(c)) {
				CardsCanPlay.add(c);
			}
		}
		return CardsCanPlay;
	}
}
