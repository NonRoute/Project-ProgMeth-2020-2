package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	private static ArrayList<KeyCode> keypressed;

	static {
		InputUtility.keypressed = new ArrayList<KeyCode>();
	}

	public static void setKeyPressed(KeyCode keyCode) {
		if (!InputUtility.keypressed.contains(keyCode)) {
			InputUtility.keypressed.add(keyCode);
		}
	}

	public static void removeKeyPressed() {
		for (int i = InputUtility.keypressed.size() - 1; i >= 0; --i) {
			InputUtility.keypressed.remove(i);
		}
	}

	public static ArrayList<KeyCode> getKeypressed() {
		return InputUtility.keypressed;
	}

}
