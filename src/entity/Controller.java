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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.Board;
import logic.Direction;
import logic.GameController;
import screen.EndGame;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public abstract class Controller extends Entity {

	protected int heart;
	protected int money;
	private Deck deck;
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

	public void drawCard(int number) {
		Thread thread = new Thread(() -> {
			try {
				if (GameController.threadAttackCard != null) {
					GameController.threadAttackCard.join();
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

							Card card = (Card) getDeck().getListOfCardsbyCost(costOfCard).get(indexOfCard).clone();
							card.setPlayingSide(playingSide); // set playing side to card
							cardsInHandPane.add(deck.getName(), card);
							// GameController.gameScreen.addCardsInHands(deck.getName(), card);
						}
					});
					Thread.sleep(500); // Delay 0.5 second
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

	public void useCard(int index) {
		money -= cardsInHandPane.get(index).getCost();
		if (cardsInHandPane.get(index) instanceof Trickable) {
			((Trickable) cardsInHandPane.get(index)).activateTrick();
		}
		cardsInHandPane.remove(index);
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

	public int getPlayableColumn() {
		switch (playingSide) {
		case LEFT:
			return 0;
		case RIGHT:
			return (Board.NUMBER_OF_COLUMN - 1);
		}
		return -1;
	}

	public int getHeart() {
		return heart;
	}

	public void reduceHeart(int number) {
		if (heart - number <= 0) {
			heart = 0;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Deck getDeck() {
		return deck;
	}

	public HandPane getCardsInHandPane() {
		return cardsInHandPane;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.heart, x, y, 50, 50);
		gc.setFont(FontHolder.getInstance().font28);
		gc.drawImage(RenderableHolder.cost, x + 60, y, 50, 50);
		gc.setFill(Color.DARKRED);
		if (heart >= 10) {
			gc.fillText("" + heart, x + 14, y + 33);
		} else {
			gc.fillText("" + heart, x + 18, y + 33);
		}
		gc.setFill(Color.DARKGOLDENROD);
		if (money >= 100) {
			gc.fillText("" + money, x + 66, y + 33);
		} else if (money >= 10) {
			gc.fillText("" + money, x + 73, y + 33);
		} else {
			gc.fillText("" + money, x + 80, y + 33);
		}
	}
}
