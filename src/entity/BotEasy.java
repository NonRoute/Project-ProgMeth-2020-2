package entity;

import java.util.ArrayList;
import java.util.Collections;
import card.Card;
import card.FighterCard;
import deck.Deck;
import gui.CardInHandPane;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;

public class BotEasy extends Bot {

	public BotEasy(int heart, int money, Deck deck, Direction playingSide) {
		super(heart, money, deck, playingSide);
	}

	public CardInHandPane selectCard() { // select by random
		ArrayList<CardInHandPane> cardsCanPlay = getAllCardsCanPlay();
		if (cardsCanPlay.size() > 0) {
			Collections.shuffle(cardsCanPlay);
			return cardsCanPlay.get(0);
		} else {
			return null; // can't play any card
		}
	}

	public int selectRow() { // select by random
		ArrayList<Integer> rowCanPlay = getPlayableRow();
		if (rowCanPlay.size() > 0) {
			Collections.shuffle(rowCanPlay);
			return rowCanPlay.get(0);
		} else {
			return -1; // can't play any row
		}
	}

	public void play() {
		// BotEasy will play card until can't play
		Thread thread = new Thread(() -> {
			try {
				GameController.threadDrawCard.join(); // wait for draw card finish
				System.out.println("Bot start play");
				Thread.sleep(1000);
				while (getAllCardsCanPlay().size() > 0 && selectRow() != -1) { // have card can play and have row can
					Platform.runLater(new Runnable() {
						public void run() { // play
							CardInHandPane selectCard = selectCard();
							useCard(cardsInHandPane.indexOf(selectCard));
							if (selectCard.getCard() instanceof FighterCard) {
								GameController.board.setCardOnMap(selectCard, selectRow(), getPlayableColumn());
							}
						}
					});
					Thread.sleep(1000);
				}
				System.out.println("Bot finish play");
				GameController.startNextPhase();
				System.out.println("Bot press change phase");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		GameController.threadBotPlay = thread;
//		Thread thread1 = new Thread(() -> {
//			try {
//				thread.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			GameController.startNextPhase();
//			System.out.println("Bot press change side");
//		});
//		thread1.start();
//		GameController.threadBotPlay = thread1;
	}
}
