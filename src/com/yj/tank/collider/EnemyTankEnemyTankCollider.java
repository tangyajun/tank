package com.yj.tank.collider;

import java.util.Random;

import com.yj.tank.collider.Collider;
import com.yj.tank.constant.Dir;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GameProp;

/**
 *  敌军坦克和敌军坦克碰撞
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-0:31
 **/
public class EnemyTankEnemyTankCollider implements Collider {
	Random random=new Random();
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof EnemyTank && gameProp2 instanceof EnemyTank) {
			EnemyTank enemyTank = (EnemyTank) gameProp1;
			EnemyTank enemyTank1 = (EnemyTank) gameProp2;
			if (enemyTank.getRectangle().intersects(enemyTank1.getRectangle())) {
				changeDir(enemyTank, enemyTank1);
				return true;
			}
		}
		return false;
	}

	private void changeDir(EnemyTank enemyTank,EnemyTank enemyTank1) {
		/*enemyTank.setX(enemyTank.getPreX());
		enemyTank.setY(enemyTank.getPreY());
		enemyTank1.setX(enemyTank1.getPreX());
		enemyTank1.setY(enemyTank1.getPreY());*/
		enemyTank.back();
		enemyTank1.back();
		enemyTank.setDir(Dir.values()[random.nextInt(3)]);
		enemyTank1.setDir(Dir.values()[random.nextInt(3)]);
	}
}
