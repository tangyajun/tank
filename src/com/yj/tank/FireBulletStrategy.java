package com.yj.tank;

import com.yj.tank.model.AbstractMilitaryWeapon;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-20:31
 **/
public interface FireBulletStrategy {

	void execute(AbstractMilitaryWeapon abstractMilitaryWeapon,int x,int y);
}
