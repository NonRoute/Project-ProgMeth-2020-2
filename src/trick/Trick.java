package trick;

import java.util.ArrayList;
import java.util.Arrays;

import logic.Direction;

public abstract class Trick {
	protected Direction playingSide;
	protected ArrayList<String> trickParameter;

	public abstract void activate();

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
