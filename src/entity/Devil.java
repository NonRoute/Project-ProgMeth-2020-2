package entity;

import java.util.ArrayList;

import card.Card;
import entity.base.Entity;
import entity.base.Character;
import javafx.scene.canvas.GraphicsContext;

public class Devil extends Character {
	public static String filename = "DevilDeck.csv";
	public static ArrayList<Card> deck = importDeck(filename);

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
