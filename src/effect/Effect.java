package effect;

public abstract class Effect {
	private boolean isActivateWhenUseCard = false;

	public boolean isActivateWhenUseCard() {
		return isActivateWhenUseCard;
	}

	public void setActivateWhenUseCard(boolean isActivateWhenUseCard) {
		this.isActivateWhenUseCard = isActivateWhenUseCard;
	}

	public abstract void activate();

}
