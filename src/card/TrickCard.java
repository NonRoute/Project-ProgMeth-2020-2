package card;

import trick.Trick;

public class TrickCard extends Card implements Trickable {
	private Trick trick;

	public void activateTrick() {
		trick.activate();
	}

	public Trick getTrick() {
		return trick;
	}

	public void setTrick(String trick, String trickParameter) {
		switch (trick) {
	}
}
