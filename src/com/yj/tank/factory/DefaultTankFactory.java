package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.TankFireBulletStrategy;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.Explode;
import com.yj.tank.model.Tank;
import com.yj.tank.model.TankBullet;

/**
 * 默认坦克抽象工厂
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-20:55
 **/
public class DefaultTankFactory extends AbstractWeaponFactory {

	private static DefaultTankFactory INSTANCE=new DefaultTankFactory();

	private DefaultTankFactory() {

	}

	public static DefaultTankFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public AbstractMilitaryWeapon createWeapon(int x, int y, Dir dir, GameModelManager gameModelManager, Group group) {
		return new Tank(x,y,dir,gameModelManager,group,new TankFireBulletStrategy());
	}

	@Override
	public AbstractBullet createBullet(int x, int y, int width, int height, Dir dir, int speed, GameModelManager gameModelManager, Group group, Image image) {
		return new TankBullet(x,y,dir,group,gameModelManager);
	}

	@Override
	public AbstractExplode createExplode(int x, int y,int width,int height, GameModelManager gameModelManager) {
		return new Explode(x,y,gameModelManager);
	}
}
