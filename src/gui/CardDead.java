package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import sharedObject.RenderableHolder;

public class CardDead extends CardStatus {

	public CardDead(int row, int column, int attackDamage) {
		this.attackDamage = attackDamage;
		this.row = row;
		this.column = column;
		image = new ImageView(RenderableHolder.cardDead);
		image.setEffect(new DropShadow());
		showImage();
	}

	public int indentImageX() {
		return 4;
	}

	public int indentImageY() {
		return 19;
	}

}
