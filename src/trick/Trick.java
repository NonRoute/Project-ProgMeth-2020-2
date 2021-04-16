package trick;

import java.util.ArrayList;
import java.util.Arrays;

import card.FighterCard;
import entity.Bot;
import logic.Direction;
import logic.GameController;

public abstract class Trick implements Cloneable {
	protected String description;
	protected Direction playingSide;
	protected ArrayList<String> trickParameter;

	public abstract void activate();
	public abstract String getDescription();
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	
	public Trick(String trickparameter) {
		this.trickParameter = new ArrayList<>(Arrays.asList(trickparameter.split("\\.")));
	}
	
	public FighterCard getBotSelectTargetCard() {
		switch (playingSide) {
		case LEFT:
			return ((Bot) GameController.leftSideController).getTargetCard(this);
		case RIGHT:
			return ((Bot) GameController.rightSideController).getTargetCard(this);
		}
		return null;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
	}

	public char getFirstParameter() {
		return trickParameter.get(0).charAt(0);
	}

}
