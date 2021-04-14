package sharedObject;

import javafx.scene.text.Font;

public class FontHolder {
	private static final String TTF = "ttf";
	private static final FontHolder instance;
	public Font font12;
	public Font font18;
	public Font font24;
	public Font font28;
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
		this.font12 = this.loadFont("EvilEmpire-4BBVK", "ttf", 12.0);
		this.font18 = this.loadFont("EvilEmpire-4BBVK", "ttf", 18.0);
		this.font24 = this.loadFont("EvilEmpire-4BBVK", "ttf", 24.0);
		this.font28 = this.loadFont("EvilEmpire-4BBVK", "ttf", 28.0);
		this.font36 = this.loadFont("EvilEmpire-4BBVK", "ttf", 36.0);
		this.font48 = this.loadFont("EvilEmpire-4BBVK", "ttf", 48.0);
		this.font64 = this.loadFont("EvilEmpire-4BBVK", "ttf", 64.0);
	}

	public Font loadFont(String name, String fontType, double size) {
		return Font.loadFont(ClassLoader.getSystemResourceAsStream("font/" + name + "." + fontType), size);
	}

}
