package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.Trickable;
import deck.Deck;
import gui.HandPane;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Board;
import logic.Direction;
import logic.GameController;
import screen.EndGame;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public abstract class Controller extends Entity {

	protected int heart;
	protected int money;
	protected Deck deck;
	protected HandPane cardsInHandPane;
	protected Direction playingSide;

	public Controller(int heart, int money, Deck deck, Direction playingSide) {
		this.setVisible(true);
		this.heart = Math.max(1, heart);
		this.money = money;
		this.deck = deck;
		this.cardsInHandPane = new HandPane();
		this.playingSide = playingSide;
		switch (playingSide) {
		case LEFT:
			setX(200);
			setY(20);
			break;
		case RIGHT:
			setX(970);
			setY(20);
			break;
		}
		RenderableHolder.getInstance().add(this);
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.heart, x, y, 50, 50);
		gc.setFont(FontHolder.getInstance().font28);
		gc.drawImage(RenderableHolder.cost, x + 60, y, 50, 50);
		gc.setFill(Color.DARKRED);
		gc.fillText("" + heart, x + 24 - String.valueOf(heart).length() * 5, y + 33);
		gc.setFill(Color.DARKGOLDENROD);
		gc.fillText("" + money, x + 84 - String.valueOf(money).length() * 5, y + 33);
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
							cardsInHandPane.add(deck.getName(), card);
							// GameController.gameScreen.addCardsInHands(deck.getName(), card);
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

	public HandPane getCardsInHandPane() {
		return cardsInHandPane;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getHeart() {
		return heart;
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

	public int getMoney() {
		return money;
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

	public ArrayList<Integer> getPlayableRow() {
		ArrayList<Integer> rowCanPlay = new ArrayList<>();
		switch (playingSide) {
		case LEFT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, 0)) {
					rowCanPlay.add(i);
				}
			}
			break;
		case RIGHT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, Board.NUMBER_OF_COLUMN - 1)) {
					rowCanPlay.add(i);
				}
			}
			break;
		}
		return rowCanPlay;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public void reduceHeart(int number) {
		if (heart - number <= 0) {
			heart = 0;
			// end game
			switch (playingSide) {
			case LEFT:
				GameController.winner = Direction.RIGHT;
				break;
			case RIGHT:
				GameController.winner = Direction.LEFT;
				break;
			}
			new EndGame();
		} else {
			heart -= number;
		}
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void useCard(int index) {
		GameController.lastUsedCard = cardsInHandPane.getCard(index);
		playPlaceCardSound();
		money -= cardsInHandPane.getCard(index).getCost();
		if (cardsInHandPane.getCard(index) instanceof Trickable) {
			((Trickable) cardsInHandPane.getCard(index)).activateTrick();
		}
		cardsInHandPane.remove(index);
	}

	public void playPlaceCardSound() {
		Random rand = new Random();
		int n = rand.nextInt(2);
		switch (n) {
		case 0:
			SoundHolder.getInstance().placeCard1.play();
			break;
		case 1:
			SoundHolder.getInstance().placeCard2.play();
			break;
		case 2:
			SoundHolder.getInstance().placeCard3.play();
			break;
		}
	}

}
