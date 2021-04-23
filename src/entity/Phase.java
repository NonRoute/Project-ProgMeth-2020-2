package entity;

import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Phase extends Entity {
	private int x = 595;
	private int y = 5;

	public Phase() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameController.threadAllCardMove != null && GameController.threadAllCardMove.isAlive()) {
			gc.drawImage(RenderableHolder.phaseMove, x, y, 90, 90);
		} else if (GameController.threadAttackCard != null && GameController.threadAttackCard.isAlive()) {
			gc.drawImage(RenderableHolder.phaseAttack, x, y, 90, 90);
		} else if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) {
			gc.drawImage(RenderableHolder.phaseDrawCard, x, y, 90, 90);
		} else if (GameController.threadBotPlay != null && GameController.threadBotPlay.isAlive()) {
			gc.drawImage(RenderableHolder.phaseBot, x - 5, y, 100, 100);
		}
	}

}
