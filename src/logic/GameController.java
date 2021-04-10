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

public class GameController {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HIGHT = 720;
	public static Stage primaryStage;
	public static ArrayList<Deck> Decks = new ArrayList<>();
	private static Deck angelDeck;
	private static Deck devilDeck;
	public static Board board;
	public static int turn;
	public static Controller leftSideController;
	public static Controller rightSideController;
	public static Boolean isGameEnd;
	public static Card selectCard;
	public static String gameMode;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
	}

	public static void selectGameMode(Deck leftSideDeck, Deck rightSideDeck, String difficulty, String difficulty2,
			String gameMode) {
		switch (gameMode) {
		case "PvB":
			initializeGamePvB(leftSideDeck, rightSideDeck, difficulty);
			break;
		case "PvP":
			initializeGamePvB(leftSideDeck, rightSideDeck, difficulty);
			break;
		case "BvB":
			initializeGameBvB(leftSideDeck, rightSideDeck, difficulty, difficulty2);
			break;
		}
	}

	public static void initializeGamePvB(Deck leftSideDeck, Deck rightSideDeck, String difficulty) {
		switch (difficulty) {
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

	public static void initializeGamePvP(Deck leftSideDeck, Deck rightSideDeck) {
		leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
		rightSideController = new Player(20, 1, rightSideDeck, 4, Direction.RIGHT);
		startGame();
	}

	public static void initializeGameBvB(Deck leftSideDeck, Deck rightSideDeck, String leftDifficulty,
			String rightDifficulty) {
		switch (leftDifficulty) {
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
		switch (rightDifficulty) {
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
		turn = 0;
		// while game not end

		while (!isGameEnd) {
			startTurn();
		}
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
