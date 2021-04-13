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
import sharedObject.RenderableHolder;

public abstract class Controller extends Entity {

	protected int heart;
	protected int money;
	private Deck deck;
	protected HandPane cardsInHandPane;
	protected Direction playingSide;

	public Controller(int heart, int money, Deck deck, Direction playingSide) {
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
		// if card exceed max; not draw
		if (cardsInHandPane.getSize() >= 9) {
			return;
		}
		Thread thread = new Thread(() -> {
			try {
				for (int i = 0; i < number; i++) {
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
	}

	public int getMaxCardCostCanDraw() {
		return GameController.turn + 2;
	}

	public void useCard(int index) {
		money -= cardsInHandPane.get(index).getCost();
		if (cardsInHandPane.get(index) instanceof Trickable)
			if (((Trickable) cardsInHandPane.get(index)).getTrick().isActivateWhenUseCard()) {
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
		case RIGHT:
			for (int i = 0; i < Board.NUMBER_OF_ROW; i++) {
				if (GameController.board.isEmpty(i, Board.NUMBER_OF_COLUMN - 1)) {
					rowCanPlay.add(i);
				}
			}
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
		gc.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		gc.setFill(Color.DARKRED);
		gc.fillText("" + heart, x + 12, y + 33);
		gc.drawImage(RenderableHolder.cost, x + 60, y, 50, 50);
		gc.setFill(Color.DARKGOLDENROD);
		gc.fillText("" + money, x + 72, y + 33);
	}
}
