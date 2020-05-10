package com.yj.tank.collider;

import com.yj.tank.collider.Collider;
import com.yj.tank.constant.Group;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.SmallTankBullet;

/**
 *  敌军坦克和子弹碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-08-9:00
 **/
public class EnemyTankBulletCollider implements Collider {

	public EnemyTankBulletCollider() {

	}

	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof EnemyTank && gameProp2 instanceof SmallTankBullet) {
			EnemyTank enemyTank=(EnemyTank)gameProp1;
			SmallTankBullet bullet=(SmallTankBullet)gameProp2;
			return check(enemyTank,bullet);
		}else if (gameProp1 instanceof SmallTankBullet && gameProp2 instanceof EnemyTank) {
			EnemyTank enemyTank=(EnemyTank)gameProp2;
			SmallTankBullet bullet=(SmallTankBullet)gameProp1;
			return check(enemyTank,bullet);
		}else {
			return false;
		}
	}

	private boolean check(EnemyTank enemyTank, SmallTankBullet bullet) {
		if (enemyTank.getRectangle().intersects(bullet.getRectangle())) {
			if (enemyTank.getGroup() == Group.BAD && bullet.getGroup() == Group.GOOD) {
				startBlast(enemyTank,bullet);
				bullet.die();
				enemyTank.die();
				return true;
			}else if (enemyTank.getGroup() == Group.GOOD && bullet.getGroup() == Group.BAD) {
				startBlast(enemyTank,bullet);
				bullet.die();
				enemyTank.die();
				return true;
			}
		}
		return false;
	}

	public boolean startBlast(EnemyTank enemyTank, SmallTankBullet bullet) {
		if (bullet.getBlastStrategy()!=null) {
			bullet.getBlastStrategy().execute(enemyTank, bullet, bullet.getGameModelManager());
		}
		return true;
	}
}
