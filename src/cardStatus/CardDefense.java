package cardStatus;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardDefense extends CardStatusShowChangedHeart {
	public CardDefense(int row, int column, int attackDamage) {
		this.showDuration = GameController.DELAY_ATTACK;
		this.attackDamage = attackDamage;
		this.row = row;
		this.column = column;
		image = new ImageView(RenderableHolder.cardDefense);
		image.setEffect(new DropShadow());
		showImage();
	}
}
