package com.yj.tank.collider;

import com.yj.tank.collider.Collider;
import com.yj.tank.constant.Group;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.SmallTankBullet;

/**
 *  玩家坦克和子弹碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-16:15
 **/
public class GamersTankBulletCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof GamersTank && gameProp2 instanceof SmallTankBullet) {
			GamersTank gamersTank=(GamersTank)gameProp1;
			SmallTankBullet bullet=(SmallTankBullet)gameProp2;
			return check(gamersTank,bullet);
		}else if (gameProp1 instanceof SmallTankBullet && gameProp2 instanceof GamersTank) {
			GamersTank gamersTank=(GamersTank)gameProp2;
			SmallTankBullet bullet=(SmallTankBullet)gameProp1;
			return check(gamersTank,bullet);
		}else {
			return false;
		}
	}

	private boolean check(GamersTank gamersTank,SmallTankBullet bullet) {
		if (gamersTank.getRectangle().intersects(bullet.getRectangle())) {
			if (gamersTank.getGroup() == Group.BAD && bullet.getGroup() == Group.GOOD) {
				startBlast(gamersTank,bullet);
				bullet.die();
				gamersTank.die();
				return true;
			}else if (gamersTank.getGroup() == Group.GOOD && bullet.getGroup() == Group.BAD) {
				startBlast(gamersTank,bullet);
				bullet.die();
				gamersTank.die();
				return true;
			}
		}
		return false;
	}

	public boolean startBlast(GamersTank gamersTank,SmallTankBullet bullet) {
		if (bullet.getBlastStrategy()!= null) {
			bullet.getBlastStrategy().execute(gamersTank, bullet);
		}
		return true;
	}
}
