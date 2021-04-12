package gui;

import card.Card;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardPane extends GridPane {
	private CardPane cardGUI = this;
	private int cardWidth = 130;
	private int cardHight = 58;

	public CardPane(String deckName, String cardtype, Direction playingSide, Card card) {
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) { // TODO can't select if not your turn
				switch (playingSide) {
				case LEFT:
					((CardsInHandPane) GameController.gameScreen.getLeftCardsInHand()).setSelectedCard(cardGUI, card);
					break;
				case RIGHT:
					((CardsInHandPane) GameController.gameScreen.getRightCardsInHand()).setSelectedCard(cardGUI, card);
					break;
				}
			}

		});

		System.out.println("DRAW");
		switch (deckName) {
//		case "Angel":
//			switch (cardtype) {
//			case "Fighter":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			case "Migician":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			case "Trick":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			}
//			break;
//		case "Devil":
//			switch (cardtype) {
//			case "Fighter":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			case "Migician":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			case "Trick":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			}
//			break;
		default: // TODO remove when finish
			System.out.println("Test");
			switch (cardtype) {
//			case "Fighter":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
//			case "Migician":
//				if (playingSide == Direction.LEFT) {
//					this.getChildren().add(RenderableHolder.TestDeckNameLeft);
//				} else {
//					this.getChildren().add(RenderableHolder.TestDeckNameRight);
//				}
//				break;
			default:
				if (playingSide == Direction.LEFT) {
					ImageView imageView = new ImageView(RenderableHolder.testDeckNameLeft);
					imageView.setPreserveRatio(true);
					imageView.setFitWidth(cardWidth);
					imageView.setFitHeight(cardHight);
					this.add(imageView, 0, 0);
				} else {
					ImageView imageView = new ImageView(RenderableHolder.testDeckNameRight);
					imageView.setPreserveRatio(true);
					imageView.setFitWidth(cardWidth);
					imageView.setFitHeight(cardHight);
					this.add(imageView, 0, 0);
				}
				break;
			}
			break;
		}
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
	}
}
