package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.SmallTank;
import com.yj.tank.model.SmallTankExplode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-18:53
 **/
public class SmallTankBlast implements BlastStrategy {

	private AbstractBullet abstractBullet;
	private GameModelManager gameModelManager;
	private GameProp gameProp;

	public SmallTankBlast() {

	}

	public SmallTankBlast(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		this.abstractBullet=abstractBullet;
		this.gameModelManager=gameModelManager;
		this.gameProp=gameProp;
	}

	@Override
	public void execute(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		gameModelManager.getExplodes().add(new SmallTankExplode(abstractBullet.getX(),abstractBullet.getY(),gameModelManager));
	}
}
