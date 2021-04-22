package entity;

import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Phase extends Entity {
	private int x = 590;
	private int y = 10;

	public Phase() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameController.threadAllCardMove != null && GameController.threadAllCardMove.isAlive()) {
			gc.drawImage(RenderableHolder.PhaseMove, x, y, 100, 100);
		}
		else if (GameController.threadBotPlay != null && GameController.threadBotPlay.isAlive()) {
			gc.drawImage(RenderableHolder.PhaseBot, x, y, 100, 100);
		}
		else if (GameController.threadAttackCard != null && GameController.threadAttackCard.isAlive()) {
			gc.drawImage(RenderableHolder.PhaseFight, x, y, 100, 100);
		}
	}

}
