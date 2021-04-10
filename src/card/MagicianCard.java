package card;

import logic.Direction;
import logic.GameController;
import trick.Trick;

public class MagicianCard extends Card implements Moveable, Trickable {
	private int attackDamage;
	private int attackRange;
	private int heart;
	private int speed;
	private int row;
	private int column;
	private Trick trick;

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

	public void setPlayingSide(Direction playingSide) {
		this.playingSide = playingSide;
		trick.setPlayingSide(playingSide);
	}

	public void activateTrick() {
		trick.activate();
	}

	public Trick getTrick() {
		return trick;
	}

	public void setTrick(Trick trick) {
		this.trick = trick;
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
