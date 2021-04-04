package entity;

import java.util.ArrayList;
import java.util.Arrays;

import application.CSVParser;
import card.Card;
import card.base.FighterCard;
import card.base.TrickCard;
import entity.base.Entity;
import entity.base.character;
import javafx.scene.canvas.GraphicsContext;

public class Angel extends character {
	public static ArrayList<Card> deck;
	
	public Angel() {
		deck = new ArrayList<Card>();
		String[][] deckData = CSVParser.readCSV("AngelDeck.csv");
		
		for (int i = 2; i < deckData.length; i++) { //each row
			
			if (deckData[i][0].equals("Fighter")) {
				FighterCard card = new FighterCard();
				//TODO: set value
				
			} else if (deckData[i][0].equals("Trick")) {
				TrickCard card = new TrickCard();
				//TODO: set value
			}
		}
	}
	
	public static void main(String args[]) {
		Angel e = new Angel();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
