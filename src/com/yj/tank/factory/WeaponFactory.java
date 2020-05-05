package com.yj.tank.factory;

import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-18:37
 **/
public interface WeaponFactory<T> {
	/**
	 * 创建
	 * @param x
	 * @param y
	 * @param dir
	 * @param gameModelManager
	 * @param group
	 * @return
	 */
	public abstract T createWeapon(int x,int y,Dir dir, GameModelManager gameModelManager, Group group);

	/**
	 * 创建武器
	 * @param num
	 * @param gameModelManager
	 * @param group
	 * @param distance
	 * @param dir
	 * @return
	 */
	public abstract List<T> createWeapons(int num, GameModelManager gameModelManager, Group group, final int distance, Dir dir);

	/**
	 * 创建武器
	 * @param num
	 * @param gameModelManager
	 * @param group
	 * @param distance
	 * @param dir
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract List<T> createWeapons(int num,GameModelManager gameModelManager,Group group, final int distance,Dir dir,int x,int y);
}
