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
	private AbstractBullet abstractBullet;
	private GameModelManager gameModelManager;
	private GameProp gameProp;

	public PlaneBlast() {

	}

	public PlaneBlast(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		this.abstractBullet=abstractBullet;
		this.gameModelManager=gameModelManager;
		this.gameProp=gameProp;
	}

	@Override
	public void execute(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		GameModelManager.getInstance().getExplodes().add(new PlaneExplode(abstractBullet.getX(),abstractBullet.getY(),PlaneExplode.WIDTH,PlaneExplode.HEIGHT,GameModelManager.getInstance()));
	}
}
