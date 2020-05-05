package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
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
		//AbstractBullet abstractBullet=null;
		int x=weapon.getX()+(Tank.WIDTH/2)-(TankBullet.WIDTH/2);
		int y=weapon.getY()+(Tank.HEIGHT/2)-(TankBullet.HEIGHT/2);
		weapon.getFireBulletStrategy().execute(weapon,x,y);
		/*if (weapon instanceof Plane) {
			abstractBullet=new PlaneBullet(x,y,PlaneBullet.WIDTH,PlaneBullet.HEIGHT,
					weapon.getDir(),PlaneBullet.SPEED,weapon.getGameModelManager(),weapon.getGroup(),
					null,new TankBlast());
			weapon.getGameModelManager().getBullets().add(abstractBullet);
		}else if (weapon instanceof Tank) {
			abstractBullet=new TankBullet(x,y,weapon.getDir(),
					weapon.getGroup(),weapon.getGameModelManager(),null,new TankBlast());
			weapon.getGameModelManager().getBullets().add(abstractBullet);
			if (weapon.getGroup()== Group.GOOD) {
				new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
			}
		}else if (weapon instanceof SmallTank) {
			abstractBullet=new SmallTankBullet(x,y,weapon.getDir(),
					weapon.getGroup(),weapon.getGameModelManager(),null,new SmallTankBlast());
			weapon.getGameModelManager().getBullets().add(abstractBullet);
			if (weapon.getGroup()== Group.GOOD) {
				new Thread(() -> new Audio("audio/fire.wav").play()).start();
			}
		}*/
		//weapon.getGameModelManager().addGameProp(abstractBullet);
	}
}
