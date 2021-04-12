package gui;

import card.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;

public class CardsInHandPane extends VBox {
	private ObservableList<GridPane> cardsList = FXCollections.observableArrayList();

	public CardsInHandPane() {
		this.setPrefSize(140, GameController.SCREEN_HIGHT - 40);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.SIENNA, new CornerRadii(5), Insets.EMPTY)));
	}

	public void addCard(String deckName, Card card) {
		CardPane cardPane = new CardPane(deckName, card);
		this.getChildren().add(cardPane);
		cardsList.add(cardPane);
	}

	public void removeCard(int index) {
		System.out.println("Remove");
		cardsList.remove(index);
		this.getChildren().remove(index);
	}

	public int getIndex(CardPane cardPane) {
		return cardsList.indexOf(cardPane);
	}

	public void setSelectedCard(GridPane selectedCardPane, Card selectedCard) {
		GameController.selectCard = selectedCard;
		GameController.selectedCardPane = (CardPane) selectedCardPane;
		resetButtonsBackGroundColor();
		((CardPane) selectedCardPane).highlight();
		GameController.board.highlightCellCanPlay();
	}

	public void resetButtonsBackGroundColor() {
		for (GridPane e : cardsList) {
			((CardPane) e).unhighlight();
		}
	}
}
