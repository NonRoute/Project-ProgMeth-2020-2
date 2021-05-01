package cardStatus;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardAttack extends CardStatus {
	public CardAttack(int row, int column) {
		this.showDuration = GameController.DELAY_ATTACK;
		this.row = row;
		this.column = column;
		image = new ImageView(RenderableHolder.cardAttack);
		image.setEffect(new DropShadow());
		showImage();
	}
}
