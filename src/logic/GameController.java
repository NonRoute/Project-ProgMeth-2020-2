package logic;

import java.util.ArrayList;

import application.CSVParser;
import card.Card;
import card.MagicianCard;
import card.TrickCard;
import deck.Deck;
import entity.BotEasy;
import entity.BotHard;
import entity.BotNormal;
import entity.Controller;
import entity.Player;

public class GameController {
	private static Deck angelDeck;
	private static Deck devilDeck;
	public static Board board;
	public static int turn;
	public static Controller leftSideController;
	public static Controller rightSideController;
	public static Boolean isGameEnd;
	public static Card selectCard;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
	}

	public static void selectGameMode(Deck leftSideDeck, Deck rightSideDeck, String difficulty, String gameMode) {
		switch (gameMode) {
		case "PvB":
			initializeGamePvB(leftSideDeck, rightSideDeck, difficulty);
		case "PvP":
			// WIP
		case "BvB":
			// WIP
		}
	}

	public static void initializeGamePvB(Deck leftSideDeck, Deck rightSideDeck, String difficulty) {
		switch (difficulty) {
		case "Easy":
			leftSideController = new Player(30, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotEasy(20, 1, rightSideDeck, 4, Direction.RIGHT);
		case "Normal":
			leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotNormal(20, 1, rightSideDeck, 4, Direction.RIGHT);
		case "Hard":
			leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
			rightSideController = new BotHard(30, 1, rightSideDeck, 4, Direction.RIGHT);
		}
		startGame();
	}

	public static void initializeGamePvP(Deck leftSideDeck, Deck rightSideDeck) {
		leftSideController = new Player(20, 1, leftSideDeck, 4, Direction.LEFT);
		rightSideController = new Player(20, 1, rightSideDeck, 4, Direction.RIGHT);
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
