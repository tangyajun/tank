package com.yj.tank.collider;

import com.yj.tank.collider.Collider;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;

/**
 * 玩家坦克和敌军坦克碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-16:29
 **/
public class GamersTankEnemyTankCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof GamersTank && gameProp2 instanceof EnemyTank) {
			GamersTank gamersTank=(GamersTank)gameProp1;
			EnemyTank enemyTank=(EnemyTank)gameProp2;
			return check(gamersTank,enemyTank);
		}else if (gameProp1 instanceof EnemyTank && gameProp2 instanceof GamersTank) {
			EnemyTank enemyTank=(EnemyTank)gameProp1;
			GamersTank gamersTank=(GamersTank)gameProp2;
			return check(gamersTank,enemyTank);
		}else {
			return false;
		}
	}

	private boolean check(GamersTank gamersTank, EnemyTank enemyTank) {
		if (gamersTank.getRectangle().intersects(enemyTank.getRectangle())) {
			// 坦克爆炸策略
			if (enemyTank.getBlastStrategy()!= null) {
				enemyTank.getBlastStrategy().execute(gamersTank, enemyTank, enemyTank.getGameModelManager());
			}
			enemyTank.die();
			gamersTank.die();
			return true;
		}
		return false;
	}
}
