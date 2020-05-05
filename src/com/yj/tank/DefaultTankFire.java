package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.SmallTank;
import com.yj.tank.model.SmallTankBullet;
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
	public void fire(AbstractMilitaryWeapon weapon) {
		int x=weapon.getX()+(Tank.WIDTH/2)-(TankBullet.WIDTH/2);
		int y=weapon.getY()+(Tank.HEIGHT/2)-(TankBullet.HEIGHT/2);
		if (weapon instanceof Plane) {
			weapon.getGameModelManager().getBullets().add(new PlaneBullet(x,y,PlaneBullet.WIDTH,PlaneBullet.HEIGHT, weapon.getDir(),PlaneBullet.SPEED,weapon.getGameModelManager(),weapon.getGroup(), null));
		}else if (weapon instanceof Tank) {
			weapon.getGameModelManager().getBullets().add(new TankBullet(x,y,weapon.getDir(),weapon.getGroup(),weapon.getGameModelManager(),null));
			if (weapon.getGroup()== Group.GOOD) {
				new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
			}
		}else if (weapon instanceof SmallTank) {
			weapon.getGameModelManager().getBullets().add(new SmallTankBullet(x,y,weapon.getDir(),weapon.getGroup(),weapon.getGameModelManager(),null));
			if (weapon.getGroup()== Group.GOOD) {
				new Thread(() -> new Audio("audio/fire.wav").play()).start();
			}
		}

	}
}
