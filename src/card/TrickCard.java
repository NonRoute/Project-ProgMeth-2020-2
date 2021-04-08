package card;

import effect.Effect;
import effect.NoEffect;

public class TrickCard extends Card implements Activatable{
	private Effect effect;
	
	public void activateEffect() {
		effect.activate();
	}
	
	public Effect getEffect() {
		return effect;
	}

	public void setEffect(String name, String type) {
		switch (type) {
		case (""):
			this.effect = new NoEffect();
		}
	}
}
