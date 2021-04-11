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

public class CardsInHand extends VBox {
	private ObservableList<GridPane> cardsList = FXCollections.observableArrayList();

	public CardsInHand() {
		this.setPrefSize(150, GameController.SCREEN_HIGHT-40);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, new CornerRadii(5), Insets.EMPTY)));
	}

	public void addCard(String deckName, String cardtype, Direction playingSide, Card card) {
		CardGUI cardGUI = new CardGUI(deckName, cardtype, playingSide, card);
		this.getChildren().add(cardGUI);
		cardsList.add(cardGUI);
	}
	
	public void removeCard(int index) {
		this.getChildren().remove(index);
	}
	
	public void setSelectedCard(GridPane selectedCardGUI, Card selectedCard) {
		GameController.selectCard = selectedCard;
		resetButtonsBackGroundColor();
		((CardGUI) selectedCardGUI).highlight();
	}

	public void resetButtonsBackGroundColor() {
		//unhighlight each button in itemButtonList
		for (GridPane e : cardsList) {
			((CardGUI) e).unhighlight();
		}
	}
}
