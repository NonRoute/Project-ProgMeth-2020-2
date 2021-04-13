package gui;

import card.Card;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public abstract class CardPane extends GridPane {
	protected Card card;

	public Card getCard() {
		return card;
	}
	
	public abstract void addCardImage(Image image);
	public abstract void setUpCardAbility(Card card);

}
