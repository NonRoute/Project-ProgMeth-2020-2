package card;

import effect.Trick;
import effect.NoEffect;

public class TrickCard extends Card implements Trickable {
	private Trick effect;

	public void activateEffect() {
		effect.activate();
	}

	public Trick getEffect() {
		return effect;
	}

	public void setTrick(String trick, String trickParameter) {
		switch (trick) {
	}
}
