package exception;

public class AudioNotFoundException extends Exception {

	public AudioNotFoundException(String fileName) {
		super("Audio " + fileName + " not found");
	}

}
