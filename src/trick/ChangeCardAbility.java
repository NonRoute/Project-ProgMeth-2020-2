package trick;

import java.util.ArrayList;
import java.util.Arrays;

import card.Card;
import card.FighterCard;
import logic.GameController;

public class ChangeCardAbility extends Trick {
	private char activateType;
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;

	public ChangeCardAbility(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		attackDamage = Integer.parseInt(trickParameter.get(1));
		attackRange = Integer.parseInt(trickParameter.get(2));
		heart = Integer.parseInt(trickParameter.get(3));
		speed = Integer.parseInt(trickParameter.get(4));
	}

	@Override
	public void activate() {
		FighterCard card = null;
		switch (activateType) {
		case 'A':
			card = GameController.board.getRandomFriendly(playingSide);
			break;
		case 'B':
			card = GameController.board.getRandomEnemy(playingSide);
			break;
		case 'C':
			card = GameController.targetCard;
			break;
		case 'D':
			card = GameController.targetCard;
			break;
		}
		Update(card);
		GameController.board.update();
		GameController.board.removeDeadCards();
	}

	public void Update(FighterCard card) {
		if (card != null) {
			card.setAttackDamage(card.getAttackDamage() + attackDamage);
			card.setAttackRange(card.getAttackRange() + attackRange);
			card.setHeart(card.getHeart() + heart);
			card.setSpeed(card.getSpeed() + speed);
		}
	}
}
