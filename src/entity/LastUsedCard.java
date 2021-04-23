package entity;

import card.Card;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class LastUsedCard extends Entity {
	private final int X = 350;
	private final int Y = 68;

	public LastUsedCard() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}

	public String replaceLineWithSpace(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		String text = "";
		for (int i = 0; i < lines.length; i++) {
			text += lines[i] + " ";
		}
		return text;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameController.lastUsedCard != null) {
			Card card = GameController.lastUsedCard;
			String description = replaceLineWithSpace(card.getDescription());
			gc.setFont(FontHolder.getInstance().font15);
			gc.setFill(Color.ROYALBLUE);
			gc.fillText("Recently card:", X, Y);
			gc.setFill(Color.BLACK);
			gc.setFont(FontHolder.getInstance().font15);
			gc.fillText("- " + card.getType() + " -\n" + description, X, Y + 17);
		}
	}

}
