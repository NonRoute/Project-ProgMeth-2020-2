package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import deck.Deck;
import logic.Board;
import logic.Direction;
import logic.GameController;

public abstract class Bot extends Controller {

	public Bot(int heart, int money, Deck deck, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, deck, initialNumberOfCardInHand, playingSide);
	}

	public abstract Card selectCard();

	public abstract int selectRow();

	public abstract void play();

	public int randomRow() {
		Random rand = new Random();
		return rand.nextInt(Board.NUMBER_OF_ROW);
	}

	public int randomIndexCardInHand() {
		Random rand = new Random();
		return rand.nextInt(cardsInHandPane.getSize());
	}

	public boolean isCardCanPlay(Card card) {
		return card.getCost() > money;
	}

	public ArrayList<Card> getAllCardsCanPlay() {
		ArrayList<Card> CardsCanPlay = new ArrayList<>();
		for (Card c : cardsInHandPane.getCardsList()) {
			if (!isCardCanPlay(c)) {
				CardsCanPlay.add(c);
			}
		}
		return CardsCanPlay;
	}
}
