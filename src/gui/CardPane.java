package gui;

import card.Card;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sharedObject.FontHolder;

public abstract class CardPane extends GridPane {
	protected Card card;

	public Card getCard() {
		return card;
	}

	public abstract void addCardImage(Image image);

	public abstract void setCardAbility(Card card);

	public void setToolTip() {
		Tooltip tooltip = new Tooltip();
		tooltip.setFont(FontHolder.getInstance().font12);
		tooltip.setText(card.getType() + "\n" + card.getDescription());
		this.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}
}
