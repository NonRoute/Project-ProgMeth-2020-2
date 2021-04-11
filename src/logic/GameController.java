package logic;

import java.util.ArrayList;

import card.Card;
import deck.Deck;
import entity.BotEasy;
import entity.BotHard;
import entity.BotNormal;
import entity.Controller;
import entity.Player;
import javafx.stage.Stage;
import screen.GameScreen;

public class GameController {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HIGHT = 720;
	public static Stage primaryStage;
	public static GameScreen gamescreen;

	public static ArrayList<Deck> Decks = new ArrayList<>();
	private static Deck angelDeck;
	private static Deck devilDeck;
	private static Deck testDeck; //TODO Remove this when geme finish

	public static Board board;
	public static int turn = 1;

	public static Controller leftSideController;
	public static Controller rightSideController;

	public static Boolean isGameEnd;
	public static Card selectCard;

	public static String gameMode;
	public static Deck leftSideDeck;
	public static Deck rightSideDeck;
	public static String difficultyLeft;
	public static String difficultyRight;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
		testDeck = new Deck("Test","TestDeck.csv"); //TODO Remove this when geme finish
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
		gamescreen = new GameScreen();
	}

	public static void initializeGamePvB() {
		switch (difficultyRight) {
		case "Easy":
			leftSideController = new Player(30, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotEasy(20, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		case "Normal":
			leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotNormal(20, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		case "Hard":
			leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotHard(30, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		}
		startGame();
	}

	public static void initializeGamePvP() {
		leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
		rightSideController = new Player(20, 1, rightSideDeck, 4, Direction.RIGHT);
		startGame();
	}

	public static void initializeGameBvB() {
		switch (difficultyLeft) {
		case "Easy":
			leftSideController = new BotEasy(20, 1, rightSideDeck, 4, Direction.LEFT);
			break;
		case "Normal":
			leftSideController = new BotNormal(20, 1, rightSideDeck, 4, Direction.LEFT);
			break;
		case "Hard":
			leftSideController = new BotHard(30, 1, rightSideDeck, 4, Direction.LEFT);
			break;
		}
		switch (difficultyRight) {
		case "Easy":
			rightSideController = new BotEasy(20, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		case "Normal":
			rightSideController = new BotNormal(20, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		case "Hard":
			rightSideController = new BotHard(30, 1, rightSideDeck, 4, Direction.RIGHT);
			break;
		}
		startGame();
	}

	public static void startGame() {
		board = new Board();
		isGameEnd = false;
		// while game not end
//		while (!isGameEnd) {
//			startTurn();
//		}
		// TODO play end screen
	}

	public static void startTurn() {
		turn++;
		// TODO
		// Random side play first
		// side play first; select card , place card
		// side play after; select card , place card
		// gamemap moveall, side play first move first
		// change side play first
		// draw card += turn
	}

	public static Deck getAngelDeck() {
		return angelDeck;
	}

	public static Deck getDevilDeck() {
		return devilDeck;
	}

}
