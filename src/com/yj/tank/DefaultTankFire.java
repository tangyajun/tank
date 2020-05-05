package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.TankBullet;
import com.yj.tank.model.Plane;
import com.yj.tank.model.PlaneBullet;
import com.yj.tank.model.Tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-11:57
 **/
public class DefaultTankFire implements Fire {

	@Override
	public void fire(AbstractMilitaryWeapon tank) {
		int x=tank.getX()+(Tank.WIDTH/2)-(TankBullet.WIDTH/2);
		int y=tank.getY()+(Tank.HEIGHT/2)-(TankBullet.HEIGHT/2);
		if (tank instanceof Plane) {
			tank.getGameModelManager().getBullets().add(new PlaneBullet(x,y,PlaneBullet.WIDTH,PlaneBullet.HEIGHT, tank.getDir(),PlaneBullet.SPEED,tank.getGameModelManager(),tank.getGroup(), null));
		}else if (tank instanceof Tank) {
			tank.getGameModelManager().getBullets().add(new TankBullet(x,y,tank.getDir(),tank.getGroup(),tank.getGameModelManager(),null));
		}
		if (tank.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
