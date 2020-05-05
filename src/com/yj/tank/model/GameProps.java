package com.yj.tank.model;

import java.awt.Graphics;

/**
 * 游戏道具
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-7:04
 **/
public abstract class GameProps {
	int x;
	int y;

	public GameProps(int x,int y) {
		this.x=x;
		this.y=y;
	}

	/**
	 * 绘制游戏道具
	 * @param graphics
	 */
	public abstract void paint(Graphics graphics);

}
