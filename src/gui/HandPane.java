package gui;

import card.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
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
import logic.GameController;

public class HandPane extends VBox {
	private ObservableList<CardInHandPane> cardsList = FXCollections.observableArrayList();

	public HandPane() {
		this.setMinSize(140, GameController.SCREEN_HEIGHT - 40);
		this.setPrefSize(140, GameController.SCREEN_HEIGHT - 40);
		this.setMaxSize(140, GameController.SCREEN_HEIGHT - 40);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.SIENNA, new CornerRadii(5), Insets.EMPTY)));
	}
	
	public void add(String deckName, Card card) {
		CardInHandPane cardPane = new CardInHandPane(card);
		this.getChildren().add(cardPane);
		cardsList.add(cardPane);
	}
	
	public Card getCard(int index) {
		return cardsList.get(index).getCard();
	}

	public ObservableList<CardInHandPane> getCardsList() {
		return cardsList;
	}

	public int getSize() {
		return cardsList.size();
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, new CornerRadii(5), Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		this.setEffect(new DropShadow());
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

	public void remove(int index) {
		cardsList.remove(index);
		this.getChildren().remove(index);
	}

	public void setSelectedCard(CardInHandPane selectedCardPane) {
		GameController.board.unHighlightAllCells();
		GameController.selectedCardPane = selectedCardPane;
		unHighlightAllCardInHandPane();
		(selectedCardPane).highlight();
		GameController.board.highlightCellCanPlay(selectedCardPane);
	}
	
	public void unHighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.SIENNA, new CornerRadii(5), Insets.EMPTY)));
		this.setBorder(null);
		this.setEffect(null);
	}

	public void unHighlightAllCardInHandPane() {
		for (GridPane e : cardsList) {
			((CardInHandPane) e).unhighlight();
		}
	}
}
