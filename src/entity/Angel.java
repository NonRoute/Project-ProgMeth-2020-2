package entity;

import java.util.ArrayList;
import java.util.Arrays;

import application.CSVParser;
import card.Card;
import card.base.FighterCard;
import card.base.TrickCard;
import entity.base.Entity;
import entity.base.Character;
import javafx.scene.canvas.GraphicsContext;

public class Angel extends Character {
	public static String filename = "AngelDeck.csv";
	public static ArrayList<Card> deck = importDeck(filename);

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

}
