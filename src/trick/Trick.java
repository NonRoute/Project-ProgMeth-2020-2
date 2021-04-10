package trick;

import java.util.ArrayList;
import java.util.Arrays;

import logic.Direction;

public abstract class Trick {
	protected Direction playingSide;
	protected ArrayList<String> trickParameter;
	protected boolean isActivateWhenPlayCard = false;
	protected boolean isActivateWhenCardAttack = false;
	protected boolean isActivateWhenCardGetDamage = false;
	protected boolean isActivateWhenCardMove = false;
	protected boolean isActivateWhenCardDestory = false;
	protected boolean isActivateWhenCardBeDestoryed = false;
	protected boolean isActivateWhenNewTurn = false;
	protected boolean isActivateWhenDrawACard = false;

	public abstract void activate();

	public Trick(String trickparameter) {
		this.trickParameter = new ArrayList<>(Arrays.asList(trickparameter.split(".")));
		switch (trickParameter.get(0)) {
		case "P":
			isActivateWhenPlayCard = true;
		case "A":
			isActivateWhenCardAttack = true;
		case "G":
			isActivateWhenCardGetDamage = true;
		case "M":
			isActivateWhenCardMove = true;
		case "D":
			isActivateWhenCardDestory = true;
		case "X":
			isActivateWhenCardBeDestoryed = true;
		case "T":
			isActivateWhenNewTurn = true;
		case "C":
			isActivateWhenDrawACard = true;
		}
	}

	public boolean isActivateWhenUseCard() {
		return isActivateWhenPlayCard;
	}

	public void setActivateWhenUseCard(boolean isActivateWhenPlayCard) {
		this.isActivateWhenPlayCard = isActivateWhenPlayCard;
	}

	public boolean isActivateWhenPlayCard() {
		return isActivateWhenPlayCard;
	}

	public void setActivateWhenPlayCard(boolean isActivateWhenPlayCard) {
		this.isActivateWhenPlayCard = isActivateWhenPlayCard;
	}

	public boolean isActivateWhenCardAttack() {
		return isActivateWhenCardAttack;
	}

	public void setActivateWhenCardAttack(boolean isActivateWhenCardAttack) {
		this.isActivateWhenCardAttack = isActivateWhenCardAttack;
	}

	public boolean isActivateWhenCardGetDamage() {
		return isActivateWhenCardGetDamage;
	}

	public void setActivateWhenCardGetDamage(boolean isActivateWhenCardGetDamage) {
		this.isActivateWhenCardGetDamage = isActivateWhenCardGetDamage;
	}

	public boolean isActivateWhenCardMove() {
		return isActivateWhenCardMove;
	}

	public void setActivateWhenCardMove(boolean isActivateWhenCardMove) {
		this.isActivateWhenCardMove = isActivateWhenCardMove;
	}

	public boolean isActivateWhenCardDestory() {
		return isActivateWhenCardDestory;
	}

	public void setActivateWhenCardDestory(boolean isActivateWhenCardDestory) {
		this.isActivateWhenCardDestory = isActivateWhenCardDestory;
	}

	public boolean isActivateWhenCardBeDestoryed() {
		return isActivateWhenCardBeDestoryed;
	}

	public void setActivateWhenCardBeDestoryed(boolean isActivateWhenCardBeDestoryed) {
		this.isActivateWhenCardBeDestoryed = isActivateWhenCardBeDestoryed;
	}

	public boolean isActivateWhenNewTurn() {
		return isActivateWhenNewTurn;
	}

	public void setActivateWhenNewTurn(boolean isActivateWhenNewTurn) {
		this.isActivateWhenNewTurn = isActivateWhenNewTurn;
	}

	public boolean isActivateWhenDrawACard() {
		return isActivateWhenDrawACard;
	}

	public void setActivateWhenDrawACard(boolean isActivateWhenDrawACard) {
		this.isActivateWhenDrawACard = isActivateWhenDrawACard;
	}

	public Direction getPlayingSide() {
		return playingSide;
	}

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
	}

}
