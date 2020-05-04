package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.AbstractBullet;
import com.yj.tank.domain.AbstractExplode;
import com.yj.tank.domain.AbstractMilitaryWeapon;
import com.yj.tank.domain.Explode;
import com.yj.tank.domain.Plane;
import com.yj.tank.domain.PlaneBullet;
import com.yj.tank.domain.PlaneExplode;
import com.yj.tank.domain.Tank;
import com.yj.tank.domain.TankBullet;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author yang
 *  @create 2020-05-04-20:55
 **/
public class TankFamilyFactory implements AbstractWeaponFamilyFactory {
	@Override
	public AbstractMilitaryWeapon createWeapon(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
		return new Tank(x,y,dir,tankFrame,group);
	}

	@Override
	public AbstractBullet createBullet(int x, int y, int width, int height, Dir dir, int speed, TankFrame tankFrame, Group group, Image image) {
		return new TankBullet(x,y,dir,group,tankFrame);
	}

	@Override
	public AbstractExplode createExplode(int x, int y,int width,int height, TankFrame tankFrame) {
		return new Explode(x,y,tankFrame);
	}
}
