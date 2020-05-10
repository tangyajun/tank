package com.yj.tank.collider;

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
	 * @param gameProp1 游戏道具
	 * @param gameProp2 游戏道具
	 * @return 碰撞检测器成功返回true,碰撞检测失败返回false
	 */
	boolean collide(GameProp gameProp1,GameProp gameProp2);
}
