package gui;

import java.util.Random;

import card.Card;
import card.FighterCard;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class CardOnBoardPane extends CardPane {
	private CardOnBoardPane cardPane = this;
	private int cardWidth = 88;
	private int cardHeight = 112;
	private int insets = 2;

	public CardOnBoardPane(Card card) {
		this.card = card;
		this.setPrefSize(cardWidth, cardHeight);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(insets));
		addCardImage(card.getImage());
		setCardAbility(card);
		setToolTip();
		this.getRowConstraints().add(new RowConstraints((cardHeight / 4) - 2 * insets));
	}

	public void addCardAbility(Image image, Card card, int value, int defultValue, int x, int y) {
		StackPane stackPane = new StackPane();
		stackPane.setPrefSize((cardWidth - 2 * insets) / 2, (cardHeight - 2 * insets) / 4);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth((cardWidth - 2 * insets) / 2);
		imageView.setFitHeight((cardHeight - 2 * insets) / 4);
		Text text = new Text();
		text.setFont(FontHolder.getInstance().font21);
		text.setText("" + value);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(0.3);
		dropShadow.setSpread(1);
		dropShadow.setOffsetX(1);
		dropShadow.setColor(Color.BLACK);
		text.setEffect(dropShadow);
		if (value > defultValue) {
			text.setFill(Color.LIGHTGREEN);
		} else if (value == defultValue) {
			text.setFill(Color.WHITE);
		} else {
			text.setFill(Color.LIGHTPINK);
		}
		stackPane.getChildren().addAll(imageView, text);
		this.add(stackPane, x, y);
		GridPane.setHalignment(stackPane, HPos.CENTER);
	}

	public void addCardImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(cardWidth - (2 * insets));
		imageView.setFitHeight((cardHeight / 2) - (2 * insets));
		this.add(imageView, 0, 0, 2, 2);
		GridPane.setHalignment(imageView, HPos.CENTER);
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
									playMoveSound();
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
									card.setColumn(card.getColumn() + 1);
									GameController.board.setCard(cardPane, card.getRow(), card.getColumn());
								}
							});
							Thread.sleep(GameController.DELAY_CARD_MOVE);
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
									playMoveSound();
									GameController.board.removeCardOnMap(card.getRow(), card.getColumn());
									card.setColumn(card.getColumn() - 1);
									GameController.board.setCard(cardPane, card.getRow(), card.getColumn());
								}
							});
							Thread.sleep(GameController.DELAY_CARD_MOVE);
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
	
	public void playMoveSound() {
		Random rand = new Random();
		int n = rand.nextInt(6);
		switch(n) {
		case 0:
			SoundHolder.getInstance().move1.play();
			break;
		case 1:
			SoundHolder.getInstance().move2.play();
			break;
		case 2:
			SoundHolder.getInstance().move3.play();
			break;
		case 3:
			SoundHolder.getInstance().move4.play();
			break;
		case 4:
			SoundHolder.getInstance().move5.play();
			break;
		case 5:
			SoundHolder.getInstance().move6.play();
			break;
		case 6:
			SoundHolder.getInstance().move7.play();
			break;
		}
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
}
