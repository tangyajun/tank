package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.SmallTank;
import com.yj.tank.model.SmallTankBullet;
import com.yj.tank.model.SmallTankExplode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-11:21
 **/
public class SmallTankFamilyFactory extends AbstractWeaponFactory {
	private static SmallTankFamilyFactory INSTANCE=new SmallTankFamilyFactory();
	private SmallTankFamilyFactory() {

	}

	public static SmallTankFamilyFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public AbstractMilitaryWeapon createWeapon(int x, int y, Dir dir, GameModelManager gameModelManager, Group group) {
		return new SmallTank(x,y,dir,gameModelManager,group);
	}

	@Override
	public AbstractBullet createBullet(int x, int y, int width, int height, Dir dir, int speed, GameModelManager gameModelManager, Group group, Image image) {
		return new SmallTankBullet(x,y,dir,group,gameModelManager);
	}

	@Override
	public AbstractExplode createExplode(int x, int y, int width, int height, GameModelManager gameModelManager) {
		return new SmallTankExplode(x,y,gameModelManager);
	}
}
