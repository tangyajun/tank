package com.yj.tank.model;

import java.awt.Image;
import java.awt.Rectangle;

import com.yj.tank.BlastStrategy;
import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:35
 **/
public abstract class AbstractBullet extends GameProp {
	/**
	 * 子弹的速度
	 */
	protected int speed=20;

	/**
	 * 子弹的方向
	 */
	protected Dir dir;

	/**
	 * 子弹是否存活
	 */
	protected boolean live=true;

	/**
	 *
	 */
	protected GameModelManager gameModelManager;

	/**
	 * 所属分组
	 */
	protected Group group;

	/**
	 * 子弹矩形
	 */
	//protected Rectangle rectangle=new Rectangle();

	protected BlastStrategy blastStrategy;

	/**
	 * 子弹的图片
	 */
	protected Image image;

	/**
	 * 构造方法
	 * @param x x 坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param dir 方向
	 * @param speed 速度
	 * @param gameModelManager
	 * @param group 分组
	 * @param image
	 */
	public AbstractBullet(int x,int y,int width,int height,Dir dir,int speed, GameModelManager gameModelManager,
			Group group,Image image) {
		super(x,y,width,height);
		this.dir=dir;
		this.speed=speed;
		this.gameModelManager=gameModelManager;
		this.group=group;
		this.image=image;
	}

	public AbstractBullet(int x,int y,int width,int height,Dir dir,int speed, GameModelManager gameModelManager,
			Group group,Image image,BlastStrategy blastStrategy) {
		super(x,y,width,height);
		this.dir=dir;
		this.speed=speed;
		this.gameModelManager=gameModelManager;
		this.group=group;
		this.image=image;
		this.blastStrategy=blastStrategy;
	}


	/*protected void move() {
		switch (dir) {
			case LEFT:
				x-=speed;
				break;
			case RIGHT:
				x+=speed;
				break;
			case UP:
				y-=speed;
				break;
			case DOWN:
				y+=speed;
				break;
			default:
				break;
		}
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) {
			live=false;
		}
	}*/

	/**
	 * 碰撞检测
	 * @param weapon
	 */
	public abstract void collideWith(AbstractMilitaryWeapon weapon);
	/**
	 * 碰撞检测
	 * @param //weapon
	 */

	public abstract void die();

	public BlastStrategy getBlastStrategy() {
		return blastStrategy;
	}

	public void setBlastStrategy(BlastStrategy blastStrategy) {
		this.blastStrategy = blastStrategy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public GameModelManager getGameModelManager() {
		return gameModelManager;
	}

	public void setGameModelManager(GameModelManager gameModelManager) {
		this.gameModelManager = gameModelManager;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
