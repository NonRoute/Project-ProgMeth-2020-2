package card;

import logic.Direction;
import trick.Trick;

public class TrickCard extends Card implements Trickable {
	private Trick trick;

	public TrickCard(String deckName, int cost, Trick trick) {
		super(deckName, cost);
		this.trick = trick;
		this.description = setDescription();
	}
	
	public String setDescription() {
		return trick.getDescription();
	}

	public void activateTrick() {
		trick.activate();
	}

	public Object clone() {
		TrickCard card = (TrickCard) super.clone();
		card.setTrick((Trick) card.getTrick().clone());
		return card;
	}

	public Trick getTrick() {
		return trick;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		this.trick.setPlayingSide(playingSide);
	}

	public void setTrick(Trick trick) {
		this.trick = trick;
	}

}
