package card;

import logic.Direction;
import logic.GameController;

public class FighterCard extends Card implements Movable {
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;
	private int row;
	private int column;

	public FighterCard(String deckName, String name, String description, int cost, int attackDamage, int attackRange,
			int heart, int speed) {
		super(deckName, name, description, cost);
		this.attackDamage = attackDamage;
		this.attackRange = attackRange;
		this.heart = heart;
		this.speed = speed;
	}

	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void move() {
		switch (playingSide) {
		case LEFT:
			for (int i = 1; i <= speed; i++) {
				if (GameController.board.isEmpty(row, column + 1)) {
					// can move to next cell
					GameController.board.removeCardOnMap(row, column);
					column++;
					GameController.board.setCardOnMap(this, row, column);
				} else if (GameController.board.isOutOfBoard(row, column + 1)) {
					// can attack controller
					GameController.rightSideController.reduceHeart(attackDamage);
					GameController.board.removeCardOnMap(row, column);
				} else {
					// stop moving
					break;
				}
			}
		case RIGHT:
			for (int i = 1; i <= speed; i++) {
				if (GameController.board.isEmpty(row, column - 1)) {
					GameController.board.removeCardOnMap(row, column);
					column--;
					GameController.board.setCardOnMap(this, row, column);
				} else if (GameController.board.isOutOfBoard(row, column - 1)) {
					GameController.leftSideController.reduceHeart(attackDamage);
					GameController.board.removeCardOnMap(row, column);
				} else {
					break;
				}
			}
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
