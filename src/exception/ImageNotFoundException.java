package exception;

public class ImageNotFoundException extends Exception {
	
	public ImageNotFoundException(String fileName) {
		super("Image " + fileName + " not found");
	}

}
