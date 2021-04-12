package gui;

import card.Card;
import card.Movable;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardPane extends GridPane {
	private CardPane cardGUI = this;
	private int cardWidth = 130;
	private int cardHight = 58;

	public CardPane(String deckName, Card card) {
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) { // TODO can't select if not your turn
				switch (card.getPlayingSide()) {
				case LEFT:
					((CardsInHandPane) GameController.gameScreen.getLeftCardsInHand()).setSelectedCard(cardGUI, card);
					break;
				case RIGHT:
					((CardsInHandPane) GameController.gameScreen.getRightCardsInHand()).setSelectedCard(cardGUI, card);
					break;
				}
			}
		});
		setCardImage(deckName, card);
		setCardAbility(card);
		this.setGridLinesVisible(true);
		this.getRowConstraints().add(new RowConstraints(cardHight/3));
	}

	public void setCardImage(String deckName, Card card) {
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
			switch (card.getType()) {
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
				if (card.getPlayingSide() == Direction.LEFT) {
					addCardImage(RenderableHolder.testDeckNameLeft);
				} else {
					addCardImage(RenderableHolder.testDeckNameRight);
				}
				break;
			}
			break;
		}
	}

	public void addCardImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
//		imageView.setFitWidth(cardWidth);
		imageView.setFitHeight(cardHight);
		this.add(imageView, 0, 0, 3, 3);
	}

	public void setCardAbility(Card card) {
		Text cost = new Text();
		cost.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		cost.setText("C:"+card.getCost());
		cost.setFill(Color.BLACK);
		this.add(cost, 3, 0, 2, 1);
		GridPane.setHalignment(cost, HPos.CENTER);
		if (card instanceof Movable) {
			Text attackDamage = new Text();
			attackDamage.setFont(Font.font("Arial", FontWeight.BOLD, 10));
			attackDamage.setText("A:"+((Movable) card).getAttackDamage());
			attackDamage.setFill(Color.BLACK);
			this.add(attackDamage, 3, 1);
			GridPane.setHalignment(attackDamage, HPos.CENTER);
			Text attackRange = new Text();
			attackRange.setFont(Font.font("Arial", FontWeight.BOLD, 10));
			attackRange.setText("R:"+((Movable) card).getAttackRange());
			attackRange.setFill(Color.BLACK);
			this.add(attackRange, 4, 1);
			GridPane.setHalignment(attackRange, HPos.CENTER);
			Text heart = new Text();
			heart.setFont(Font.font("Arial", FontWeight.BOLD, 10));
			heart.setText("H:"+((Movable) card).getHeart());
			heart.setFill(Color.BLACK);
			this.add(heart, 3, 2);
			GridPane.setHalignment(heart, HPos.CENTER);
			Text speed = new Text();
			speed.setFont(Font.font("Arial", FontWeight.BOLD, 10));
			speed.setText("S:"+((Movable) card).getSpeed());
			speed.setFill(Color.BLACK);
			this.add(speed, 4, 2);
			GridPane.setHalignment(speed, HPos.CENTER);
		}
	}

	public void highlight() {
		this.setBackground(
				new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
	}
}
