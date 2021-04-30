package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import card.Card;
import card.FighterCard;
import card.Trickable;
import deck.Deck;
import gui.CardInHandPane;
import javafx.application.Platform;
import logic.Direction;
import logic.GameController;
import sharedObject.SoundHolder;

public class BotHard extends Bot {

	public BotHard(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public void drawCard(int number) {
		Thread thread = new Thread(() -> {
			try {
				if (GameController.threadAttackCard != null && GameController.threadAttackCard.isAlive()) {
					GameController.threadAttackCard.join();
				}
				if (GameController.isGameEnd) { // stop running if game end
					return;
				}
				// if card exceed max; not draw
				for (int i = 0; i < number; i++) {
					if (cardsInHandPane.getSize() >= 9) {
						break;
					}
					Platform.runLater(new Runnable() {
						public void run() {
							if (GameController.isGameEnd) { // stop running if game end
								return;
							}
							// random pick 1 card from deck
							Random rand = new Random();
							// .nextInt(int) will random value from 0 to int-1
							// random select cost of card
							int costOfCard;
							int numberOfCard;
							do {
								costOfCard = rand.nextInt(Math.min((getMaxCardCostCanDraw() + 1),
										getDeck().getNumberOfCardsEachCost().size()));
								// random select index of card that have this cost
								numberOfCard = getDeck().getNumberOfCardsEachCost().get(costOfCard);
							} while (numberOfCard == 0); // random again if no card with this cost

							int indexOfCard = rand.nextInt(numberOfCard);
							SoundHolder.getInstance().drawCard.play();
							Card card = (Card) getDeck().getListOfCardsbyCost(costOfCard).get(indexOfCard).clone();
							card.setPlayingSide(playingSide); // set playing side to card
							// every FighterCard of HardBot have 1 extra heart when draw
							if (card instanceof FighterCard) {
								((FighterCard) card).setHeart(((FighterCard) card).getHeart() + 1);
							}
							cardsInHandPane.add(deck.getName(), card);
						}
					});
					Thread.sleep(GameController.DELAY_DRAW_CARD); // Delay
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		GameController.threadDrawCard = thread;
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

}
