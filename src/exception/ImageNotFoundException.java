package exception;

import java.util.Arrays;

public class ImageNotFoundException extends Exception {
	
	public ImageNotFoundException(String fileName) {
		super("Image " + fileName + " not found");
	}

}
