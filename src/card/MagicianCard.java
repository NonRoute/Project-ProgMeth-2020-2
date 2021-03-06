package card;

import logic.Direction;
import sharedObject.RenderableHolder;
import trick.Trick;

public class MagicianCard extends FighterCard implements Trickable {
	private Trick trick;

	public MagicianCard(String deckName, int cost, int attackDamage, int attackRange,
			int health, int speed, Trick trick) {
		super(deckName, cost, attackDamage, attackRange, health, speed);
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
		}
	}
	
	@Override
	public void activateTrick() {
		trick.activate();
	}

	@Override
	public Object clone() {
		MagicianCard card = (MagicianCard) super.clone();
		card.setTrick((Trick) card.getTrick().clone());
		return card;
	}

	@Override
	public Trick getTrick() {
		return trick;
	}

	@Override
	public String setDescription() {
		return trick.getDescription();
	}

	@Override
	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		trick.setPlayingSide(playingSide);
	}

	@Override
	public void setTrick(Trick trick) {
		this.trick = trick;
	}

}
