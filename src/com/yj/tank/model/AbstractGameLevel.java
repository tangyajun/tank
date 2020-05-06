package com.yj.tank.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-23:32
 **/
public abstract class AbstractGameLevel {
	/**
	 * 敌军坦克
	 */
	protected static List<EnemyTank> enemyTanks=new ArrayList<>(5);

	/**
	 * 玩家坦克
	 */
	protected static List<GamersTank> gamersTanks=new ArrayList<>(1);

	/**
	 * 敌军坦克数量
	 */
	public static final int ENEMY_TANKS_NUM=5;

	/**
	 * 玩家生命数量
	 */
	public static final int GAMERS_LIFE_NUM=1;

	/**
	 * 敌军坦克循环出现次数
	 */
	public static final int ENEMY_TANK_LOOP_NUM=3;
}
