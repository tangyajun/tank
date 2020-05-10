package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.Plane;
import com.yj.tank.model.PlaneBullet;
import com.yj.tank.model.PlaneExplode;

/**
 *  飞机抽象工厂，生产飞机、飞机的子弹、飞机爆炸
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-17:40
 **/
public class PlaneFamilyFactory extends AbstractWeaponFactory {

	@Override
	public AbstractMilitaryWeapon createWeapon(int x, int y, Dir dir, Group group) {
		return new Plane(x,y,dir,group);
	}

	@Override
	public AbstractBullet createBullet(int x, int y, int width, int height, Dir dir, int speed,  Group group, Image image) {
		return new PlaneBullet(x,y,width,height,dir,speed,group,image);
	}

	@Override
	public AbstractExplode createExplode(int x, int y,int width,int height) {
		return new PlaneExplode(x,y,width,height);
	}
}
