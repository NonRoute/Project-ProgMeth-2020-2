package card;

import logic.Direction;

public class FighterCard extends Card {
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;
	private int row;
	private int column;
	private Direction playingSide;

	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void move() {
		switch (playingSide) {
		case LEFT:
			column++;
			//TODO if end of board -> attack controller, remove card
		case RIGHT:
			column--;
		}
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

}
