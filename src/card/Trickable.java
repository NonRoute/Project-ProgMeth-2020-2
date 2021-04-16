package card;

import logic.Direction;
import trick.Trick;

public interface Trickable {

	void activateTrick();
	Trick getTrick();
	void setTrick(Trick trick);
	void setPlayingSide(Direction playingSide);
	public String setDescription();
}
