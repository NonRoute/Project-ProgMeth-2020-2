package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.FontHolder;
import sharedObject.RenderableHolder;

public class Turn extends Entity {

	public Turn() {
		this.setZ(1);
		this.setVisible(true);
		RenderableHolder.getInstance().add(this);
	}
	
	

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFont(FontHolder.getInstance().font36);
		gc.setFill(Color.NAVY);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(Color.WHITE);
		dropShadow.setRadius(2);
		dropShadow.setSpread(1);
		gc.setEffect(dropShadow);
		gc.fillText("Turn : " + GameController.turn, 350, 50);
		gc.setEffect(null);
	}

}
