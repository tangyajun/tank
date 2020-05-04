package com.yj.tank;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.TankBullet;
import com.yj.tank.model.Tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-12:00
 **/
public class TankFireFourBullet implements Fire {
	@Override
	public void fire(AbstractMilitaryWeapon tank) {
		int x=tank.getX()+(Tank.WIDTH/2)-(TankBullet.WIDTH/2);
		int y=tank.getY()+(Tank.HEIGHT/2)-(TankBullet.HEIGHT/2);
		Dir[] dirs=Dir.values();
		for (int i=0;i<dirs.length;i++) {
			tank.getTankFrame().getBullets().add(new TankBullet(x,y, dirs[i], tank.getGroup(), tank.getTankFrame()));
		}
		if (tank.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
