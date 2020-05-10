package com.yj.tank.collider;

import java.util.Random;

import com.yj.tank.constant.Dir;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.SmallTank;
import com.yj.tank.model.Wall;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-12:55
 **/
public class WallTankCollider implements Collider {
	Random random=new Random();
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof Wall && (gameProp2 instanceof EnemyTank || gameProp2 instanceof GamersTank)) {
			Wall wall=(Wall)gameProp1;
			SmallTank smallTank=(SmallTank)gameProp2;
			if (wall.getRectangle().intersects(smallTank.getRectangle())) {
				changeDir(wall, smallTank);
				return true;
			}
		}else if (gameProp2 instanceof Wall && (gameProp1 instanceof EnemyTank || gameProp1 instanceof GamersTank)){
			SmallTank smallTank=(SmallTank)gameProp1;
			Wall wall=(Wall)gameProp2;
			if (smallTank.getRectangle().intersects(wall.getRectangle())) {
				changeDir(wall, smallTank);
				return true;
			}
		}
		return false;
	}

	private void changeDir(Wall wall,SmallTank smallTank) {
		smallTank.setX(smallTank.getPreX());
		smallTank.setY(smallTank.getPreY());
		//enemyTank1.setX(enemyTank1.getPreX());
		//enemyTank1.setY(enemyTank1.getPreY());
		smallTank.setDir(Dir.values()[random.nextInt(3)]);
		//smallTank.setDir(Dir.values()[random.nextInt(3)]);
	}
}
