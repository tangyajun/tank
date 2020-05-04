package com.yj.tank;

import com.yj.tank.domain.AbstractMilitaryWeapon;

/**
 *
 *  @Description TO DO
 *  @author yang
 *  @create 2020-05-03-16:54
 **/
public interface MoveBehavior {
	/**
	 *移动
	 */
	void move(AbstractMilitaryWeapon abstractMilitaryWeapon);
}
