package card;

import entity.Entity;
import javafx.scene.image.Image;
import logic.Direction;
import sharedObject.RenderableHolder;

public abstract class Card extends Entity implements Cloneable {
	private String name;
	private String description;
	private int cost;
	private boolean isInHand;
	protected Direction playingSide;
	private Image leftPlayingSideImage;
	private Image rightPlayingSideImage;

	public Card(String deckName, String name, String description, int cost) {
		this.name = name;
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
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public String getType() {
		if (this instanceof FighterCard) {
			return "Fighter";
		} else if (this instanceof MagicianCard) {
			return "Magician";
		} else {
			return "Trick";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
