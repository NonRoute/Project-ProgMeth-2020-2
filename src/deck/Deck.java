package deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import application.CSVParser;
import card.Card;
import card.FighterCard;
import card.MagicianCard;
import card.TrickCard;
import logic.GameController;
import trick.ChangeCardAbility;
import trick.NoEffect;

public class Deck {
	private String Name;
	private ArrayList<Card> cards;
	private ArrayList<Integer> numberOfCardsEachCost; // index = cost

	public Deck(String Name, String fileName) {
		this.Name = Name;
		this.cards = importDeck(fileName);
		this.numberOfCardsEachCost = countNumberOfCardsEachCost();
		GameController.Decks.add(this);
	}

	public static ArrayList<Card> importDeck(String filename) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[][] deckData = CSVParser.readCSV(filename);
		for (int i = 2; i < deckData.length; i++) { // each row = each cards
			System.out.println(Arrays.toString(deckData[i]));
			switch (deckData[i][0]) {
			case "Fighter":
				FighterCard fighterCard = new FighterCard();
				fighterCard.setName(deckData[i][1]);
				fighterCard.setDescription(deckData[i][2]);
				fighterCard.setAttackDamage(Integer.parseInt(deckData[i][3]));
				fighterCard.setHeart(Integer.parseInt(deckData[i][4]));
				fighterCard.setCost(Integer.parseInt(deckData[i][5]));
				fighterCard.setSpeed(Integer.parseInt(deckData[i][6]));
				fighterCard.setAttackRange(Integer.parseInt(deckData[i][7]));
				deck.add(fighterCard);
				break;
			case "Magician":
				MagicianCard magicianCard = new MagicianCard();
				magicianCard.setName(deckData[i][1]);
				magicianCard.setDescription(deckData[i][2]);
				magicianCard.setAttackDamage(Integer.parseInt(deckData[i][3]));
				magicianCard.setHeart(Integer.parseInt(deckData[i][4]));
				magicianCard.setCost(Integer.parseInt(deckData[i][5]));
				magicianCard.setSpeed(Integer.parseInt(deckData[i][6]));
				magicianCard.setAttackRange(Integer.parseInt(deckData[i][7]));
				switch (deckData[i][8]) {
				case ("ChangeCardAbility"):
					magicianCard.setTrick(new ChangeCardAbility(deckData[i][9])); //TODO
				}
				deck.add(magicianCard);
				break;
			case "Trick":
				TrickCard trickCard = new TrickCard();
				trickCard.setName(deckData[i][1]);
				trickCard.setDescription(deckData[i][2]);
				trickCard.setCost(Integer.parseInt(deckData[i][5]));
				switch (deckData[i][8]) {
				case ("ChangeCardAbility"):
					trickCard.setTrick(new ChangeCardAbility(deckData[i][9])); //TODO
				}
				deck.add(trickCard);
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

	public ArrayList<Integer> getNumberOfCardsEachCost() {
		return numberOfCardsEachCost;
	}
}
