package com.yj.tank;

import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.Windmill;

/**
 * 玩家坦克和风车相撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-17:37
 **/
public class GamersTankWindmillCollider implements Collider {
	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		if (gameProp1 instanceof GamersTank && gameProp2 instanceof Windmill) {
			GamersTank gamersTank=(GamersTank)gameProp1;
			Windmill windmill=(Windmill)gameProp2;
			return check(gamersTank,windmill);
		}else if (gameProp1 instanceof Windmill && gameProp2 instanceof GamersTank) {
			GamersTank gamersTank=(GamersTank)gameProp2;
			Windmill windmill=(Windmill)gameProp1;
			return check(gamersTank,windmill);
		}else {
			return false;
		}
	}

	private boolean check(GamersTank gamersTank,Windmill windmill) {
		if (gamersTank.getRectangle().intersects(windmill.getRectangle())) {
			windmill.die();
		}
		return true;
	}
}
