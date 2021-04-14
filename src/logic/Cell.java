package logic;

import card.Card;
import card.FighterCard;
import gui.CardInHandPane;
import gui.CardOnBoardPane;
import gui.CardPane;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Cell extends StackPane {
	private CardOnBoardPane cardOnBoardPane;
//	private Card cardOnCell;
	private int row;
	private int column;
	private boolean isEmpty = true;
	private boolean isHighLight;
	private int cardWidth = 88;
	private int cardHight = 116;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		this.setPrefWidth(cardWidth);
		this.setPrefHeight(cardHight);
		this.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (isHighLight) {
					switch (GameController.selectedCardPane.getCard().getPlayingSide()) {
					case LEFT:
						int index = GameController.leftSideController.getCardsInHandPane()
								.indexOf(GameController.selectedCardPane);
						GameController.leftSideController.useCard(index);
						break;
					case RIGHT:
						int index2 = GameController.rightSideController.getCardsInHandPane()
								.indexOf(GameController.selectedCardPane);
						GameController.rightSideController.useCard(index2);
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
			if (cardPane instanceof CardInHandPane) { //change gui to OnBoard
				cardPane = new CardOnBoardPane(((CardInHandPane) cardPane).getCard());
			}
			this.cardOnBoardPane = (CardOnBoardPane) cardPane;
			this.cardOnBoardPane.getCard().setRow(row); //update row to card
			this.cardOnBoardPane.getCard().setColumn(column);
			GameController.board.unHighlightAllCells();
			this.getChildren().add(cardPane);
			isEmpty = false;
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

	public CardOnBoardPane getCardOnBoardPane() {
		return cardOnBoardPane;
	}

	public FighterCard getCard() {
		return cardOnBoardPane.getCard();
	}

}
