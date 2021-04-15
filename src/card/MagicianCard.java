package card;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;
import trick.Trick;

public class MagicianCard extends FighterCard implements Trickable {
	private Trick trick;

	public MagicianCard(String deckName, String description, int cost, int attackDamage, int attackRange,
			int heart, int speed, Trick trick) {
		super(deckName, description, cost, attackDamage, attackRange, heart, speed);
		this.trick = trick;
	}

	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Object clone() {
		MagicianCard card = (MagicianCard) super.clone();
		card.setTrick((Trick) card.getTrick().clone());
		return card;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		trick.setPlayingSide(playingSide);
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

}
