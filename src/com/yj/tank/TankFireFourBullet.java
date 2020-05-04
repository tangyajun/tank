package com.yj.tank;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.Bullet;
import com.yj.tank.domain.Tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-12:00
 **/
public class TankFireFourBullet implements Fire {
	@Override
	public void fire(Tank tank) {
		int x=tank.getX()+(Tank.WIDTH/2)-(Bullet.WIDTH/2);
		int y=tank.getY()+(Tank.HEIGHT/2)-(Bullet.HEIGHT/2);
		Dir[] dirs=Dir.values();
		for (int i=0;i<dirs.length;i++) {
			tank.getTankFrame().getBullets().add(new Bullet(x,y, dirs[i], tank.getGroup(), tank.getTankFrame()));
		}
		if (tank.getGroup()== Group.GOOD) {
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
		}
	}
}
