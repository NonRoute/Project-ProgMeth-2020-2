package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import deck.Deck;
import gui.CardInHandPane;
import logic.Board;
import logic.Direction;

public abstract class Bot extends Controller {

	public Bot(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public abstract CardInHandPane selectCard();

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

	public boolean isCardCanPlay(CardInHandPane cardPane) {
		return cardPane.getCard().getCost() > money;
	}

	public ArrayList<CardInHandPane> getAllCardsCanPlay() {
		ArrayList<CardInHandPane> CardsCanPlay = new ArrayList<>();
		for (CardInHandPane c : cardsInHandPane.getCardsList()) {
			if (!isCardCanPlay(c)) {
				CardsCanPlay.add(c);
			}
		}
		return CardsCanPlay;
	}
}
