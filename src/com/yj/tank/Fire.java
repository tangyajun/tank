package com.yj.tank;

import com.yj.tank.domain.Tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-11:54
 **/
public interface Fire {
	/**
	 * 发射子弹
	 * @param tank
	 */
	void fire(Tank tank);
}
