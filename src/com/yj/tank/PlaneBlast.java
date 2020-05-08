package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.PlaneExplode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-19:10
 **/
public class PlaneBlast implements BlastStrategy {
	private GameProp gameProp1;
	private GameModelManager gameModelManager;
	private GameProp gameProp;

	public PlaneBlast() {

	}

	public PlaneBlast(GameProp gameProp, GameProp gameProp1,GameModelManager gameModelManager) {
		this.gameProp1=gameProp1;
		this.gameModelManager=gameModelManager;
		this.gameProp=gameProp;
	}

	@Override
	public void execute(GameProp gameProp, GameProp gameProp1,GameModelManager gameModelManager) {
		gameModelManager.addGameProp(new PlaneExplode(gameProp1.getX(),gameProp1.getY(),PlaneExplode.WIDTH,PlaneExplode.HEIGHT,GameModelManager.getInstance()));
	}
}
