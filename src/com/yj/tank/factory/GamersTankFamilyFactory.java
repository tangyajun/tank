package com.yj.tank.factory;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.SmallTankFireBulletStrategy;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.SmallTankBullet;
import com.yj.tank.model.SmallTankExplode;

/**
 * 玩家坦克抽象工厂
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-18:38
 **/
public class GamersTankFamilyFactory extends AbstractWeaponFactory {
	private static GamersTankFamilyFactory INSTANCE=new GamersTankFamilyFactory();

	private GamersTankFamilyFactory() {

	}

	public static GamersTankFamilyFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public AbstractMilitaryWeapon createWeapon(int x, int y, Dir dir, GameModelManager gameModelManager, Group group) {
		return new GamersTank(x,y,dir,gameModelManager,group,new SmallTankFireBulletStrategy());
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
