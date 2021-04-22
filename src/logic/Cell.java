package logic;

import card.FighterCard;
import card.Trickable;
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
	private Color bgColor;
	private int row;
	private int column;
	private boolean isEmpty = true;
	private boolean isHighLight;
	private int cardWidth = 88;
	private int cardHight = 112;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		if ((row + column) % 2 == 0) {
			bgColor = Color.PAPAYAWHIP;
		} else {
			bgColor = Color.PEACHPUFF;
		}
		this.setPrefWidth(cardWidth);
		this.setPrefHeight(cardHight);
		this.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (isHighLight) {
					if (GameController.selectedCardPane.getCard() instanceof FighterCard) {
						// place card on board
						setCard(GameController.selectedCardPane);
					}
					if (GameController.selectedCardPane.getCard() instanceof Trickable) {
						// set target card
						switch (((Trickable) GameController.selectedCardPane.getCard()).getTrick()
								.getFirstParameter()) {
						case 'C': // not random
							GameController.targetCard = cardOnBoardPane.getCard();
							break;
						case 'D': // not random
							GameController.targetCard = cardOnBoardPane.getCard();
							break;
						}
					}
					// use card
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
				}
			}
		});
	}

	public FighterCard getCard() {
		return cardOnBoardPane.getCard();
	}

	public CardOnBoardPane getCardOnBoardPane() {
		return cardOnBoardPane;
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = true;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void removeCard() {
		this.getChildren().clear();
		isEmpty = true;
	}

	public void setCard(CardPane cardPane) {
		if (isEmpty) {
			if (cardPane instanceof CardInHandPane) { // change GUI from cardOnHand to cardOnBoard
				cardPane = new CardOnBoardPane(((CardInHandPane) cardPane).getCard());
			}
			this.cardOnBoardPane = (CardOnBoardPane) cardPane;
			this.cardOnBoardPane.getCard().setRow(row); // update row to card
			this.cardOnBoardPane.getCard().setColumn(column);
			GameController.board.unHighlightAllCells();
			this.getChildren().add(cardPane);
			isEmpty = false;
		}
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = false;
	}

}
