package entity;

import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import card.FighterCard;
import card.Trickable;
import deck.Deck;
import gui.CardInHandPane;
import javafx.application.Platform;
import logic.Direction;
import logic.GameController;

public class BotNormal extends Bot {

	public BotNormal(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public CardInHandPane selectCard() { // select Trickable card first
		ArrayList<CardInHandPane> cardsCanPlay = getAllCardsCanPlay();
		if (cardsCanPlay.size() > 0) {
			Collections.shuffle(cardsCanPlay);
			for (CardInHandPane e : cardsCanPlay) {
				if (e.getCard() instanceof Trickable) {
					return e;
				}
			}
			return cardsCanPlay.get(0); // no Trickable card, select any card
		} else {
			return null; // can't play any card
		}
	}

	public int selectRow() { // select row that have nearest enemy first
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		ArrayList<Integer> excludedRow = new ArrayList<>();
		if (rowCanPlay.size() > 0) {
			int row = GameController.board.getNearestEnemyRow(playingSide, excludedRow);
			while (row != -1) { // found enemy
				if (rowCanPlay.contains(row)) {
					return row;
				} else { // can't play -> find new row
					excludedRow.add(row);
					row = GameController.board.getNearestEnemyRow(playingSide, excludedRow);
				}
			}
			Collections.shuffle(rowCanPlay);
			return rowCanPlay.get(0);
		} else {
			return -1; // can't play any row
		}
	}

	public void play() {
		// BotNormal will play card until can't play
		Thread thread = new Thread(() -> {
			try {
				GameController.threadDrawCard.join();
				Thread.sleep(1000);
				while (getAllCardsCanPlay().size() > 0 && selectRow() != -1) {
					// have card can play and have row can play
					Platform.runLater(new Runnable() {
						public void run() {
							CardInHandPane selectCard = selectCard();
							useCard(cardsInHandPane.indexOf(selectCard));
							if (selectCard.getCard() instanceof FighterCard) {
								GameController.board.setCardOnMap(selectCard, selectRow(), getPlayableColumn());
							}
						}
					});
					Thread.sleep(1000);
				}
				GameController.startNextPhase();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		GameController.threadBotPlay = thread;
	}
}
