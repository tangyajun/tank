package com.yj.tank.model;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.EnemySmallTankFactory;
import com.yj.tank.factory.GamersSmallTankFactory;

/**
 *  第一关
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-23:57
 **/
public class FirstGameLevel extends AbstractGameLevel {

	public static FirstGameLevel INSTANCE=new FirstGameLevel();

	private FirstGameLevel() {

	}

	public static FirstGameLevel getInstance() {
		return INSTANCE;
	}

	@Override
	public void start() {
		// 初始化敌军坦克
		enemyTanks.addAll(EnemySmallTankFactory.getInstance().createWeapons(ENEMY_TANKS_NUM,
				Group.BAD,GameModelManager.ENEMY_TANK_DISTANCE, Dir.DOWN));
		GameModelManager.getInstance().addGameProps(enemyTanks);
		//  初始化玩家坦克
		for (int i=0;i<GameModelManager.LIFE_NUM;i++) {
			gamersTanks.add(GamersSmallTankFactory.getInstance().createWeapon(100,400,Dir.DOWN,Group.GOOD));
		}
		GameModelManager.getInstance().addGameProps(gamersTanks);
	}
}
