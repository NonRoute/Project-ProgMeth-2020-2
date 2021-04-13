package card;

import logic.Direction;

public interface Movable { //TODO change to abstract class

//	void move();
	
	public Direction getPlayingSide();
	public int getAttackDamage();
	public void setAttackDamage(int attackDamage);
	public int getAttackRange();
	public void setAttackRange(int attackRange);
	public int getHeart();
	public void setHeart(int heart);
	public int getSpeed();
	public void setSpeed(int speed);
	public int getCost();
	public void setCost(int cost);
	public int getRow();
	public void setRow(int row);
	public int getColumn();
	public void setColumn(int column);

}
