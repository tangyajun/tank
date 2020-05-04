package com.yj.tank.domain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 * 子弹
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-14:00
 **/
public class Bullet {
	/**
	 * 子弹的速度
	 */
	private static final int SPEED=20;
	public static final int WIDTH= ResourceManager.bulletUpImage.getWidth();
	public static final int HEIGHT=ResourceManager.bulletUpImage.getHeight();

	/**
	 * 子弹的坐标
	 */
	private int x,y;

	/**
	 * 子弹的方向
	 */
	private Dir dir;

	/**
	 * 子弹是否存活
	 */
	boolean live=true;

	/**
	 *
	 */
	TankFrame tankFrame;

	/**
	 * 所属分组
	 */
	private Group group=Group.BAD;

	Rectangle rectangle=new Rectangle();

	/**
	 * 子弹的图片
	 */
	private Image image;

	public Bullet(int x,int y,Dir dir,Group group,TankFrame tankFrame) {
		this(tankFrame,x,y,dir,group,null);
	}

	public Bullet(TankFrame tankFrame,int x,int y,Dir dir,Group group, Image image) {
		this.tankFrame=tankFrame;
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.group=group;
		this.image=image;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=WIDTH;
		this.rectangle.height=HEIGHT;
	}

	public void paint(Graphics graphics) {
		if (!live) {
			tankFrame.getBullets().remove(this);
		}
		switch (dir) {
			case DOWN:
				if (image ==null) {
					graphics.drawImage(ResourceManager.bulletDownImage, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case UP:
				if (image == null) {
					graphics.drawImage(ResourceManager.bulletUpImage, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case LEFT:
				if (image==null) {
					graphics.drawImage(ResourceManager.bulletLeftImage, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case RIGHT:
				if (image==null) {
					graphics.drawImage(ResourceManager.bulletRightImage,x,y,null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			default:
				break;
		}
		move();
	}

	private void move() {
		switch (dir) {
			case LEFT:
				x-=SPEED;
				break;
			case RIGHT:
				x+=SPEED;
				break;
			case UP:
				y-=SPEED;
				break;
			case DOWN:
				y+=SPEED;
				break;
			default:
				break;
		}
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) {
			live=false;
		}
	}

	/**
	 * 碰撞检测
	 * @param tank
	 */
	public void collideWith(Tank tank) {
		if (this.group==tank.getGroup()) {
			return;
		}
		if (this.rectangle.intersects(tank.getRectangle())) {
			if (this.group == Group.BAD && tank.getGroup() == Group.GOOD) {
				die();
				tank.die();
				this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
			}else if (this.group==Group.GOOD && tank.getGroup()==Group.BAD) {
				die();
				tank.die();
				this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
			}
		}

	}

	public void die() {
		this.live=false;
	}

	public static int getSPEED() {
		return SPEED;
	}

	public static int getWIDTH() {
		return WIDTH;
	}


	public static int getHEIGHT() {
		return HEIGHT;
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

}
