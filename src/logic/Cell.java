package logic;

import card.Card;
import card.Movable;
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
					System.out.println("PLACE CARD");
					setCard(GameController.selectCard);
					System.out.println("PLACE CARD2");
				}
			}
		});
	}

	public void setUpCellPaneFromCardPane() {
		CardPane selectedCardPane = GameController.selectedCardPane;
		ImageView image = (ImageView) getNodeByRowColumnIndex(0, 0, selectedCardPane);
		this.add(image, 0, 0, 2, 2);
		setCardAbility(GameController.selectCard);
	}

	public void setCardAbility(Card card) {
		if (card instanceof Movable) {
			addCardAbility(RenderableHolder.attackDamage, card, ((Movable) card).getAttackDamage(), 0, 2);
			addCardAbility(RenderableHolder.attackRange, card, ((Movable) card).getAttackRange(), 1, 2);
			addCardAbility(RenderableHolder.heart, card, ((Movable) card).getHeart(), 0, 3);
			addCardAbility(RenderableHolder.speed, card, ((Movable) card).getSpeed(), 1, 3);
		}
	}

	public void addCardAbility(Image image, Card card, int text, int x, int y) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) / 2, (cardHight - 2 * insets) / 4);
		stackPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((cardWidth - 2 * insets) / 2);
		imageView.setFitHeight((cardHight - 2 * insets) / 4);
		Text text1 = new Text();
		text1.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		text1.setText("" + text);
		text1.setFill(Color.BLACK);
		stackPane.getChildren().addAll(imageView, text1);
		this.add(stackPane, x, y);
		GridPane.setHalignment(stackPane, HPos.CENTER);
	}

	public Node getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
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
		setUpCellPaneFromCardPane();// TODO ONLY first time
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
