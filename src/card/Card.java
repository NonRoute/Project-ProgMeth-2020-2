package card;

import javafx.scene.image.Image;
import logic.Direction;
import sharedObject.RenderableHolder;

public abstract class Card implements Cloneable {
	private String description;
	private int cost;
	private boolean isInHand;
	protected Direction playingSide;
	private Image leftPlayingSideImage;
	private Image rightPlayingSideImage;

	public Card(String deckName, String description, int cost) {
		this.description = description;
		this.cost = cost;
		switch (deckName) {
		case "Angel": // TODO Update
			leftPlayingSideImage = RenderableHolder.testDeckNameLeft;
			rightPlayingSideImage = RenderableHolder.testDeckNameRight;
		case "Devil": // TODO Update
			leftPlayingSideImage = RenderableHolder.testDeckNameLeft;
			rightPlayingSideImage = RenderableHolder.testDeckNameRight;
		case "Test": // TODO Remove
			leftPlayingSideImage = RenderableHolder.testDeckNameLeft;
			rightPlayingSideImage = RenderableHolder.testDeckNameRight;
		}
	}

	public Object clone() {
		try {
			Card card = (Card) super.clone();
			return card;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isInHand() {
		return isInHand;
	}

	public void setInHand(boolean isInHand) {
		this.isInHand = isInHand;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
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

}
