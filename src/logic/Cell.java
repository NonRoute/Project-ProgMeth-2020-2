package logic;

import card.Card;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Cell extends GridPane {
	private Card cardOnCell;
	private boolean isEmpty = true;
	private boolean isHighLight;
	private int cardWidth = 88;
	private int cardHight = 116;
	private int insets = 2;

	public Cell() {
		this.setPrefWidth(cardWidth);
		this.setPrefHeight(cardHight);
		this.setPadding(new Insets(insets));
		this.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (isHighLight) {
					setCard(GameController.selectCard);
				}

			}

		});
	}

	public void setUpCell() {

	}

	public Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = true;
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		isHighLight = false;
	}

	public void setCard(Card card) {
		if (isEmpty) {
			cardOnCell = card;
			isEmpty = false;
			// TODO show gui
		}
	}

	public void removeCard() {
		cardOnCell = null;
		isEmpty = true;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Card getCard() {
		return cardOnCell;
	}

}
