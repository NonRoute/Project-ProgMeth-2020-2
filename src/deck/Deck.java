package deck;

import java.util.ArrayList;
import java.util.Collections;

import application.CSVParser;
import card.Card;
import card.FighterCard;
import card.MagicianCard;
import card.TrickCard;
import logic.GameController;
import trick.ChangeCardAbility;

public class Deck {
	private String name;
	private ArrayList<Card> cards;
	private ArrayList<Integer> numberOfCardsEachCost; // index = cost

	public Deck(String Name, String fileName) {
		this.name = Name;
		this.cards = importDeck(fileName);
		this.numberOfCardsEachCost = countNumberOfCardsEachCost();
		GameController.Decks.add(this);
	}

	public ArrayList<Card> importDeck(String filename) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[][] deckData = CSVParser.readCSV(filename);
		for (int i = 2; i < deckData.length; i++) { // each row = each cards
			switch (deckData[i][0]) {
			case "Fighter":
				System.out.println("Import a Fighter");
				FighterCard fighterCard = new FighterCard(name, deckData[i][1], deckData[i][2],
						Integer.parseInt(deckData[i][3]), Integer.parseInt(deckData[i][4]),
						Integer.parseInt(deckData[i][5]), Integer.parseInt(deckData[i][6]),
						Integer.parseInt(deckData[i][7]));
				deck.add(fighterCard);
				break;
			case "Magician":
				System.out.println("Import a Magician");
				switch (deckData[i][8]) {
				case ("ChangeCardAbility"):
					MagicianCard magicianCard = new MagicianCard(name, deckData[i][1], deckData[i][2],
							Integer.parseInt(deckData[i][3]), Integer.parseInt(deckData[i][4]),
							Integer.parseInt(deckData[i][5]), Integer.parseInt(deckData[i][6]),
							Integer.parseInt(deckData[i][7]), new ChangeCardAbility(deckData[i][9])); // TODO
					deck.add(magicianCard);
					break;
				}
				break;
			case "Trick":
				System.out.println("Import a Trick");
				switch (deckData[i][8]) {
				case ("ChangeCardAbility"):
					TrickCard trickCard = new TrickCard(name, deckData[i][1], deckData[i][2],
							Integer.parseInt(deckData[i][3]), new ChangeCardAbility(deckData[i][9])); // TODO
					deck.add(trickCard);
					break;
				}
				break;
			}
		}
		return deck;

	}

	public ArrayList<Integer> countNumberOfCardsEachCost() {
		ArrayList<Integer> temp = new ArrayList<>();
		for (Card card : this.cards) {
			temp.add(card.getCost());
		}
		// set the initial size = [0,1,2,3,...,max cost]
		ArrayList<Integer> numberOfCardsEachCost = new ArrayList<>();
		for (int i = 0; i <= Collections.max(temp); i++) {
			numberOfCardsEachCost.add(0);
		}
		for (int cost : temp) {
			numberOfCardsEachCost.set(cost, numberOfCardsEachCost.get(cost) + 1); // index = cost
		}
		return numberOfCardsEachCost;
	}

	public ArrayList<Card> getListOfCardsbyCost(int cost) {
		ArrayList<Card> listOfCardsbyCost = new ArrayList<>();
		for (Card card : this.cards) {
			if (card.getCost() == cost) {
				listOfCardsbyCost.add(card);
			}
		}
		return listOfCardsbyCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Integer> getNumberOfCardsEachCost() {
		return numberOfCardsEachCost;
	}
}
