package entity;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected int x, y;
	protected int z;
	protected boolean visible;

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
