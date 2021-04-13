package gui;

import java.util.ArrayList;

import card.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;

public class HandPane extends VBox {
	private ObservableList<CardInHandPane> cardsList = FXCollections.observableArrayList();

	public HandPane() {
		this.setPrefSize(140, GameController.SCREEN_HIGHT - 40);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.SIENNA, new CornerRadii(5), Insets.EMPTY)));
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, new CornerRadii(5), Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		this.setEffect(new DropShadow());
	}
	
	public void unHighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.SIENNA, new CornerRadii(5), Insets.EMPTY)));
		this.setBorder(null);
		this.setEffect(null);
	}

	public int getSize() {
		return cardsList.size();
	}

	public Card get(int index) {
		return cardsList.get(index).getCard();
	}

	public ArrayList<Card> getCardsList() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (CardInHandPane e : cardsList) {
			cards.add(e.getCard());
		}
		return cards;
	}

	public void add(String deckName, Card card) {
		CardInHandPane cardPane = new CardInHandPane(card);
		this.getChildren().add(cardPane);
		cardsList.add(cardPane);
	}

	public void remove(int index) {
		cardsList.remove(index);
		this.getChildren().remove(index);
	}

	public int indexOf(Card card) {
		for (int i = 0; i < cardsList.size(); i++) {
			if (cardsList.get(i).getCard().equals(card)) {
				return i;
			}
		}
		return -1;
	}

	public int indexOf(CardInHandPane cardPane) {
		return cardsList.indexOf(cardPane);
	}

	public void setSelectedCard(GridPane selectedCardPane, Card selectedCard) {
		GameController.selectedCardPane = (CardInHandPane) selectedCardPane;
		resetButtonsBackGroundColor();
		((CardInHandPane) selectedCardPane).highlight();
		GameController.board.highlightCellCanPlay();
	}

	public void resetButtonsBackGroundColor() {
		for (GridPane e : cardsList) {
			((CardInHandPane) e).unhighlight();
		}
	}
}