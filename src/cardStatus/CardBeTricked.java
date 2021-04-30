package cardStatus;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardBeTricked extends CardStatus{

	public CardBeTricked(int row, int column) {
		this.showDuration = 1000;
		this.row = row;
		this.column = column;
		image = new ImageView(RenderableHolder.trick);
		image.setEffect(new DropShadow());
		showImage();
	}
}
