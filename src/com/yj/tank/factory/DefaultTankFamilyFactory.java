package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.Explode;
import com.yj.tank.model.Tank;
import com.yj.tank.model.TankBullet;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-20:55
 **/
public class DefaultTankFamilyFactory implements AbstractWeaponFamilyFactory {

	private static DefaultTankFamilyFactory INSTRANCE=new DefaultTankFamilyFactory();

	private DefaultTankFamilyFactory() {

	}

	public static DefaultTankFamilyFactory getInstance() {
		return INSTRANCE;
	}

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
