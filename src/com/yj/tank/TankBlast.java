package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.Explode;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.SmallTankExplode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-19:08
 **/
public class TankBlast implements BlastStrategy {

	private AbstractBullet abstractBullet;
	private GameModelManager gameModelManager;
	private GameProp gameProp;

	public TankBlast() {

	}

	public TankBlast(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		this.abstractBullet=abstractBullet;
		this.gameModelManager=gameModelManager;
		this.gameProp=gameProp;
	}

	@Override
	public void execute(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager) {
		gameModelManager.getExplodes().add(new Explode(abstractBullet.getX(),abstractBullet.getY(),gameModelManager));
	}
}
