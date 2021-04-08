package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import card.Card;
import card.FighterCard;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;

public class BotEasy extends Bot {

	public BotEasy(int heart, int money, int initialNumberOfCardInHand, Direction playingSide) {
		super(heart, money, initialNumberOfCardInHand, playingSide);
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn;
	}

	public Card selectCard() {
		ArrayList<Card> CardsCanPlay = getAllCardsCanPlay();
		if (CardsCanPlay.size() > 0) {
			Collections.shuffle(CardsCanPlay);
			return CardsCanPlay.get(0);
		} else {
			return null; // can't play any card
		}
	}

	public int selectRow() {
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		if (rowCanPlay.size() > 0) {
			Collections.shuffle(rowCanPlay);
			return rowCanPlay.get(0);
		} else {
			return -1; // can't play any row
		}
	}

	public void play() {
		// for BotEasy will play until can't play
		while (getAllCardsCanPlay().size() > 0 && selectRow() != -1) { // have card can play and have row can play
			Card selectCard = selectCard();
			useCard(cardsInHand.indexOf(selectCard));
			if (selectCard instanceof FighterCard) {
				GameController.board.setCardOnMap(selectCard, selectRow(), getPlayableColumn());
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
}
