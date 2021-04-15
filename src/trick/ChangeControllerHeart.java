package trick;

import logic.Direction;
import logic.GameController;

public class ChangeControllerHeart extends Trick {
	private char activateType;
	private int number;

	public ChangeControllerHeart(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		number = Integer.parseInt(trickParameter.get(1));
	}

	@Override
	public void activate() {
		switch (activateType) {
		case 'T': // this side
			if (playingSide == Direction.LEFT) {
				GameController.leftSideController.reduceHeart(-number);
			} else {
				GameController.rightSideController.reduceHeart(-number);
			}
			break;
		case 'E': // enemy side
			if (playingSide == Direction.RIGHT) {
				GameController.leftSideController.reduceHeart(-number);
			} else {
				GameController.rightSideController.reduceHeart(-number);
			}
			break;
		case 'S': // both side
			GameController.leftSideController.reduceHeart(-number);
			GameController.rightSideController.reduceHeart(-number);
			break;
		}
		GameController.board.unHighlightAllCells();
	}
}
