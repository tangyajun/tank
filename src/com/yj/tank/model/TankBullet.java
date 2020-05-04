package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Image;

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
public class TankBullet extends AbstractBullet {
	/**
	 * 子弹的速度
	 */
	private static final int SPEED=20;
	public static final int WIDTH= ResourceManager.bulletUpImage.getWidth();
	public static final int HEIGHT=ResourceManager.bulletUpImage.getHeight();

	/**
	 * 子弹的坐标
	 */
	/*private int x,y;

	*//**
	 * 子弹的方向
	 *//*
	private Dir dir;

	*//**
	 * 子弹是否存活
	 *//*
	boolean live=true;

	*//**
	 *
	 *//*
	TankFrame tankFrame;

	*//**
	 * 所属分组
	 *//*
	private Group group=Group.BAD;

	Rectangle rectangle=new Rectangle();

	*//**
	 * 子弹的图片
	 *//*
	private Image image;*/

	public TankBullet(int x,int y,Dir dir,Group group,TankFrame tankFrame) {
		this(x,y,dir,group,tankFrame,null);
	}

	public TankBullet(int x,int y,Dir dir,Group group, TankFrame tankFrame,Image image) {
		super(x,y,WIDTH,HEIGHT,dir,SPEED,tankFrame,group,image);
		/*this.tankFrame=tankFrame;
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.group=group;
		this.image=image;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=WIDTH;
		this.rectangle.height=HEIGHT;*/
	}

	@Override
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

	@Override
	public void die() {
		this.live=false;
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
	/*public void collideWith(Tank tank) {
		if (this.group==tank.getGroup()) {
			return;
		}
		if (this.rectangle.intersects(tank.getRectangle())) {
			if (this.group == Group.BAD && tank.getGroup() == Group.GOOD) {
				die();
				tank.die();
				//this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
			}else if (this.group==Group.GOOD && tank.getGroup()==Group.BAD) {
				die();
				tank.die();
				//this.tankFrame.getExplodes().add(new Explode(x,y,tankFrame));
			}
		}

	}*/

	/**
	 * 碰撞检测
	 * @param weapon
	 */
	@Override
	public void collideWith(AbstractMilitaryWeapon weapon) {
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
	}


}
