package gui;

import card.Card;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sharedObject.FontHolder;

public abstract class CardPane extends GridPane {
	protected Card card;
	protected Tooltip tooltip;

	public abstract void addCardImage(Image image);

	public Card getCard() {
		return card;
	}

	public abstract void setCardAbility(Card card);

	public void setMouseEvent() {
		this.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

	public void setToolTip() {
		this.tooltip = new Tooltip();
		tooltip.setFont(FontHolder.getInstance().font12);
		tooltip.setText(card.getType() + "\n" + card.getDescription());
		setMouseEvent();
	}
}
