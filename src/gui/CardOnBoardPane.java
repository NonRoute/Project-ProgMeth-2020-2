package gui;

import card.Card;
import card.FighterCard;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class CardOnBoardPane extends CardPane {
	private CardOnBoardPane cardPane = this;
	private int cardWidth = 88;
	private int cardHight = 116;
	private int insets = 2;

	public CardOnBoardPane(Card card) {
		this.card = card;
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(insets));
		addCardImage(card.getImage());
		setCardAbility(card);
		setToolTip();
		this.getRowConstraints().add(new RowConstraints((cardHight / 4) - 2 * insets));
	}

	public void move() {
		FighterCard card = (FighterCard) this.card;
		Thread thread = new Thread(() -> {
			try {
				switch (card.getPlayingSide()) {
				case LEFT:
					for (int i = 0; i < card.getSpeed(); i++) {
						if (GameController.board.isEmpty(card.getRow(), card.getColumn() + 1)) {
							Platform.runLater(new Runnable() {
								public void run() {
									// can move to next cell
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
									card.setColumn(card.getColumn() + 1);
									GameController.board.setCardOnMap(cardPane, card.getRow(), card.getColumn());
								}
							});
							Thread.sleep(250);
						} else if (GameController.board.isOutOfBoard(card.getRow(), card.getColumn() + 1)) {
							// can attack controller
							Platform.runLater(new Runnable() {
								public void run() {
									GameController.rightSideController.reduceHeart(card.getAttackDamage());
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
								}
							});
							break;
						}
					}
					break;
				case RIGHT:
					for (int i = 0; i < card.getSpeed(); i++) {
						if (GameController.board.isEmpty(card.getRow(), card.getColumn() - 1)) {
							Platform.runLater(new Runnable() {
								public void run() {
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
									card.setColumn(card.getColumn() - 1);
									GameController.board.setCardOnMap(cardPane, card.getRow(), card.getColumn());
								}
							});
							Thread.sleep(250);
						} else if (GameController.board.isOutOfBoard(card.getRow(), card.getColumn() - 1)) {
							Platform.runLater(new Runnable() {
								public void run() {
									GameController.leftSideController.reduceHeart(card.getAttackDamage());
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
								}
							});
							break;
						}
					}
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		GameController.threadCardMove = thread;
		thread.start();

	}

	public void attack() {
		FighterCard card = (FighterCard) this.card;
		switch (card.getPlayingSide()) {
		case LEFT:
			for (int i = 1; i <= card.getAttackRange(); i++) {
				if (GameController.board.isEnemy(card.getRow(), card.getColumn() + i, card.getPlayingSide())) {
					GameController.board.attackCard(card.getRow(), card.getColumn() + i, card.getAttackDamage());
				}
			}
			break;
		case RIGHT:
			for (int i = 1; i <= card.getAttackRange(); i++) {
				if (GameController.board.isEnemy(card.getRow(), card.getColumn() - i, card.getPlayingSide())) {
					GameController.board.attackCard(card.getRow(), card.getColumn() - i, card.getAttackDamage());
				}
			}
			break;
		}
	}

	public FighterCard getCard() {
		return (FighterCard) card;
	}

	public void addCardImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(cardWidth - (2 * insets));
		imageView.setFitHeight((cardHight / 2) - (2 * insets));
		this.add(imageView, 0, 0, 2, 2);
		GridPane.setHalignment(imageView, HPos.CENTER);
	}

	public void setCardAbility(Card card) {
		if (card instanceof FighterCard) {
			addCardAbility(RenderableHolder.attackDamage, card, ((FighterCard) card).getAttackDamage(),
					((FighterCard) card).getDefaultAttackDamage(), 0, 2);
			addCardAbility(RenderableHolder.attackRange, card, ((FighterCard) card).getAttackRange(),
					((FighterCard) card).getDefaultAttackRange(), 1, 2);
			addCardAbility(RenderableHolder.heart, card, ((FighterCard) card).getHeart(),
					((FighterCard) card).getDefaultHeart(), 0, 3);
			addCardAbility(RenderableHolder.speed, card, ((FighterCard) card).getSpeed(),
					((FighterCard) card).getDefaultSpeed(), 1, 3);
		}
	}

	public void addCardAbility(Image image, Card card, int value, int defultValue, int x, int y) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) / 2, (cardHight - 2 * insets) / 4);
		stackPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, CornerRadii.EMPTY, Insets.EMPTY)));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((cardWidth - 2 * insets) / 2);
		imageView.setFitHeight((cardHight - 2 * insets) / 4);
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font18);
		text.setText("" + value);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.WHITE);
		text.setEffect(dropShadow);
		if (value > defultValue) {
			text.setFill(Color.SPRINGGREEN);
		} else if (value == defultValue) {
			text.setFill(Color.BLACK);
		} else {
			text.setFill(Color.BROWN);
		}
		stackPane.getChildren().addAll(imageView, text);
		this.add(stackPane, x, y);
		GridPane.setHalignment(stackPane, HPos.CENTER);
	}
}
