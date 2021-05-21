package entity;

import java.util.ArrayList;
import java.util.Random;

import card.FighterCard;
import card.TrickCard;
import deck.Deck;
import gui.Board;
import gui.CardInHandPane;
import javafx.application.Platform;
import logic.Direction;
import logic.GameController;
import trick.Trick;

public abstract class Bot extends Controller {

	public Bot(int health, int money, Deck deck, Direction playingSide) {
		super(health, money, deck, playingSide);
	}

	public ArrayList<CardInHandPane> getAllCardsCanPlay() {
		ArrayList<CardInHandPane> CardsCanPlay = new ArrayList<>();
		for (CardInHandPane c : cardsInHandPane.getCardsList()) {
			if (isCardCanPlay(c)) {
				CardsCanPlay.add(c);
			}
		}
		return CardsCanPlay;
	}

	public FighterCard getTargetCard(Trick trick) {
		FighterCard card = null;
		switch (trick.getFirstParameter()) {
		case 'C':
			card = GameController.board.getRandomFriendly(playingSide);
			break;
		case 'D':
			card = GameController.board.getRandomEnemy(playingSide);
			break;
		}
		return card;
	}

	public boolean isCardCanPlay(CardInHandPane cardPane) {
		boolean canPlay = true;
		if (cardPane.getCard() instanceof TrickCard) {
			// if trickCard, trickCard must canPlay and check cost of card <= money
			canPlay = GameController.board.canPlayTrickCard((TrickCard) cardPane.getCard());
		}
		return cardPane.getCard().getCost() <= money && canPlay;

	}

	public void play() {
		// will play card until can't play
		Thread thread = new Thread(() -> {
			try {
				if (GameController.isGameEnd) { // stop running if game end
					return;
				}
				Thread.sleep(GameController.DELAY_BOT_PLAY);
				if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) { // if still draw card wait to draw finish
					GameController.threadDrawCard.join();
				}
				if (GameController.threadCardMove != null && GameController.threadCardMove.isAlive()) { // if still CardMove wait to finish
					GameController.threadCardMove.join();
				}
				if (GameController.threadStartAttackCard != null && GameController.threadStartAttackCard.isAlive()) { // if still StartAttackCard wait to finish
					GameController.threadStartAttackCard.join();
				}
				while (getAllCardsCanPlay().size() > 0 && selectRow() != -1) { // have card can play and have row can
					if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) { // if still draw card wait to draw finish
						GameController.threadDrawCard.join();
					}
					Platform.runLater(new Runnable() {
						public void run() { // play
							CardInHandPane selectCard = selectCard();
							useCard(cardsInHandPane.indexOf(selectCard));
							if (selectCard.getCard() instanceof FighterCard) {
								// set card on map, if it is FighterCard or MagicianCard
								GameController.board.setCard(selectCard, selectRow(), getPlayableColumn());
							}
						}
					});
					Thread.sleep(GameController.DELAY_BOT_PLAY);
				}
				GameController.startNextPhase();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		GameController.threadBotPlay = thread;
	}

	public int randomIndexCardInHand() {
		Random rand = new Random();
		return rand.nextInt(cardsInHandPane.getSize());
	}

	public int randomRow() {
		Random rand = new Random();
		return rand.nextInt(Board.NUMBER_OF_ROW);
	}

	public abstract CardInHandPane selectCard();

	public abstract int selectRow();
}
