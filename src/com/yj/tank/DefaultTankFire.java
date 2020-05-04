package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.domain.Bullet;
import com.yj.tank.domain.Tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-11:57
 **/
public class DefaultTankFire implements Fire {

	@Override
	public void fire(Tank tank) {
		int x=tank.getX()+(Tank.WIDTH/2)-(Bullet.WIDTH/2);
		int y=tank.getY()+(Tank.HEIGHT/2)-(Bullet.HEIGHT/2);
		tank.getTankFrame().getBullets().add(new Bullet(x,y, tank.getDir(), tank.getGroup(), tank.getTankFrame()));
		if (tank.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
