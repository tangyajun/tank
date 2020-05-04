package com.yj.tank.domain;

import java.awt.Image;

import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:43
 **/
public class PlaneBullet extends AbstractBullet {
	/**
	 * 子弹的速度
	 */
	public static final int SPEED=20;

	/**
	 * 子弹宽度
	 */
	public static final int WIDTH= ResourceManager.bulletUpImage.getWidth();

	/**
	 * 子弹高度
	 */
	public static final int HEIGHT=ResourceManager.bulletUpImage.getHeight();

	public PlaneBullet(int x,int y, Dir dir,int speed, TankFrame tankFrame,
			Group group, Image image) {
		this(x,y,WIDTH,HEIGHT,dir,speed,tankFrame,group,image);
	}

	public PlaneBullet(int x,int y,int width,int height, Dir dir,int speed, TankFrame tankFrame,
			Group group, Image image) {
		super(x,y,width,height,dir,speed,tankFrame,group,image);
	}
}
