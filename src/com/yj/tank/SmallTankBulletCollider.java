package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.SmallTank;
import com.yj.tank.model.SmallTankBullet;

/**
 *  坦克和子弹碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-21:21
 **/
public class SmallTankBulletCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof SmallTank && gameProp2 instanceof SmallTankBullet) {
			SmallTank smallTank=(SmallTank) gameProp1;
			SmallTankBullet smallTankBullet=(SmallTankBullet)gameProp2;
			return check(smallTankBullet,smallTank);
		}else if (gameProp1 instanceof  SmallTankBullet && gameProp2 instanceof SmallTank) {
			SmallTankBullet smallTankBullet=(SmallTankBullet) gameProp1;
			SmallTank smallTank=(SmallTank)gameProp2;
			return check(smallTankBullet,smallTank);
		}else {
			return false;
		}
	}

	private boolean check(SmallTankBullet smallTankBullet,SmallTank smallTank) {
		/*if (smallTank.getGroup()==smallTankBullet.getGroup()) {
			return;
		}*/
		if (smallTank.getRectangle().intersects(smallTankBullet.getRectangle())) {
			if (smallTank.getGroup() == Group.BAD && smallTankBullet.getGroup() == Group.GOOD) {
				smallTankBullet.die();
				smallTank.die();
			}else if (smallTank.getGroup() == Group.GOOD && smallTankBullet.getGroup() == Group.BAD) {
				smallTankBullet.die();
				smallTank.die();
			}
		}
		smallTankBullet.getBlastStrategy().execute(smallTank,smallTankBullet,smallTankBullet.getGameModelManager());
		return true;
	}
}
