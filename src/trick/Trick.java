package trick;

public abstract class Trick {
	private boolean isActivateWhenPlayCard = false;
	private boolean isActivateWhenCardAttack = false;
	private boolean isActivateWhenCardGetDamage = false;
	private boolean isActivateWhenCardMove = false;
	private boolean isActivateWhenCardDestory = false;
	private boolean isActivateWhenCardBeDestoryed = false;
	private boolean isActivateWhenNewTurn = false;
	private boolean isActivateWhenDrawACard = false;

	public abstract void activate();
	
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

}
