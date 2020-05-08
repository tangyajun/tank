package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.SmallTankBullet;
import com.yj.tank.thread.ThreadConfig;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-20:48
 **/
public class SmallTankFireBulletStrategy implements FireBulletStrategy {

	@Override
	public void execute(AbstractMilitaryWeapon abstractMilitaryWeapon,int x,int y) {
		AbstractBullet abstractBullet=new SmallTankBullet(x,y,abstractMilitaryWeapon.getDir(),
				abstractMilitaryWeapon.getGroup(),abstractMilitaryWeapon.getGameModelManager(),null,new SmallTankBlast());
		//abstractMilitaryWeapon.getGameModelManager().getBullets().add(abstractBullet);
		abstractMilitaryWeapon.getGameModelManager().addGameProp(abstractBullet);
		/*if (abstractMilitaryWeapon.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/fire.wav").play()).start();
		}*/
		if (abstractMilitaryWeapon.getGroup()== Group.GOOD) {
			ThreadConfig.executorService.execute(() -> new Audio("audio/fire.wav").play());
		}
	}
}
