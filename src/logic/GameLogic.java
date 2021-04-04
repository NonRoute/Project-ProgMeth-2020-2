package logic;

import java.util.ArrayList;
import java.util.List;

import entity.base.Entity;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		
	}
}
