package trick;

import logic.Direction;
import logic.GameController;

public class ChangeControllerHeart extends Trick {
	private char activateType;
	private int heart;

	public ChangeControllerHeart(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		heart = Integer.parseInt(trickParameter.get(1));
	}

	@Override
	public void activate() {
		switch (activateType) {
		case 'T': // this side
			if (playingSide == Direction.LEFT) {
				GameController.leftSideController.reduceHeart(-heart);
			} else {
				GameController.rightSideController.reduceHeart(-heart);
			}
			break;
		case 'E': // enemy side
			if (playingSide == Direction.RIGHT) {
				GameController.leftSideController.reduceHeart(-heart);
			} else {
				GameController.rightSideController.reduceHeart(-heart);
			}
			break;
		case 'S': // both side
			GameController.leftSideController.reduceHeart(-heart);
			GameController.rightSideController.reduceHeart(-heart);
			break;
		}
		GameController.board.unHighlightAllCells();
	}

	@Override
	public String getDescription() {
		String description = "";
		switch (activateType) {
		case 'T':
			description += "Your controller's Heart ";
			break;
		case 'E':
			description += "Enemy controller's Heart ";
			break;
		case 'S':
			description += "Both side controller's Heart ";
			break;
		}
		if (heart >= 0) {
			description += "+ " + heart;
		} else {
			description += heart;
		}
		return description;
	}
}
