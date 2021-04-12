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
	private int cardWidth = 120;
	private int cardHight = 58;
	private int insets = 2;

	public CardPane(String deckName, Card card) {
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(insets));
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
		this.getRowConstraints().add(new RowConstraints((cardHight / 3) - 2 * insets));
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
		imageView.setFitWidth(cardWidth - 2 * insets);
		imageView.setFitHeight(cardHight - 2 * insets);
		this.add(imageView, 0, 0, 3, 3);
		GridPane.setHalignment(imageView, HPos.CENTER);
	}

	public void setCardAbility(Card card) {
		addCardAbility(RenderableHolder.cost, card, 3, 0, 2);

		if (card instanceof Movable) {
			addCardAbility(RenderableHolder.attackDamage, card, 3, 1, 1);
			addCardAbility(RenderableHolder.attackRange, card, 4, 1, 1);
			addCardAbility(RenderableHolder.heart, card, 3, 2, 1);
			addCardAbility(RenderableHolder.speed, card, 4, 2, 1);
		}
	}

	public void addCardAbility(Image image, Card card, int x, int y, int columnSpan) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) * columnSpan / 5, (cardHight - 2 * insets) / 3);
		stackPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((cardWidth - 2 * insets) / 5);
		imageView.setFitHeight((cardHight - 2 * insets) / 3);
		Text text = new Text();
		text.setFont(Font.font("Arial", FontWeight.BOLD, 10));
		text.setText("" + card.getCost());
		text.setFill(Color.BLACK);
		stackPane.getChildren().addAll(imageView, text);
		this.add(stackPane, x, y, columnSpan, 1);
		GridPane.setHalignment(stackPane, HPos.CENTER);
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
