package card;

import logic.Direction;
import trick.Trick;

public class TrickCard extends Card implements Trickable {
	private Trick trick;

	public TrickCard(String deckName, int cost, Trick trick) {
		super(deckName, cost);
		this.trick = trick;
		this.description = setDescription();
		leftPlayingSideImage = trick.getImage();
		rightPlayingSideImage = trick.getImage();
	}

	@Override
	public void activateTrick() {
		trick.activate();
	}

	@Override
	public Object clone() {
		TrickCard card = (TrickCard) super.clone();
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
		this.trick.setPlayingSide(playingSide);
	}

	@Override
	public void setTrick(Trick trick) {
		this.trick = trick;
	}

}
