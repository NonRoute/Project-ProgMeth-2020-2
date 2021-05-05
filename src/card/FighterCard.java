package card;

import sharedObject.RenderableHolder;

public class FighterCard extends Card {
	protected final int DEFAULT_ATTACK_DAMAGE;
	protected final int DEFAULT_ATTACK_RANGE;
	protected final int DEFAULT_HEALTH;
	protected final int DEFAULT_SPEED;
	protected int attackDamage;
	protected int attackRange;
	protected int health;
	protected int speed;
	protected int row;
	protected int column;

	public FighterCard(String deckName, int cost, int attackDamage, int attackRange, int health, int speed) {
		super(deckName, cost);
		this.description = "";
		this.DEFAULT_ATTACK_DAMAGE = attackDamage;
		this.attackDamage = attackDamage;
		this.DEFAULT_ATTACK_RANGE = attackRange;
		this.attackRange = attackRange;
		this.DEFAULT_HEALTH = health;
		this.health = health;
		this.DEFAULT_SPEED = speed;
		this.speed = speed;
		switch (deckName) { // set Fighter image
		case "Angel":
			leftPlayingSideImage = RenderableHolder.angelFighterL;
			rightPlayingSideImage = RenderableHolder.angelFighterR;
			break;
		case "Devil":
			leftPlayingSideImage = RenderableHolder.devilFighterL;
			rightPlayingSideImage = RenderableHolder.devilFighterR;
			break;
		}
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getColumn() {
		return column;
	}

	public int getDefaultAttackDamage() {
		return DEFAULT_ATTACK_DAMAGE;
	}

	public int getDefaultAttackRange() {
		return DEFAULT_ATTACK_RANGE;
	}

	public int getDefaultHealth() {
		return DEFAULT_HEALTH;
	}

	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	public int getHealth() {
		return health;
	}

	public int getRow() {
		return row;
	}

	public int getSpeed() {
		return speed;
	}

	public void reduceHealth(int attackCard) {
		health = Math.max(health - attackCard, 0);
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = Math.max(0, attackDamage);
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = Math.max(0, attackRange);
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setHealth(int health) {
		this.health = Math.max(0, health);
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setSpeed(int speed) {
		this.speed = Math.max(0, speed);
	}

}
