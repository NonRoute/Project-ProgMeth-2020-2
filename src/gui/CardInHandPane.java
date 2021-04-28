package gui;

import card.Card;
import card.FighterCard;
import card.TrickCard;
import entity.Bot;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
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
import javafx.scene.text.Text;
import logic.Direction;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

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
		setMouseEvent();
		addCardImage(card.getImage());
		setCardAbility(card);
		this.getRowConstraints().add(new RowConstraints((cardHight / 3) - 2 * insets));
	}

	public void addCardAbility(Image image, Card card, int value, int defultValue, int x, int y, int columnSpan) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) * columnSpan / 5, (cardHight - 2 * insets) / 3);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(((cardWidth - 2 * insets) / 5) + 1);
		imageView.setFitHeight(((cardHight - 2 * insets) / 3) + 1);
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font15);
		text.setText("" + value);
		if (value > defultValue) {
			text.setFill(Color.LIGHTGREEN);
		} else if (value == defultValue) {
			text.setFill(Color.WHITE);
		} else {
			text.setFill(Color.LIGHTPINK);
		}
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.BLACK);
		dropShadow.setRadius(0.3);
		dropShadow.setSpread(0.6);
		dropShadow.setOffsetX(1);
		text.setEffect(dropShadow);
		stackPane.getChildren().addAll(imageView, text);
		this.add(stackPane, x, y, columnSpan, 1);
		GridPane.setHalignment(stackPane, HPos.CENTER);
	}

	public void addCardImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(cardWidth - 2 * insets);
		imageView.setFitHeight(cardHight - 2 * insets);
		this.add(imageView, 0, 0, 3, 3);
		GridPane.setHalignment(imageView, HPos.CENTER);
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
		if (card instanceof TrickCard && !GameController.board.canPlayTrickCard((TrickCard) card)) {
			// can't select if can't use this trick card
			return false;
		}
		if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) {
			return false;
		}
		if (GameController.threadAllCardMove != null && GameController.threadAllCardMove.isAlive()) {
			return false;
		}
		if (GameController.threadBotPlay != null && GameController.threadBotPlay.isAlive()) {
			return false;
		}
		if (GameController.threadAllCardMove != null && GameController.threadAllCardMove.isAlive()) {
			return false;
		}
		if (GameController.threadAttackCard != null && GameController.threadAttackCard.isAlive()) {
			return false;
		}
		return true;
	}

	public void highlight() {
		this.setBackground(
				new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
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

	public boolean isSelect() {
		return GameController.selectedCardPane == this;
	}

	public void setCardAbility(Card card) {
		addCardAbility(RenderableHolder.cost, card, card.getCost(), card.getCost(), 3, 0, 2);

		if (card instanceof FighterCard) {
			addCardAbility(RenderableHolder.attackDamage, card, ((FighterCard) card).getAttackDamage(),
					((FighterCard) card).getDefaultAttackDamage(), 3, 1, 1);
			addCardAbility(RenderableHolder.attackRange, card, ((FighterCard) card).getAttackRange(),
					((FighterCard) card).getDefaultAttackRange(), 4, 1, 1);
			addCardAbility(RenderableHolder.heart, card, ((FighterCard) card).getHeart(),
					((FighterCard) card).getDefaultHeart(), 3, 2, 1);
			addCardAbility(RenderableHolder.speed, card, ((FighterCard) card).getSpeed(),
					((FighterCard) card).getDefaultSpeed(), 4, 2, 1);
		}
	}

	public void setMouseEvent() {
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
					SoundHolder.getInstance().cannotSelect.play();
				}
			}
		});
		this.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
			if (canSelectCard()) {
				cardPane.setBackground(new Background(
						new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, new CornerRadii(3), new Insets(3))));
			}
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
			if (!isSelect()) {
				cardPane.setBackground(
						new Background(new BackgroundFill(Color.WHITE, new CornerRadii(3), new Insets(3))));
			}
		});
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(3))));
		this.setBorder(new Border(
				new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(3))));
	}

}
