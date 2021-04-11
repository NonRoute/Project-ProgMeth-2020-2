package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;

public class CardGUI extends Canvas {
	private int cardWidth = 80;
	private int cardHight = 58;

	public CardGUI(String deckName, String cardtype, Direction playingSide) {
		this.resize(cardWidth, cardHight);
		GraphicsContext gc = this.getGraphicsContext2D();
		switch (deckName) {
		case "Angel":
			switch (cardtype) {
			case "Fighter":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Migician":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Trick":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			}
			break;
		case "Devil":
			switch (cardtype) {
			case "Fighter":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Migician":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Trick":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			}
			break;
		case "Test":
			switch (cardtype) {
			case "Fighter":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Migician":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			case "Trick":
				if (playingSide == Direction.LEFT) {
					gc.drawImage(RenderableHolder.TestDeckNameLeft, cardWidth, cardHight);
				} else {
					gc.drawImage(RenderableHolder.TestDeckNameRight, cardWidth, cardHight);
				}
				break;
			}
			break;
		}
	}

}
