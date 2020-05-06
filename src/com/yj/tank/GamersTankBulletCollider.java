package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;

/**
 *  玩家坦克和子弹碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-16:15
 **/
public class GamersTankBulletCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof GamersTank && gameProp2 instanceof AbstractBullet) {
			GamersTank gamersTank=(GamersTank)gameProp1;
			AbstractBullet bullet=(AbstractBullet)gameProp2;
			return check(gamersTank,bullet);
		}else if (gameProp1 instanceof AbstractBullet && gameProp2 instanceof GamersTank) {
			GamersTank gamersTank=(GamersTank)gameProp2;
			AbstractBullet bullet=(AbstractBullet)gameProp1;
			return check(gamersTank,bullet);
		}else {
			return false;
		}
	}

	private boolean check(GamersTank gamersTank,AbstractBullet bullet) {
		/*if (gamersTank.getGroup()==bullet.getGroup()) {
			return;
		}*/
		if (gamersTank.getRectangle().intersects(bullet.getRectangle())) {
			if (gamersTank.getGroup() == Group.BAD && bullet.getGroup() == Group.GOOD) {
				bullet.die();
				gamersTank.die();
			}else if (gamersTank.getGroup() == Group.GOOD && bullet.getGroup() == Group.BAD) {
				bullet.die();
				gamersTank.die();
			}
		}
		bullet.getBlastStrategy().execute(gamersTank,bullet,bullet.getGameModelManager());
		return true;
	}
}
