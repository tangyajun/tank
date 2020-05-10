package com.yj.tank.collider;

import com.yj.tank.model.GameProp;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-10:11
 **/
public class WallBulletCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		return false;
	}
}
