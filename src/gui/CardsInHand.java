package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;

public class CardsInHand extends VBox {

	public CardsInHand() {
		this.setPrefSize(150, GameController.SCREEN_HIGHT-40);
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10);
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, new CornerRadii(5), Insets.EMPTY)));
	}

	public void addCard(String deckName, String cardtype, Direction playingSide) {
		this.getChildren().add(new CardGUI(deckName, cardtype, playingSide));
	}
	
	public void removeCard(int index) {
		this.getChildren().remove(index);
	}
}
