package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 游戏道具
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-7:04
 **/
public abstract class GameProp {
	/**
	 * 横坐标
	 */
	protected int x;

	/**
	 * 纵坐标
	 */
	protected int y;

	/**
	 * 宽度
	 */
	protected int width;

	/**
	 * 高度
	 */
	protected int height;

	/**
	 * 范围
	 */
	protected Rectangle rectangle=new Rectangle();

	public GameProp(int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=this.width;
		this.rectangle.height=this.height;
	}

	/**
	 * 绘制游戏道具
	 * @param graphics
	 */
	public abstract void paint(Graphics graphics);

}
