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
import trick.ChangeControllerHeart;
import trick.DestroyCard;
import trick.Draw;

public class Deck {
	private String name;
	private ArrayList<Card> cards;
	private ArrayList<Integer> numberOfCardsEachCost; // index = cost

	public Deck(String Name, String fileName) {
		this.name = Name;
		this.cards = importDeck(fileName);
		this.numberOfCardsEachCost = countNumberOfCardsEachCost();
		GameController.DECKS.add(this);
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

	public ArrayList<Card> getCards() {
		return cards;
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

	public ArrayList<Integer> getNumberOfCardsEachCost() {
		return numberOfCardsEachCost;
	}

	public ArrayList<Card> importDeck(String filename) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[][] deckData = CSVParser.readCSV(filename);
		for (int i = 2; i < deckData.length; i++) { // each row = each cards
			try {
				switch (deckData[i][0]) {
				case "Fighter":
					FighterCard fighterCard = new FighterCard(name, Integer.parseInt(deckData[i][1]),
							Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
							Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]));
					deck.add(fighterCard);
					break;
				case "Magician":
					MagicianCard magicianCard;
					switch (deckData[i][6]) {
					case ("ChangeCardAbility"):
						magicianCard = new MagicianCard(name, Integer.parseInt(deckData[i][1]),
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								new ChangeCardAbility(deckData[i][7]));
						deck.add(magicianCard);
						break;
					case ("DestroyCard"):
						magicianCard = new MagicianCard(name, Integer.parseInt(deckData[i][1]),
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								new DestroyCard(deckData[i][7]));
						deck.add(magicianCard);
						break;
					case ("Draw"):
						magicianCard = new MagicianCard(name, Integer.parseInt(deckData[i][1]),
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								new Draw(deckData[i][7]));
						deck.add(magicianCard);
						break;
					case ("ChangeControllerHeart"):
						magicianCard = new MagicianCard(name, Integer.parseInt(deckData[i][1]),
								Integer.parseInt(deckData[i][2]), Integer.parseInt(deckData[i][3]),
								Integer.parseInt(deckData[i][4]), Integer.parseInt(deckData[i][5]),
								new ChangeControllerHeart(deckData[i][7]));
						deck.add(magicianCard);
						break;
					default:
						System.out.println("Wrong magician input");
					}
					break;
				case "Trick":
					TrickCard trickCard;
					switch (deckData[i][6]) {
					case ("ChangeCardAbility"):
						trickCard = new TrickCard(name, Integer.parseInt(deckData[i][1]),
								new ChangeCardAbility(deckData[i][7]));
						deck.add(trickCard);
						break;
					case ("DestroyCard"):
						trickCard = new TrickCard(name, Integer.parseInt(deckData[i][1]),
								new DestroyCard(deckData[i][7]));
						deck.add(trickCard);
						break;
					case ("Draw"):
						trickCard = new TrickCard(name, Integer.parseInt(deckData[i][1]), new Draw(deckData[i][7]));
						deck.add(trickCard);
						break;
					case ("ChangeControllerHeart"):
						trickCard = new TrickCard(name, Integer.parseInt(deckData[i][1]),
								new ChangeControllerHeart(deckData[i][7]));
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
				e.printStackTrace();
				System.out.println("Wrong card input " + name + " " + deckData[i][0]);
			}
		}
		return deck;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public void setName(String name) {
		this.name = name;
	}
}
