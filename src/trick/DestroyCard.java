package trick;

import card.FighterCard;
import logic.GameController;

public class DestroyCard extends Trick {
	private char activateType;

	public DestroyCard(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
	}

	@Override
	public void activate() {
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
		Update(card);
		GameController.board.update();
		GameController.board.removeDeadCards();
	}

	public void Update(FighterCard card) {
		if (card != null) {
			card.setHeart(0);
		}
	}

}
