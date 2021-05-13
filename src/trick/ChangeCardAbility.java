package trick;

import card.FighterCard;
import cardStatus.CardBeTricked;
import exception.WrongTrickActivateTypeException;
import logic.GameController;
import sharedObject.RenderableHolder;
import sharedObject.SoundHolder;

public class ChangeCardAbility extends Trick {
	private char activateType;
	private int attackDamage;
	private int attackRange;
	private int health;
	private int speed;

	public ChangeCardAbility(String trickparameter) throws WrongTrickActivateTypeException {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		if (!"ABCD".contains(String.valueOf(activateType))) {
			throw new WrongTrickActivateTypeException();
		}
		attackDamage = Integer.parseInt(trickParameter.get(1));
		attackRange = Integer.parseInt(trickParameter.get(2));
		health = Integer.parseInt(trickParameter.get(3));
		speed = Integer.parseInt(trickParameter.get(4));
		image = RenderableHolder.changeCardAbility;
	}

	@Override
	public void activate() { // this method be called when use card
		SoundHolder.trick.play();
		FighterCard card = null;
		switch (activateType) {
		case 'A': // Random Friendly
			card = GameController.board.getRandomFriendly(playingSide);
			break;
		case 'B': // Random Enemy
			card = GameController.board.getRandomEnemy(playingSide);
			break;
		case 'C':// Select Friendly
			if (GameController.isBotSide(playingSide)) { // if bot play this card
				card = getBotSelectTargetCard();
			} else { // use player selected target card
				card = GameController.targetCard;
			}
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
		GameController.board.unHighlightAllCells();
		GameController.board.update();
		GameController.board.removeDeadCards();
	}

	@Override
	public String getDescription() {
		String description = "";
		switch (activateType) {
		case 'A':
			description += "Random a friendly:";
			break;
		case 'B':
			description += "Random a enemy:";
			break;
		case 'C':
			description += "Select a friendly:";
			break;
		case 'D':
			description += "Select a enemy:";
			break;
		}
		description += "\n";
		if (attackDamage > 0) {
			description += "ATK DMG + " + attackDamage + "\n";
		}
		if (attackRange > 0) {
			description += "ATK RNG + " + attackRange + "\n";
		}
		if (health > 0) {
			description += "Health + " + health + "\n";
		}
		if (speed > 0) {
			description += "Speed + " + speed + "\n";
		}
		if (attackDamage < 0) {
			description += "ATK DMG " + attackDamage + "\n";
		}
		if (attackRange < 0) {
			description += "ATK RNG " + attackRange + "\n";
		}
		if (health < 0) {
			description += "Health " + health + "\n";
		}
		if (speed < 0) {
			description += "Speed " + speed + "\n";
		}
		return description;
	}

	public void update(FighterCard card) {
		if (card != null) {
			card.setAttackDamage(card.getAttackDamage() + attackDamage);
			card.setAttackRange(card.getAttackRange() + attackRange);
			card.setHealth(card.getHealth() + health);
			card.setSpeed(card.getSpeed() + speed);
			new CardBeTricked(card.getRow(), card.getColumn()); // show image
		}
	}
}
