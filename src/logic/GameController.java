package logic;

import java.util.ArrayList;

import application.CSVParser;
import card.Card;
import card.base.FighterCard;
import card.base.TrickCard;

public class GameController {
	private static ArrayList<Card> angelDeck;
	private static ArrayList<Card> devilDeck;
	private static ArrayList<Card> cardInPlayerHand;
	private static ArrayList<Card> cardInBotHand;
	private static GameMap gameMap;
	private static int turn;
	private static int playerHeart;
	private static int botHeart;
	
	static {
		angelDeck = importDeck("AngelDeck.csv");
		devilDeck = importDeck("DevilDeck.csv");
	}

	public static ArrayList<Card> importDeck(String filename) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[][] deckData = CSVParser.readCSV(filename);

		for (int i = 2; i < deckData.length; i++) { // each row = each card
			
			if (deckData[i][0].equals("Fighter")) {
				FighterCard card = new FighterCard();
				card.setName(deckData[i][1]);
				card.setAttackDamage(Integer.parseInt(deckData[i][2]));
				card.setHeart(Integer.parseInt(deckData[i][3]));
				card.setCost(Integer.parseInt(deckData[i][4]));
				card.setSpeed(Integer.parseInt(deckData[i][5]));
				card.setAttackRange(Integer.parseInt(deckData[i][6]));
				card.setEffect(deckData[i][7]);
				deck.add(card);

			} else if (deckData[i][0].equals("Trick")) {
				TrickCard card = new TrickCard();
				card.setName(deckData[i][1]);
				card.setCost(Integer.parseInt(deckData[i][4]));
				card.setEffect(deckData[i][7]);
				deck.add(card);
			}
		}
		return deck;
	}
	
	public static void startGame(Card playerCard, Card botCard, String gameMode) {
		switch(gameMode) {
		case "Easy":
			playerHeart = 30;
			botHeart = 20;
		case "Normal":
			playerHeart = 20;
			botHeart = 20;
		case "Hard" :
			playerHeart = 20;
			botHeart = 30;
		}
		gameMap = new GameMap();
		turn = 0;
		//cardInPlayerHand = random 4 card
		//cardInBotHand = random 4 card
		//while game not end
		startTurn();
		
	}
	public static void startTurn() {
		turn++;
		//TODO
	}
	
	public static Card drawCard(ArrayList<Card> deck) {
		
	}
	

	public static ArrayList<Card> getAngelDeck() {
		return angelDeck;
	}

	public static ArrayList<Card> getDevilDeck() {
		return devilDeck;
	}

}
