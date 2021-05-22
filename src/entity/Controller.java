package entity;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.Trickable;
import deck.Deck;
import gui.Board;
import gui.HandPane;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;
import screen.EndGame;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public abstract class Controller extends Entity {

	protected int health;
	protected int money;
	protected Deck deck;
	protected HandPane cardsInHandPane;
	protected Direction playingSide;

	public Controller(int health, int money, Deck deck, Direction playingSide) {
		this.setVisible(true);
		this.health = Math.max(1, health);
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

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.health, x, y, 50, 50);
		gc.setFont(FontHolder.getInstance().font28);
		gc.drawImage(RenderableHolder.cost, x + 60, y, 50, 50);
		gc.setFill(Color.DARKRED);
		gc.fillText("" + health, x + 24 - String.valueOf(health).length() * 5, y + 33);
		gc.setFill(Color.DARKGOLDENROD);
		gc.fillText("" + money, x + 84 - String.valueOf(money).length() * 5, y + 33);
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
						@Override
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

	public HandPane getCardsInHandPane() {
		return cardsInHandPane;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getHealth() {
		return health;
	}

	public abstract int getMaxCardCostCanDraw();

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

	public Card getRandomCardEachCostChanceEqually() {
		// random pick 1 card from deck
		Random rand = new Random();
		// .nextInt(int) will random value from 0 to int-1
		// random select cost of card
		int costOfCard;
		int numberOfCard;
		do {
			costOfCard = rand
					.nextInt(Math.min((getMaxCardCostCanDraw() + 1), getDeck().getNumberOfCardsEachCost().size()));
			// random select index of card that have this cost
			numberOfCard = getDeck().getNumberOfCardsEachCost().get(costOfCard);
		} while (numberOfCard == 0); // random again if no card with this cost

		int indexOfCard = rand.nextInt(numberOfCard);
		Card card = (Card) getDeck().getListOfCardsbyCost(costOfCard).get(indexOfCard).clone();
		return card;
	}

	public void playPlaceCardSound() {
		Random rand = new Random();
		int n = rand.nextInt(2);
		switch (n) {
		case 0:
			SoundHolder.placeCard1.play();
			break;
		case 1:
			SoundHolder.placeCard2.play();
			break;
		case 2:
			SoundHolder.placeCard3.play();
			break;
		}
	}

	public void reduceHealth(int number) {
		if (health - number <= 0) {
			health = 0;
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
			health -= number;
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

}
