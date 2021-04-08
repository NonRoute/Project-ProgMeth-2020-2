package card;

import trick.Trick;

public interface Trickable {

	void activateTrick();
	void setTrick(String trick, String trickParameter);
	Trick getTrick();
}
