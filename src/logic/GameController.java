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
	public static int initialMoney;
	public static int initialNumberOfCardInHand;
	private static int turn;
	private static Controller leftSideController;
	private static Controller rightSideController;

	static {
		angelDeck = new Deck("Angel", "AngelDeck.csv");
		devilDeck = new Deck("Devil", "DevilDeck.csv");
	}

	public static void startGame(Card playerCard, Card botCard, String botMode, String gameMode) {
		switch (gameMode) {
		case "PvB":
			startGamePvB(playerCard, botCard, botMode);
		case "PvP":
			// WIP
		case "BvB":
			// WIP
		}
	}

	public static void startGamePvB(Card playerCard, Card botCard, String botMode) {
		leftSideController = new Player();
		switch (botMode) {
		case "Easy":
			rightSideController = new BotEasy();
		case "Normal":
			rightSideController = new BotNormal();
		case "Hard":
			rightSideController = new BotHard();
		}
		gameMap = new Board();
		turn = 0;
		// cardInPlayerHand = random 4 card
		// cardInBotHand = random 4 card
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
