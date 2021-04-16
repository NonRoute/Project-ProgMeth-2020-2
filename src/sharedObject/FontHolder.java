package sharedObject;

import javafx.scene.text.Font;

public class FontHolder {
	private static final FontHolder instance;
	public Font font12;
	public Font font15;
	public Font font18;
	public Font font24;
	public Font font28;
	public Font font32;
	public Font font36;
	public Font font48;
	public Font font64;

	static {
		instance = new FontHolder();
	}

	public static FontHolder getInstance() {
		return FontHolder.instance;
	}

	public FontHolder() {
		this.font12 = this.loadFont("EvilEmpire", "ttf", 12.0);
		this.font15 = this.loadFont("EvilEmpire", "ttf", 15.0);
		this.font18 = this.loadFont("EvilEmpire", "ttf", 18.0);
		this.font24 = this.loadFont("EvilEmpire", "ttf", 24.0);
		this.font28 = this.loadFont("EvilEmpire", "ttf", 28.0);
		this.font32 = this.loadFont("EvilEmpire", "ttf", 32.0);
		this.font36 = this.loadFont("EvilEmpire", "ttf", 36.0);
		this.font48 = this.loadFont("EvilEmpire", "ttf", 48.0);
		this.font64 = this.loadFont("EvilEmpire", "ttf", 64.0);
	}

	public Font loadFont(String name, String fontType, double size) {
		return Font.loadFont(ClassLoader.getSystemResourceAsStream("font/" + name + "." + fontType), size);
	}

}
