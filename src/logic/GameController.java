package logic;

import java.util.ArrayList;
import java.util.Random;

import card.FighterCard;
import deck.Deck;
import entity.Bot;
import entity.BotEasy;
import entity.BotHard;
import entity.BotNormal;
import entity.Controller;
import entity.Player;
import gui.CardInHandPane;
import javafx.application.Platform;
import javafx.stage.Stage;
import screen.GameScreen;

public class GameController {
	public static Thread threadDrawCard;
	public static Thread threadBotPlay;
	public static Thread threadCardMove;
	public static Thread threadAllCardMove;
	public static Thread threadAttackCard;

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HIGHT = 720;
	public static Stage primaryStage;
	public static GameScreen gameScreen;

	public static ArrayList<Deck> Decks = new ArrayList<>();
	private static Deck angelDeck;
	private static Deck devilDeck;
	private static Deck testDeck; // TODO Remove this when game finish

	public static Board board;
	public static int turn;
	public static boolean isPhaseOneEnd;
	public static Direction currentPlayingSide;

	public static Controller leftSideController;
	public static Controller rightSideController;

	public static Direction winner;
	public static CardInHandPane selectedCardPane;
	public static FighterCard targetCard;

	public static String gameMode;
	public static Deck leftSideDeck;
	public static Deck rightSideDeck;
	public static String difficultyLeft;
	public static String difficultyRight;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
		testDeck = new Deck("Test", "TestDeck.csv"); // TODO Remove this when game finish
	}

	public static void initializeGameBvB() {
		switch (difficultyLeft) {
		case "Easy":
			leftSideController = new BotEasy(20, 1, rightSideDeck, Direction.LEFT);
			break;
		case "Normal":
			leftSideController = new BotNormal(20, 1, rightSideDeck, Direction.LEFT);
			break;
		case "Hard":
			leftSideController = new BotHard(30, 1, rightSideDeck, Direction.LEFT);
			break;
		}
		switch (difficultyRight) {
		case "Easy":
			rightSideController = new BotEasy(20, 1, rightSideDeck, Direction.RIGHT);
			break;
		case "Normal":
			rightSideController = new BotNormal(20, 1, rightSideDeck, Direction.RIGHT);
			break;
		case "Hard":
			rightSideController = new BotHard(30, 1, rightSideDeck, Direction.RIGHT);
			break;
		}
		startGame();
	}

	public static void initializeGamePvB() {
		switch (difficultyRight) {
		case "Easy":
			leftSideController = new Player(30, 1, leftSideDeck, Direction.LEFT);
			rightSideController = new BotEasy(20, 1, rightSideDeck, Direction.RIGHT);
			break;
		case "Normal":
			leftSideController = new Player(20, 1, leftSideDeck, Direction.LEFT);
			rightSideController = new BotNormal(20, 1, rightSideDeck, Direction.RIGHT);
			break;
		case "Hard":
			leftSideController = new Player(20, 1, leftSideDeck, Direction.LEFT);
			rightSideController = new BotHard(30, 1, rightSideDeck, Direction.RIGHT);
			break;
		}
		startGame();
	}

	public static void initializeGamePvP() {
		leftSideController = new Player(20, 1, leftSideDeck, Direction.LEFT);
		rightSideController = new Player(20, 1, rightSideDeck, Direction.RIGHT);
		startGame();
	}

	public static void playGame() {
		switch (gameMode) {
		case "PvB":
			initializeGamePvB();
			break;
		case "PvP":
			initializeGamePvP();
			break;
		case "BvB":
			initializeGameBvB();
			break;
		}
	}

	public static void startAttackCard() {
		Thread thread = new Thread(() -> {
			try {
				threadAllCardMove.join(); // wait all card move finish
				Thread.sleep(1000);
				Platform.runLater(new Runnable() {
					public void run() {
						board.allCardAttack();
						board.update();
						board.removeDeadCards();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		threadAttackCard = thread;
	}

	public static void startFirstTurn() {
		isPhaseOneEnd = false;
		turn++;
		// first turn each side have 4 cards
		leftSideController.drawCard(4);
		rightSideController.drawCard(4);
		// if it is bot turn, bot will play
		if ((currentPlayingSide == Direction.LEFT) && (leftSideController instanceof Bot)) {
			((Bot) leftSideController).play();
		} else if ((currentPlayingSide == Direction.RIGHT) && (rightSideController instanceof Bot)) {
			((Bot) rightSideController).play();
		}
	}

	public static void startGame() {
		gameScreen = new GameScreen();
		winner = null;
		turn = 0;
		// Random side play first
		Random rand = new Random();
		if (rand.nextInt(2) == 1) {
			currentPlayingSide = Direction.LEFT;
			gameScreen.highlightHandPane(Direction.LEFT);
		} else {
			currentPlayingSide = Direction.RIGHT;
			gameScreen.highlightHandPane(Direction.RIGHT);
		}
		startFirstTurn();
	}

	public static void startMoveCard() {
		switch (currentPlayingSide) { // controller play first each turn, move card after, attack after
		case LEFT:
			board.moveAllCard(Direction.RIGHT);
			break;
		case RIGHT:
			board.moveAllCard(Direction.LEFT);
			break;
		}
	}

	public static void startNextPhase() {
		Thread thread = new Thread(() -> {
			try {
				if (threadBotPlay != null) {
					threadBotPlay.join(); // wait for bot finish play
//					Thread.sleep(1000);
				}
				Platform.runLater(new Runnable() {
					public void run() {
						if (isPhaseOneEnd == true) { // two controller have played
							startMoveCard();
							startAttackCard();
							startTurn();
						} else { // one controller have played
							switchPlayingSide();
						}
						// if it is bot turn, bot will play
						if ((currentPlayingSide == Direction.LEFT) && (leftSideController instanceof Bot)) {
							((Bot) leftSideController).play();
						} else if ((currentPlayingSide == Direction.RIGHT) && (rightSideController instanceof Bot)) {
							((Bot) rightSideController).play();
						}
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

	public static boolean isBotSide(Direction direction) {
		switch(direction) {
		case LEFT:
			return (leftSideController instanceof Bot);
		case RIGHT:
			return (rightSideController instanceof Bot);
		}
		return false;
	}
	
	public static void startTurn() { // called when click next turn button
		isPhaseOneEnd = false;
		turn++;
		// each side draw 2 card, money += turn
		leftSideController.setMoney(leftSideController.getMoney() + turn);
		rightSideController.setMoney(rightSideController.getMoney() + turn);
		leftSideController.drawCard(2);
		rightSideController.drawCard(2);
	}

	public static void switchPlayingSide() {
		gameScreen.getLeftCardsInHand().unHightlightAllCardInHandPane();
		gameScreen.getRightCardsInHand().unHightlightAllCardInHandPane();
		gameScreen.unHighlightHandPane();
		if (currentPlayingSide == Direction.LEFT) {
			currentPlayingSide = Direction.RIGHT;
			gameScreen.highlightHandPane(Direction.RIGHT);
		} else {
			currentPlayingSide = Direction.LEFT;
			gameScreen.highlightHandPane(Direction.LEFT);
		}
		isPhaseOneEnd = true;
	}
}
