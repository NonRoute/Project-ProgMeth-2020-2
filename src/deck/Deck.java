package deck;

import java.util.ArrayList;

import application.CSVParser;
import card.Card;
import card.FighterCard;
import card.TrickCard;

public class Deck {
	private String Name;
	private ArrayList<Card> cards;

	public Deck(String Name, String fileName) {
		this.Name = Name;
		this.cards = importDeck(fileName);
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
}
