package exception;

import java.util.Arrays;

public class WrongDeckDataException extends Exception {

	public WrongDeckDataException(String deckName, int line, String[] wrongDeckData) {
		super("Wrong " + deckName + " deck data at line " + line + 1 + " : " + Arrays.toString(wrongDeckData));
	}
}
