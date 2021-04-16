package trick;

import logic.Direction;
import logic.GameController;

public class Draw extends Trick {
	private char activateType;
	private int number;

	public Draw(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		number = Integer.parseInt(trickParameter.get(1));
	}

	@Override
	public void activate() {
		switch (activateType) {
		case 'T': // this side
			if (playingSide == Direction.LEFT) {
				GameController.leftSideController.drawCard(number);
			} else {
				GameController.rightSideController.drawCard(number);
			}
			break;
		case 'E': // enemy side
			if (playingSide == Direction.RIGHT) {
				GameController.leftSideController.drawCard(number);
			} else {
				GameController.rightSideController.drawCard(number);
			}
			break;
		case 'S':// both side
			GameController.leftSideController.drawCard(number);
			GameController.rightSideController.drawCard(number);
			break;
		}
		GameController.board.unHighlightAllCells();
	}

	@Override
	public String getDescription() {
		String description = "";
		switch (activateType) {
		case 'T':
			description += "Draw ";
			break;
		case 'E':
			description += "Enemy draw ";
			break;
		case 'S':
			description += "Both side draw ";
			break;
		}
		if (number == 1) {
			description += "a card";
		} else {
			description += number + " card";
		}
		return description;
	}

}
