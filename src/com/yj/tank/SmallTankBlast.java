package com.yj.tank;

import com.yj.tank.model.GameProp;
import com.yj.tank.model.SmallTankExplode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-18:53
 **/
public class SmallTankBlast implements BlastStrategy {

	private GameProp gameProp1;

	private GameProp gameProp;

	public SmallTankBlast() {

	}

	public SmallTankBlast(GameProp gameProp, GameProp gameProp1) {
		this.gameProp1=gameProp1;
		this.gameProp=gameProp;
	}

	@Override
	public void execute(GameProp gameProp, GameProp gameProp1) {
		GameModelManager.getInstance().addGameProp(new SmallTankExplode(gameProp1.getX(),gameProp1.getY()));
	}
}
