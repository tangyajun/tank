package com.yj.tank.domain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:35
 **/
public abstract class AbstractBullet {
	/**
	 * 子弹的速度
	 */
	protected int speed=20;

	/**
	 * 宽度
	 */
	protected int width;

	/**
	 * 高度
	 */
	protected int height;

	/**
	 * 子弹的坐标
	 */
	protected int x,y;

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
	protected TankFrame tankFrame;

	/**
	 * 所属分组
	 */
	protected Group group;

	/**
	 * 子弹矩形
	 */
	protected Rectangle rectangle=new Rectangle();

	/**
	 * 子弹的图片
	 */
	protected Image image;

	/**
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param dir
	 * @param speed
	 * @param tankFrame
	 * @param group
	 * @param image
	 */
	public AbstractBullet(int x,int y,int width,int height,Dir dir,int speed,TankFrame tankFrame,
			Group group,Image image) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.dir=dir;
		this.speed=speed;
		this.tankFrame=tankFrame;
		this.group=group;
		this.image=image;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=width;
		this.rectangle.height=height;
	}

	public abstract void paint(Graphics graphics);

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

	public abstract void collideWith(AbstractMilitaryWeapon weapon);
	/**
	 * 碰撞检测
	 * @param weapon
	 */
	/*public void collideWith(AbstractMilitaryWeapon weapon) {
		if (this.group==weapon.getGroup()) {
			return;
		}
		if (this.rectangle.intersects(weapon.getRectangle())) {
			if (this.group == Group.BAD && weapon.getGroup() == Group.GOOD) {
				die();
				weapon.die();
				if (weapon instanceof Plane) {
					this.tankFrame.getExplodes().add(new PlaneExplode(x,y,PlaneExplode.WIDTH,PlaneExplode.HEIGHT,tankFrame));
				}else if (weapon instanceof Tank) {
					this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
				}

			}else if (this.group==Group.GOOD && weapon.getGroup()==Group.BAD) {
				die();
				weapon.die();
				if (weapon instanceof Plane) {
					this.tankFrame.getExplodes().add(new PlaneExplode(x,y,PlaneExplode.WIDTH,PlaneExplode.HEIGHT,tankFrame));
				}else if (weapon instanceof Tank) {
					this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
				}
			}
		}
	}*/

	public abstract void die(); /*{
		this.live=false;
	}*/

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

	public TankFrame getTankFrame() {
		return tankFrame;
	}

	public void setTankFrame(TankFrame tankFrame) {
		this.tankFrame = tankFrame;
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
