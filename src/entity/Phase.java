package entity;

import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Phase extends Entity {
	private int x = 595;
	private int y = 5;

	public Phase() {
		this.setZ(3);
		this.setVisible(true);
		RenderableHolder.getInstance().add(this);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameController.threadMoveAllCard != null && GameController.threadMoveAllCard.isAlive()) {
			gc.drawImage(RenderableHolder.phaseMove, x, y, 90, 90);
		} else if (GameController.threadStartAttackCard != null && GameController.threadStartAttackCard.isAlive()) {
			gc.drawImage(RenderableHolder.phaseAttack, x, y, 90, 90);
		} else if (GameController.threadDrawCard != null && GameController.threadDrawCard.isAlive()) {
			gc.drawImage(RenderableHolder.phaseDrawCard, x, y, 90, 90);
		} else if (GameController.threadBotPlay != null && GameController.threadBotPlay.isAlive()) {
			gc.drawImage(RenderableHolder.phaseBot, x - 5, y, 100, 100);
		}
	}

}
