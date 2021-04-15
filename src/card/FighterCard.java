package card;

import logic.Direction;
import logic.GameController;
import trick.Trick;

public class FighterCard extends Card {
	protected final int DEFAULT_ATTACK_DAMAGE;
	protected final int DEFAULT_ATTACK_RANGE;
	protected final int DEFAULT_HEART;
	protected final int DEFAULT_SPEED;
	protected int attackDamage;
	protected int attackRange;
	protected int heart;
	protected int speed;
	protected int row;
	protected int column;

	public FighterCard(String deckName, String name, String description, int cost, int attackDamage, int attackRange,
			int heart, int speed) {
		super(deckName, name, description, cost);
		this.DEFAULT_ATTACK_DAMAGE = attackDamage;
		this.attackDamage = attackDamage;
		this.DEFAULT_ATTACK_RANGE = attackRange;
		this.attackRange = attackRange;
		this.DEFAULT_HEART = heart;
		this.heart = heart;
		this.DEFAULT_SPEED = speed;
		this.speed = speed;
	}

	public void reduceHeart(int attackCard) {
		heart -= attackCard;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = Math.max(0, attackDamage);
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = Math.max(0, attackRange);
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = Math.max(0, heart);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = Math.max(0, speed);
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

	public int getDefaultAttackDamage() {
		return DEFAULT_ATTACK_DAMAGE;
	}

	public int getDefaultAttackRange() {
		return DEFAULT_ATTACK_RANGE;
	}

	public int getDefaultHeart() {
		return DEFAULT_HEART;
	}

	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

}
