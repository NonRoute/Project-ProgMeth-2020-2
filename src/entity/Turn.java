package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class Turn extends Entity {

	public Turn() {
		RenderableHolder.getInstance().add(this);
		this.setVisible(true);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFont(FontHolder.getInstance().font36);
		gc.setFill(Color.NAVY);
		gc.fillText("Turn : " + GameController.turn, 350, 50);
	}

}
