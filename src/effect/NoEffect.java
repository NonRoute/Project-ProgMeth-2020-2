package effect;

public class NoEffect extends Effect{

	public NoEffect(String name) {
		super(name);
	}

	@Override
	public void activate() {
		//do nothing
	}

}
