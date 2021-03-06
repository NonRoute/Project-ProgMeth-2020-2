package trick;

import card.FighterCard;
import cardStatus.CardBeTricked;
import exception.WrongTrickActivateTypeException;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class DestroyCard extends Trick {
	private char activateType;

	public DestroyCard(String trickparameter) throws WrongTrickActivateTypeException {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		if (!"BD".contains(String.valueOf(activateType))) {
			throw new WrongTrickActivateTypeException();
		}
		image = RenderableHolder.cardDead;
	}

	@Override
	public void activate() {
		SoundHolder.trick.play();
		FighterCard card = null;
		switch (activateType) {
		case 'B': // Random Enemy
			card = GameController.board.getRandomEnemy(playingSide);
			break;
		case 'D':// Select Enemy
			if (GameController.isBotSide(playingSide)) { // if bot play this card
				card = getBotSelectTargetCard();
			} else {
				card = GameController.targetCard;
			}
			break;
		}
		update(card);
		GameController.board.update();
		GameController.board.removeDeadCards();
	}

	@Override
	public String getDescription() {
		String description = "";
		switch (activateType) {
		case 'A':
			description += "Random a friendly";
			break;
		case 'B':
			description += "Random a enemy";
			break;
		case 'C':
			description += "Select a friendly";
			break;
		case 'D':
			description += "Select a enemy";
			break;
		}
		description += " to destory";
		return description;
	}

	public void update(FighterCard card) {
		if (card != null) {
			card.setHealth(0);
			new CardBeTricked(card.getRow(), card.getColumn()); // show image
		}
	}

}
