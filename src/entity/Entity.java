package entity;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	private double x,y;
	private int z;
	private boolean visible;
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

}
