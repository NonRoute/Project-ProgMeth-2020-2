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
import trick.DestroyCard;
import trick.Trick;

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
			try {
				switch (deckData[i][0]) {
				case "Fighter":
					FighterCard fighterCard = new FighterCard(name, deckData[i][1],
							Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
							Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
							Integer.parseInt(deckData[i][6]));
					deck.add(fighterCard);
					break;
				case "Magician":
					MagicianCard magicianCard;
					switch (deckData[i][7]) {
					case ("ChangeCardAbility"):
						magicianCard = new MagicianCard(name, deckData[i][1],
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								Integer.parseInt(deckData[i][6]), new ChangeCardAbility(deckData[i][8]));
						deck.add(magicianCard);
						break;
					case ("DestroyCard"):
						magicianCard = new MagicianCard(name, deckData[i][1],
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								Integer.parseInt(deckData[i][6]), new DestroyCard(deckData[i][8]));
						deck.add(magicianCard);
						break;
					default:
						System.out.println("Wrong magician input");
					}
					break;
				case "Trick":
					TrickCard trickCard;
					switch (deckData[i][7]) {
					case ("ChangeCardAbility"):
						trickCard = new TrickCard(name, deckData[i][1],
								Integer.parseInt(deckData[i][2]), new ChangeCardAbility(deckData[i][8]));
						break;
					case ("DestroyCard"):
						trickCard = new TrickCard(name, deckData[i][1],
								Integer.parseInt(deckData[i][2]), new DestroyCard(deckData[i][8]));
						deck.add(trickCard);
						break;
					default:
						System.out.println("Wrong trick input");
						break;
					}
					break;
				default:
					System.out.println("Wrong card type input, You input: " + deckData[i][0]);
				}
			} catch (Exception e) {
				System.out.println("Worng card input");
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
