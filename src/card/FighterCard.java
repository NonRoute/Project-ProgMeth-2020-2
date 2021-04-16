package card;

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

	public FighterCard(String deckName, int cost, int attackDamage, int attackRange,
			int heart, int speed) {
		super(deckName, cost);
		this.DEFAULT_ATTACK_DAMAGE = attackDamage;
		this.attackDamage = attackDamage;
		this.DEFAULT_ATTACK_RANGE = attackRange;
		this.attackRange = attackRange;
		this.DEFAULT_HEART = heart;
		this.heart = heart;
		this.DEFAULT_SPEED = speed;
		this.speed = speed;
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

	public int getDefaultHeart() {
		return DEFAULT_HEART;
	}

	public int getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	public int getHeart() {
		return heart;
	}

	public int getRow() {
		return row;
	}

	public int getSpeed() {
		return speed;
	}

	public void reduceHeart(int attackCard) {
		heart -= attackCard;
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

	public void setHeart(int heart) {
		this.heart = Math.max(0, heart);
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setSpeed(int speed) {
		this.speed = Math.max(0, speed);
	}

}
