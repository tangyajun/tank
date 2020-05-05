package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.TankBullet;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-20:46
 **/
public class TankFireBulletStrategy implements FireBulletStrategy {
	@Override
	public void execute(AbstractMilitaryWeapon abstractMilitaryWeapon,int x,int y) {
		AbstractBullet abstractBullet=new TankBullet(x,y,
				abstractMilitaryWeapon.getDir(), abstractMilitaryWeapon.getGroup(),abstractMilitaryWeapon.getGameModelManager(),
				null,new TankBlast());
		//abstractMilitaryWeapon.getGameModelManager().getBullets().add(abstractBullet);
		abstractMilitaryWeapon.getGameModelManager().addGameProp(abstractBullet);
		if (abstractMilitaryWeapon.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
