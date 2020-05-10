package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Image;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.EPRExtension;
import com.yj.tank.BlastStrategy;
import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-11:41
 **/
public class SmallTankBullet extends AbstractBullet {
	/**
	 * 子弹的速度
	 */
	private static final int SPEED=20;
	public static final int WIDTH= ResourceManager.smallBulletD.getWidth();
	public static final int HEIGHT=ResourceManager.smallBulletD.getHeight();

	public SmallTankBullet(int x,int y, Dir dir, Group group) {
		this(x,y,dir,group,null);
	}

	public SmallTankBullet(int x,int y,Dir dir,Group group, Image image) {
		super(x,y,WIDTH,HEIGHT,dir,SPEED,group,image);
		setRectangle(super.rectangle);
	}

	public SmallTankBullet(int x,int y,Dir dir,Group group,  Image image,BlastStrategy blastStrategy) {
		super(x,y,WIDTH,HEIGHT,dir,SPEED,group,image,blastStrategy);
		setRectangle(super.rectangle);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			GameModelManager.getInstance().removeGameProp(this);
		}
		switch (dir) {
			case DOWN:
				if (image ==null) {
					graphics.drawImage(ResourceManager.smallBulletD, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case UP:
				if (image == null) {
					graphics.drawImage(ResourceManager.smallBulletU, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case LEFT:
				if (image==null) {
					graphics.drawImage(ResourceManager.smallBulletL, x, y, null);
				}else {
					graphics.drawImage(image, x, y, null);
				}
				break;
			case RIGHT:
				if (image==null) {
					graphics.drawImage(ResourceManager.smallBulletR,x,y,null);
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
		if (x<0 || y<0 || x> TankFrame.GAME_WINDOW_WIDTH || y>TankFrame.GAME_WINDOW_HEIGHT) {
			live=false;
		}
	}

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
				blastStrategy.execute(weapon,this);

			}else if (this.group==Group.GOOD && weapon.getGroup()==Group.BAD) {
				die();
				weapon.die();

				blastStrategy.execute(weapon,this);
			}
		}
	}
}
