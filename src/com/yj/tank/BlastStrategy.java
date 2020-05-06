package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;

/**
 *  碰撞爆炸策略
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-18:45
 **/
public interface BlastStrategy {
	/**
	 * 两个道具碰撞的爆炸效果
	 * @param gameProp
	 * @param gameProp1
	 * @param gameModelManager
	 */
	void execute(GameProp gameProp, GameProp gameProp1,GameModelManager gameModelManager);
}
