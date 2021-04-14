package gui;

import card.Card;
import card.FighterCard;
import entity.Bot;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Direction;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class CardInHandPane extends CardPane {
	private CardInHandPane cardPane = this;
	private int cardWidth = 120;
	private int cardHight = 58;
	private int insets = 2;

	public CardInHandPane(Card card) {
		this.card = card;
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(insets));
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
		setToolTip();
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if (canSelectCard()) {
					switch (card.getPlayingSide()) {
					case LEFT:
						((HandPane) GameController.gameScreen.getLeftCardsInHand()).setSelectedCard(cardPane);
						break;
					case RIGHT:
						((HandPane) GameController.gameScreen.getRightCardsInHand()).setSelectedCard(cardPane);
						break;
					}
				} else {
					System.out.println("YOU CAN'T TOUCH THIS!!");
				}
			}
		});
		addCardImage(card.getImage());
		setCardAbility(card);
		this.getRowConstraints().add(new RowConstraints((cardHight / 3) - 2 * insets));

	}

	public boolean canSelectCard() {
		// can't select it is bot card
		if (card.getPlayingSide() == Direction.LEFT && GameController.leftSideController instanceof Bot) {
			return false;
		}
		if (card.getPlayingSide() == Direction.RIGHT && GameController.rightSideController instanceof Bot) {
			return false;
		}
		if (!GameController.currentPlayingSide.equals(card.getPlayingSide())) { // can't select if not your turn
			return false;
		}
		if (isCardTooExpensive()) { // can't select if card too expensive
			return false;
		}
		if (GameController.threadDrawCard != null) {
			if (GameController.threadDrawCard.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAllCardMove != null) {
			if (GameController.threadAllCardMove.isAlive()) {
				return false;
			}
		}
		if (GameController.threadBotPlay != null) {
			if (GameController.threadBotPlay.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAllCardMove != null) {
			if (GameController.threadAllCardMove.isAlive()) {
				return false;
			}
		}
		if (GameController.threadAttackCard != null) {
			if (GameController.threadAttackCard.isAlive()) {
				return false;
			}
		}
		return true;
	}

	public boolean isCardTooExpensive() {
		switch (card.getPlayingSide()) {
		case LEFT:
			return card.getCost() > GameController.leftSideController.getMoney();
		case RIGHT:
			return card.getCost() > GameController.rightSideController.getMoney();
		}
		return true;
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
		addCardAbility(RenderableHolder.cost, card, card.getCost(), 3, 0, 2);

		if (card instanceof FighterCard) {
			addCardAbility(RenderableHolder.attackDamage, card, ((FighterCard) card).getAttackDamage(), 3, 1, 1);
			addCardAbility(RenderableHolder.attackRange, card, ((FighterCard) card).getAttackRange(), 4, 1, 1);
			addCardAbility(RenderableHolder.heart, card, ((FighterCard) card).getHeart(), 3, 2, 1);
			addCardAbility(RenderableHolder.speed, card, ((FighterCard) card).getSpeed(), 4, 2, 1);
		}
	}

	public void addCardAbility(Image image, Card card, int text, int x, int y, int columnSpan) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) * columnSpan / 5, (cardHight - 2 * insets) / 3);
		stackPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((cardWidth - 2 * insets) / 5);
		imageView.setFitHeight((cardHight - 2 * insets) / 3);
		Text text1 = new Text();
		text1.setFont(FontHolder.getInstance().font12);
		text1.setText("" + text);
		text1.setFill(Color.BLACK);
		stackPane.getChildren().addAll(imageView, text1);
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
