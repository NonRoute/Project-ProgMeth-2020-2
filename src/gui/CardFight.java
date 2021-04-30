package gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import sharedObject.RenderableHolder;

public class CardFight extends CardStatus {
	public CardFight(int row, int column, int attackDamage) {
		this.attackDamage = attackDamage;
		this.row = row;
		this.column = column;
		image = new ImageView(RenderableHolder.cardFight);
		image.setEffect(new DropShadow());
		showImage();
	}

	public int indentImageX() {
		return 5;
	}

	public int indentImageY() {
		return 19;
	}
}
