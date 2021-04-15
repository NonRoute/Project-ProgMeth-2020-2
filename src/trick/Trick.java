package trick;

import java.util.ArrayList;
import java.util.Arrays;

import card.Card;
import card.TrickCard;
import logic.Direction;

public abstract class Trick implements Cloneable {
	protected Direction playingSide;
	protected ArrayList<String> trickParameter;

	public abstract void activate();

	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	
	public Trick(String trickparameterInput) {
		this.trickParameter = new ArrayList<>(Arrays.asList(trickparameterInput.split("\\.")));
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
