package entity.base;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected double x,y;
	protected int z;
	protected boolean visible;
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

}
