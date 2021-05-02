package card;

import logic.Direction;
import sharedObject.RenderableHolder;
import trick.Trick;

public class MagicianCard extends FighterCard implements Trickable {
	private Trick trick;

	public MagicianCard(String deckName, int cost, int attackDamage, int attackRange,
			int heart, int speed, Trick trick) {
		super(deckName, cost, attackDamage, attackRange, heart, speed);
		this.trick = trick;
		this.description = setDescription();
		switch (deckName) { // set Magician image
		case "Angel":
			leftPlayingSideImage = RenderableHolder.angelMagicianL;
			rightPlayingSideImage = RenderableHolder.angelMagicianR;
			break;
		case "Devil":
			leftPlayingSideImage = RenderableHolder.devilMagicianL;
			rightPlayingSideImage = RenderableHolder.devilMagicianR;
			break;
		case "Test": // TODO Remove
			leftPlayingSideImage = RenderableHolder.testDeckNameLeft;
			rightPlayingSideImage = RenderableHolder.testDeckNameRight;
			break;
		}
	}
	
	public void activateTrick() {
		trick.activate();
	}

	public Object clone() {
		MagicianCard card = (MagicianCard) super.clone();
		card.setTrick((Trick) card.getTrick().clone());
		return card;
	}

	public Trick getTrick() {
		return trick;
	}

	public String setDescription() {
		return trick.getDescription();
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		trick.setPlayingSide(playingSide);
	}

	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void setTrick(Trick trick) {
		this.trick = trick;
	}

}
