package com.yj.tank;

import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.PlaneBullet;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-20:34
 **/
public class PlaneFireBulletStrategy implements FireBulletStrategy {
	@Override
	public void execute(AbstractMilitaryWeapon abstractMilitaryWeapon,int x,int y) {
		AbstractBullet abstractBullet=new PlaneBullet(x,y,
				PlaneBullet.WIDTH,PlaneBullet.HEIGHT,
				abstractMilitaryWeapon.getDir(),PlaneBullet.SPEED,abstractMilitaryWeapon.getGameModelManager(),
				abstractMilitaryWeapon.getGroup(),
				null,new TankBlast());
		//abstractMilitaryWeapon.getGameModelManager().getBullets().add(abstractBullet);
		abstractMilitaryWeapon.getGameModelManager().addGameProp(abstractBullet);
	}
}
