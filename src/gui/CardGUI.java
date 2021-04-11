package gui;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardGUI extends Button {
	private int cardWidth = 80;
	private int cardHight = 58;

	public CardGUI(String deckName, String cardtype, Direction playingSide) {
		this.setMaxSize(cardWidth, cardHight);
		this.setPadding(new Insets(10));
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
		default: //TODO remove when finish
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
					ImageView imageView = new ImageView("picture/TestDeckNameLeft.png");
					imageView.setFitWidth(cardWidth);
					imageView.setFitHeight(cardHight);
					this.setGraphic(imageView);
				} else {
					ImageView imageView = new ImageView("picture/TestDeckNameRight.png");
					imageView.setFitWidth(cardWidth);
					imageView.setFitHeight(cardHight);
					this.setGraphic(imageView);
				}
				break;
			}
			break;
		}
	}

}
