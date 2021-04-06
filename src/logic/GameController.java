package logic;

import java.util.ArrayList;

import application.CSVParser;
import card.Card;
import card.FighterCard;
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
	private static Board gameMap;
	public static int turn;
	private static Controller leftSideController;
	private static Controller rightSideController;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
	}

	public static void startGame(Card playerCard, Card botCard, String difficulty, String gameMode) {
		switch (gameMode) {
		case "PvB":
			startGamePvB(playerCard, botCard, difficulty);
		case "PvP":
			// WIP
		case "BvB":
			// WIP
		}
	}

	public static void startGamePvB(Card playerCard, Card botCard, String difficulty) {
		switch (difficulty) {
		case "Easy":
			leftSideController = new Player(30, 1, 4, Direction.LEFT);
			rightSideController = new BotEasy(20, 1, 4, Direction.RIGHT);
		case "Normal":
			leftSideController = new Player(20, 1, 4, Direction.LEFT);
			rightSideController = new BotNormal(20, 1, 4, Direction.RIGHT);
		case "Hard":
			leftSideController = new Player(20, 1, 4, Direction.LEFT);
			rightSideController = new BotHard(30, 1, 4, Direction.RIGHT);
		}
		gameMap = new Board();
		turn = 0;
		// while game not end
		startTurn();

	}

	public static void startTurn() {
		turn++;
		// TODO
	}

	public static Deck getAngelDeck() {
		return angelDeck;
	}

	public static Deck getDevilDeck() {
		return devilDeck;
	}

}
