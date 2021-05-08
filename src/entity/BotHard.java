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

	public BotHard(int health, int money, Deck deck, Direction playingSide) {
		super(health, money, deck, playingSide);
	}

	public void drawCard(int number) {
		Thread thread = new Thread(() -> {
			try {
				if (GameController.threadStartAttackCard != null && GameController.threadStartAttackCard.isAlive()) {
					GameController.threadStartAttackCard.join();
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
							SoundHolder.drawCard.play();
							Card card;
							do {
								card = deck.getRandomCard();
							} while (card.getCost() > getMaxCardCostCanDraw()); //redraw if card cost exceed maxCardCostCostCanDraw
							card.setPlayingSide(playingSide); // set playing side to card
							// every FighterCard of HardBot have 1 extra health when draw
							Random rd = new Random();
							if (card instanceof FighterCard && rd.nextInt(9) < 3) {
								((FighterCard) card).setHealth(((FighterCard) card).getHealth() + 1);
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

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
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
