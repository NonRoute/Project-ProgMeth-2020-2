package card;

import logic.Direction;
import trick.Trick;

public interface Trickable {

	void activateTrick();
	Trick getTrick();
	public String setDescription();
	void setPlayingSide(Direction playingSide);
	void setTrick(Trick trick);
}
