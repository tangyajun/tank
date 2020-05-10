package com.yj.tank.model;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.model.GameProp;

/**
 * 游戏中的宝贝
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-17:51
 **/
public abstract class Cowry extends GameProp  {
	/**
	 * 是否存活
	 */
	protected boolean live=false;

	/**
	 * 速度
	 */
	protected int speed=5;
	/**
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Cowry(int x,int y,int width,int height) {
		super(x,y,width,height);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
