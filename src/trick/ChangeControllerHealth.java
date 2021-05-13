package trick;

import exception.WrongTrickActivateTypeException;
import logic.Direction;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class ChangeControllerHealth extends Trick {
	private char activateType;
	private int health;

	public ChangeControllerHealth(String trickparameter) throws WrongTrickActivateTypeException {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		if (!"TES".contains(String.valueOf(activateType))) {
			throw new WrongTrickActivateTypeException();
		}
		health = Integer.parseInt(trickParameter.get(1));
		image = RenderableHolder.changeControllerHealth;
	}

	@Override
	public void activate() {
		SoundHolder.trick.play();
		switch (activateType) {
		case 'T': // this side
			if (playingSide == Direction.LEFT) {
				GameController.leftSideController.reduceHealth(-health);
			} else {
				GameController.rightSideController.reduceHealth(-health);
			}
			break;
		case 'E': // enemy side
			if (playingSide == Direction.RIGHT) {
				GameController.leftSideController.reduceHealth(-health);
			} else {
				GameController.rightSideController.reduceHealth(-health);
			}
			break;
		case 'S': // both side
			GameController.leftSideController.reduceHealth(-health);
			GameController.rightSideController.reduceHealth(-health);
			break;
		}
		GameController.board.unHighlightAllCells();
	}

	@Override
	public String getDescription() {
		String description = "";
		switch (activateType) {
		case 'T':
			description += "Your controller's Health ";
			break;
		case 'E':
			description += "Enemy controller's Health ";
			break;
		case 'S':
			description += "Both side controller's Health ";
			break;
		}
		if (health >= 0) {
			description += "+ " + health;
		} else {
			description += health;
		}
		return description;
	}
}
