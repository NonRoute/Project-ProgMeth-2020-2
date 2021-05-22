package card;

import javafx.scene.image.Image;
import logic.Direction;

public class Card implements Cloneable {
	protected String deckName;
	protected String description;
	protected int cost;
	protected boolean isInHand;
	protected Direction playingSide;
	protected Image leftPlayingSideImage;
	protected Image rightPlayingSideImage;

	public Card(String deckName, int cost) {
		this.deckName = deckName;
		this.description = "";
		this.cost = cost;
	}

	@Override
	public Object clone() {
		try {
			Card card = (Card) super.clone();
			return card;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public int getCost() {
		return cost;
	}

	public String getDescription() {
		return description;
	}

	public Image getImage() {
		switch (playingSide) {
		case LEFT:
			return leftPlayingSideImage;
		case RIGHT:
			return rightPlayingSideImage;
		}
		return null;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public String getType() {
		if (this instanceof MagicianCard) {
			return "Magician";
		} else if (this instanceof FighterCard) {
			return "Fighter";
		} else {
			return "Trick";
		}
	}

	public boolean isInHand() {
		return isInHand;
	}

	public void setCost(int cost) {
		this.cost = Math.max(0, cost);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInHand(boolean isInHand) {
		this.isInHand = isInHand;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
	}

}
