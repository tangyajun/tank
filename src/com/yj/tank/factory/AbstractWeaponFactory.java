package com.yj.tank.factory;

import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;

/**
 *  军事武器抽象工厂
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:31
 **/
public abstract class AbstractWeaponFactory {
	/**
	 * 创建武器
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param dir 方向
	 * @param
	 * @param group 所属分组
	 * @return 返回军事武器
	 */
	public abstract AbstractMilitaryWeapon createWeapon(int x,int y, Dir dir,  Group group);

	/**
	 * 创建子弹
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param dir 方向
	 * @param speed 速度
	 * @param
	 * @param group 所属分组
	 * @param image 图片
	 * @return 返回子弹
	 */
	public abstract AbstractBullet createBullet(int x,int y,int width,int height,Dir dir,int speed,
			Group group, Image image);

	/**
	 * 创建爆炸
	 * @param x 横坐标坐标
	 * @param y 纵坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param
	 * @return 返回爆炸
	 */
	public abstract AbstractExplode createExplode(int x,int y,int width,int height);
}
