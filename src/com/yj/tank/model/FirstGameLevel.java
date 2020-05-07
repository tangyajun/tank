package com.yj.tank.model;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;

/**
 *  第一关
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-23:57
 **/
public class FirstGameLevel extends AbstractGameLevel {

	public static FirstGameLevel INSTANCE=new FirstGameLevel();

	GameModelManager modelManager=GameModelManager.getInstance();

	private FirstGameLevel() {

	}

	public static FirstGameLevel getInstance() {
		return INSTANCE;
	}

	@Override
	public void start() {
		// 初始化敌军坦克
		enemyTanks.addAll(modelManager.getEnemyWeaponFactory().createWeapons(ENEMY_TANKS_NUM,modelManager,
				Group.BAD,GameModelManager.ENEMY_TANK_DISTANCE, Dir.DOWN));
		modelManager.addGameProps(enemyTanks);
		//  初始化玩家坦克
		for (int i=0;i<GameModelManager.LIFE_NUM;i++) {
			gamersTanks.add(modelManager.getGamersWeaponFactory().createWeapon(100,400,Dir.DOWN,modelManager,Group.GOOD));
		}
		modelManager.addGameProps(gamersTanks);
	}
}
