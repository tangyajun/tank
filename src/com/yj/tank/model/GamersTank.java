package com.yj.tank.model;

import com.yj.tank.DefaultTankFire;
import com.yj.tank.FireBulletStrategy;
import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;

/**
 * 玩家坦克
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-16:04
 **/
public class GamersTank extends SmallTank {

	/**
	 * 坦克构造函数
	 * @param x x坐标
	 * @param y y坐标
	 * @param dir 方向
	 * @param gameModelManager
	 * @param group 分组
	 */
	public GamersTank(int x,int y, Dir dir, GameModelManager gameModelManager, Group group) {
		this(x,y,WIDTH,HEIGHT,dir,gameModelManager,group);
	}

	public GamersTank(int x,int y, Dir dir, GameModelManager gameModelManager, Group group, FireBulletStrategy fireBulletStrategy) {
		this(x,y,WIDTH,HEIGHT,dir,gameModelManager,group,fireBulletStrategy);
	}

	/**
	 * 坦克构造函数
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param dir 方向
	 * @param gameModelManager
	 * @param group 分组
	 */
	public GamersTank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group) {
		this(x,y,width,height,dir,gameModelManager,group,null);
	}

	public GamersTank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group,
			FireBulletStrategy fireBulletStrategy) {
		super(x,y,width,height,dir,gameModelManager,group,new DefaultTankFire(),fireBulletStrategy);
	}
}
