package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-18:45
 **/
public interface BlastStrategy {
	/**
	 *
	 * @param
	 * @param
	 * @param
	 */
	void execute(GameProp gameProp, AbstractBullet abstractBullet,GameModelManager gameModelManager);
}
