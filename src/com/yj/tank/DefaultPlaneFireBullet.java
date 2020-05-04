package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.domain.AbstractMilitaryWeapon;
import com.yj.tank.domain.TankBullet;
import com.yj.tank.domain.Plane;
import com.yj.tank.domain.PlaneBullet;
import com.yj.tank.domain.Tank;

/**
 *
 *  @Description TO DO
 *  @author yang
 *  @create 2020-05-04-19:43
 **/
public class DefaultPlaneFireBullet implements Fire {
	@Override
	public void fire(AbstractMilitaryWeapon tank) {
		int x=tank.getX()+(Tank.WIDTH/2)-(TankBullet.WIDTH/2);
		int y=tank.getY()+(Tank.HEIGHT/2)-(TankBullet.HEIGHT/2);
		if (tank instanceof Plane) {
			tank.getTankFrame().getBullets().add(new PlaneBullet(x,y,PlaneBullet.WIDTH,PlaneBullet.HEIGHT, tank.getDir(),PlaneBullet.SPEED,tank.getTankFrame(),tank.getGroup(), null));
		}
		if (tank.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
