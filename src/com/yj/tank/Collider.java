package com.yj.tank;

import com.yj.tank.model.GameProp;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-21:18
 **/
public interface Collider {
	/**
	 *  游戏道具碰撞
	 * @param gameProp1
	 * @param gameProp2
	 */
	void collide(GameProp gameProp1,GameProp gameProp2);
}
