package logic;

import card.Card;
import card.Movable;
import gui.CardInHandPane;
import gui.CardOnBoardPane;
import gui.CardPane;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class Cell extends StackPane {
	private CardOnBoardPane cardPane;
//	private Card cardOnCell;
	private boolean isEmpty = true;
	private boolean isHighLight;
	private int cardWidth = 88;
	private int cardHight = 116;

	public Cell() {
		this.setPrefWidth(cardWidth);
		this.setPrefHeight(cardHight);
		this.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (isHighLight) {
					System.out.println("PLACE CARD");
					switch (GameController.selectedCardPane.getCard().getPlayingSide()) {
					case LEFT:
						int index = GameController.leftSideController.getCardsInHandPane()
								.indexOf(GameController.selectedCardPane);
						GameController.leftSideController.useCard(index);
						System.out.println("index" + index);
						break;
					case RIGHT:
						int index2 = GameController.rightSideController.getCardsInHandPane()
								.indexOf(GameController.selectedCardPane);
						GameController.rightSideController.useCard(index2);
						System.out.println("index" + index2);
						break;
					}
					setCard(GameController.selectedCardPane);
				}
			}
		});
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = true;
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = false;
	}

	public void setCard(CardPane cardPane) {
		if (isEmpty) {
			if (cardPane instanceof CardInHandPane) {
				cardPane = new CardOnBoardPane(cardPane.getCard());
			}
			this.cardPane = (CardOnBoardPane) cardPane;
			GameController.board.unHighlightAllCells();
			this.getChildren().add(cardPane);
			isEmpty = false;
			// TODO show gui
		}
	}

	public void removeCard() {
		this.getChildren().clear();
		isEmpty = true;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Card getCard() {
		return cardPane.getCard();
	}

}
