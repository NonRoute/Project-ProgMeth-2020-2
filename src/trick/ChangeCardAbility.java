package trick;

import java.util.ArrayList;
import java.util.Arrays;

import card.Card;
import card.Movable;
import logic.GameController;

public class ChangeCardAbility extends Trick {
	private char activateArea;
	private char conditionType;
	private int conditionCost;
	private int conditionAttackDamage;
	private int conditionAttackRange;
	private int conditionHeart;
	private int conditionSpeed;
	private int cost;
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;

	public ChangeCardAbility(String trickparameter) {
		super(trickparameter);
		activateArea = (trickParameter.get(1)).charAt(0);
		conditionType = trickParameter.get(2).charAt(0);
		if (conditionType != 'A') {
			conditionCost = Integer.parseInt(trickParameter.get(3));
			conditionAttackDamage = Integer.parseInt(trickParameter.get(4));
			conditionAttackRange = Integer.parseInt(trickParameter.get(5));
			conditionHeart = Integer.parseInt(trickParameter.get(6));
			conditionSpeed = Integer.parseInt(trickParameter.get(7));
		}
		cost = Integer.parseInt(trickParameter.get(8));
		attackDamage = Integer.parseInt(trickParameter.get(9));
		attackRange = Integer.parseInt(trickParameter.get(10));
		heart = Integer.parseInt(trickParameter.get(11));
		speed = Integer.parseInt(trickParameter.get(12));
	}

	@Override
	public void activate() {
		switch (activateArea) {
		case 'R':
			Movable card = (Movable) GameController.board.getRandomEnemy(playingSide);
			Update(card);
		}

	}

	public void Update(Movable card) {
		card.setCost(card.getCost() + cost);
		card.setAttackDamage(card.getAttackDamage() + attackDamage);
		card.setAttackRange(card.getAttackRange() + attackRange);
		card.setHeart(card.getHeart() + heart);
		card.setSpeed(card.getSpeed() + speed);
	}
}
