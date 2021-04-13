package card;

import logic.Direction;
import logic.GameController;
import trick.Trick;

public class FighterCard extends Card {
	protected int attackDamage;
	protected int attackRange;
	protected int heart;
	protected int speed;
	protected int row;
	protected int column;

	public FighterCard(String deckName, String name, String description, int cost, int attackDamage, int attackRange,
			int heart, int speed) {
		super(deckName, name, description, cost);
		this.attackDamage = attackDamage;
		this.attackRange = attackRange;
		this.heart = heart;
		this.speed = speed;
	}

	public void reduceHeart(int attackCard) {
		heart -= attackCard;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
