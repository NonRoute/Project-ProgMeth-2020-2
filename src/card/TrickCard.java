package card;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import trick.Trick;

public class TrickCard extends Card implements Trickable {
	private Trick trick;

	public TrickCard(String name, String description, int cost, Trick trick) {
		super(name, description, cost);
		this.trick = trick;
	}

	public void activateTrick() {
		trick.activate();
	}

	public Trick getTrick() {
		return trick;
	}

	public void setTrick(Trick trick) {
		this.trick = trick;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		trick.setPlayingSide(playingSide);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
