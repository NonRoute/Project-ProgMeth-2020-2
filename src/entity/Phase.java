package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Phase extends Entity {

	public Phase() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(null, y, x);
	}

}
