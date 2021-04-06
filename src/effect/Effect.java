package effect;

public abstract class Effect {
	private String name;
	private boolean isActivateWhenUseCard = false;

	public Effect(String name) {
		this.name = name;
	}

	public abstract void activate();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
