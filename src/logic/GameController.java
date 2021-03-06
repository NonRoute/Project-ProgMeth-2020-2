package logic;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.FighterCard;
import deck.Deck;
import entity.Bot;
import entity.BotEasy;
import entity.BotHard;
import entity.BotNormal;
import entity.Controller;
import entity.Player;
import gui.Board;
import gui.CardInHandPane;
import javafx.application.Platform;
import javafx.stage.Stage;
import screen.GameScreen;

public class GameController {
	public static Thread threadDrawCard;
	public static Thread threadBotPlay;
	public static Thread threadCardMove;
	public static Thread threadMoveAllCard;
	public static Thread threadStartAttackCard;
	public static Thread threadAttackAllCard;
	public static Thread threadAttack;

	public static final int DELAY_DRAW_CARD = 500; // 500
	public static final int DELAY_BOT_PLAY = 1200; // 1200 (MUST >= 20)
	public static final int DELAY_CARD_MOVE = 200; // 200 (MUST >= 20)
	public static final int DELAY_ATTACK = 700; //700

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static Stage primaryStage;
	public static GameScreen gameScreen;

	public static final ArrayList<Deck> DECKS = new ArrayList<>();

	public static Board board;
	public static int turn;
	public static int moneyFromTurn;
	public static boolean isPhaseOneEnd;
	public static Direction currentPlayingSide;

	public static Controller leftSideController;
	public static Controller rightSideController;

	public static Direction winner;
	public static boolean isGameEnd;
	public static CardInHandPane selectedCardPane;
	public static Card lastUsedCard;
	public static FighterCard targetCard;

	public static String gameMode;
	public static Deck leftSideDeck;
	public static Deck rightSideDeck;
	public static String difficultyLeft;
	public static String difficultyRight;

	static {
		// import deck .csv
		new Deck("Angel", "AngelDeck.csv");
		new Deck("Devil", "DevilDeck.csv");
	}

	public static void initializeGameBvB() {
		switch (difficultyLeft) {
		case "Easy":
			leftSideController = new BotEasy(20, 1, leftSideDeck, Direction.LEFT);
			break;
		case "Normal":
			leftSideController = new BotNormal(20, 1, leftSideDeck, Direction.LEFT);
			break;
		case "Hard":
			leftSideController = new BotHard(30, 1, leftSideDeck, Direction.LEFT);
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

	public static boolean isBotSide(Direction direction) {
		switch (direction) {
		case LEFT:
			return (leftSideController instanceof Bot);
		case RIGHT:
			return (rightSideController instanceof Bot);
		}
		return false;
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
				threadMoveAllCard.join(); // wait all card move finish
				board.attackAllCard();
				threadAttackAllCard.join(); // wait atackAllCard finish
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						board.update();
						board.removeDeadCards();
					}
				});
				Thread.sleep(DELAY_ATTACK);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		threadStartAttackCard = thread;
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
		isGameEnd = false;
		winner = null;
		turn = 0;
		moneyFromTurn = 1;
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
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (isPhaseOneEnd) { // two controller have played
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

	public static void startTurn() {
		isPhaseOneEnd = false;
		Thread thread = new Thread(() -> {
			try {
				if (threadStartAttackCard != null && threadStartAttackCard.isAlive()) { // wait attack finish first
					threadStartAttackCard.join();
				}
				if (isGameEnd) { // stop running if game end
					return;
				}
				turn++;
				if (turn <= 20) {
					moneyFromTurn++;
				}
				// each side draw 2 card, money += turn
				leftSideController.setMoney(leftSideController.getMoney() + moneyFromTurn);
				rightSideController.setMoney(rightSideController.getMoney() + moneyFromTurn);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		if (isGameEnd) { // stop running if game end
			return;
		}
		leftSideController.drawCard(2);
		rightSideController.drawCard(2);
	}

	public static void switchPlayingSide() {
		gameScreen.getLeftCardsInHand().unHighlightAllCardInHandPane();
		gameScreen.getRightCardsInHand().unHighlightAllCardInHandPane();
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
