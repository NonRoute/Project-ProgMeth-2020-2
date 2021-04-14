package gui;

import card.Card;
import card.FighterCard;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private FighterCard card;
	private int cardWidth = 88;
	private int cardHight = 116;
	private int insets = 2;

	public CardOnBoardPane(Card card) {
		this.card = (FighterCard) card;
		this.setPrefSize(cardWidth, cardHight);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(insets));
		addCardImage(card.getImage());
		setUpCardAbility(card);
		this.getRowConstraints().add(new RowConstraints((cardHight / 4) - 2 * insets));
	}

	public void move() {
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
							Thread.sleep(500);
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
							Thread.sleep(500);
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
			} catch (
			InterruptedException e) {
				e.printStackTrace();
			}
		});
		GameController.threadCardMove = thread;
		thread.start();

	}

	public void attack() {
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
		return card;
	}

	public void addCardImage(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(cardWidth - (2 * insets));
		imageView.setFitHeight((cardHight / 2) - (2 * insets));
		this.add(imageView, 0, 0, 2, 2);
		GridPane.setHalignment(imageView, HPos.CENTER);
	}

	public void setUpCardAbility(Card card) {
		if (card instanceof FighterCard) {
			addCardAbility(RenderableHolder.attackDamage, card, ((FighterCard) card).getAttackDamage(), 0, 2);
			addCardAbility(RenderableHolder.attackRange, card, ((FighterCard) card).getAttackRange(), 1, 2);
			addCardAbility(RenderableHolder.heart, card, ((FighterCard) card).getHeart(), 0, 3);
			addCardAbility(RenderableHolder.speed, card, ((FighterCard) card).getSpeed(), 1, 3);
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
		text1.setFont(FontHolder.getInstance().font18);
		text1.setText("" + text);
		text1.setFill(Color.BLACK);
		stackPane.getChildren().addAll(imageView, text1);
		this.add(stackPane, x, y);
		GridPane.setHalignment(stackPane, HPos.CENTER);
	}
}
