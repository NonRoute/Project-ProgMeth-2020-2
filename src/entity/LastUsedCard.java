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
	private final int X = 840;
	private final int Y = 5;
	private final int Height_PER_LINE = 13;

	public LastUsedCard() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}

	public int countLines(String str) {
		if (str.equals("")) {
			return 0;
		}
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameController.lastUsedCard != null) {
			Card card = GameController.lastUsedCard;
			int line = countLines(card.getDescription());
			gc.setFont(FontHolder.getInstance().font15);
			gc.setFill(Color.CRIMSON);
			gc.fillText("Recently card", X - 85, Y + 15);
			gc.setFill(Color.OLDLACE);
			gc.fillRoundRect(X, Y, 120, 20 + Height_PER_LINE * line, 5, 5);
			gc.setStroke(Color.BLACK);
			gc.strokeRoundRect(X, Y, 120, 20 + Height_PER_LINE * line, 5, 5);
			gc.setFill(Color.BLACK);
			gc.setFont(FontHolder.getInstance().font12);
			gc.fillText("- " + card.getType() + " -\n" + card.getDescription(), X + 10, Y + 13);
		}
	}

}
