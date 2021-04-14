package trick;

import java.util.ArrayList;
import java.util.Arrays;

import card.Card;
import card.FighterCard;
import logic.GameController;

public class ChangeCardAbility extends Trick {
	private char activateType;
	private int cost;
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;

	public ChangeCardAbility(String trickparameter) {
		super(trickparameter);
		activateType = (trickParameter.get(0)).charAt(0);
		cost = Integer.parseInt(trickParameter.get(1));
		attackDamage = Integer.parseInt(trickParameter.get(2));
		attackRange = Integer.parseInt(trickParameter.get(3));
		heart = Integer.parseInt(trickParameter.get(4));
		speed = Integer.parseInt(trickParameter.get(5));
	}

	@Override
	public void activate() {
		System.out.println("ACTIVATE");
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
			card.setCost(card.getCost() + cost);
			card.setAttackDamage(card.getAttackDamage() + attackDamage);
			card.setAttackRange(card.getAttackRange() + attackRange);
			card.setHeart(card.getHeart() + heart);
			card.setSpeed(card.getSpeed() + speed);
		}
	}
}
