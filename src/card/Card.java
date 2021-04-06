package card;

import effect.Effect;
import effect.NoEffect;
import entity.Entity;
import logic.Direction;

public abstract class Card extends Entity implements Cloneable {
	private String name;
	private int cost;
	private Effect effect;
	private boolean isInHand;

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public void activateEffect() {
		effect.activate();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(String name, String type) {
		switch (type) {
		case (""):
			this.effect = new NoEffect(name);
		}
	}

	public boolean isInHand() {
		return isInHand;
	}

	public void setInHand(boolean isInHand) {
		this.isInHand = isInHand;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

}
