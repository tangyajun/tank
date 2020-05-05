package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Image;

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

	public PlaneBullet(int x,int y, Dir dir,int speed, GameModelManager gameModelManager,
			Group group, Image image) {
		this(x,y,WIDTH,HEIGHT,dir,speed,gameModelManager,group,image);
	}

	public PlaneBullet(int x,int y,int width,int height, Dir dir,int speed, GameModelManager gameModelManager,
			Group group, Image image) {
		super(x,y,width,height,dir,speed,gameModelManager,group,image);
	}

	public PlaneBullet(int x,int y,int width,int height, Dir dir,int speed, GameModelManager gameModelManager,
			Group group, Image image, BlastStrategy blastStrategy) {
		super(x,y,width,height,dir,speed,gameModelManager,group,image,blastStrategy);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			gameModelManager.getBullets().remove(this);
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

	protected void move() {
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
		if (x<0 || y<0 || x>TankFrame.GAME_WINDOW_WIDTH || y>TankFrame.GAME_WINDOW_HEIGHT) {
			live=false;
		}
	}

	@Override
	public void collideWith(AbstractMilitaryWeapon weapon) {
		if (this.group==weapon.getGroup()) {
			return;
		}
		if (this.rectangle.intersects(weapon.getRectangle())) {
			if (this.group == Group.BAD && weapon.getGroup() == Group.GOOD) {
				die();
				weapon.die();
				/*if (weapon instanceof Plane) {
					this.gameModelManager.getExplodes().add(new PlaneExplode(x,y,PlaneExplode.WIDTH,PlaneExplode.HEIGHT,gameModelManager));
				}else if (weapon instanceof Tank) {
					this.gameModelManager.getExplodes().add(new Explode(x,y,gameModelManager));
				}*/
				blastStrategy.execute(weapon,this,gameModelManager);
			}else if (this.group==Group.GOOD && weapon.getGroup()==Group.BAD) {
				die();
				weapon.die();
				/*if (weapon instanceof Plane) {
					this.gameModelManager.getExplodes().add(new PlaneExplode(x,y,PlaneExplode.WIDTH,PlaneExplode.HEIGHT,gameModelManager));
				}else if (weapon instanceof Tank) {
					this.gameModelManager.getExplodes().add(new Explode(x,y,gameModelManager));
				}*/
				blastStrategy.execute(weapon,this,gameModelManager);
			}
		}
	}

	@Override
	public void die() {
		this.live=false;
	}
}
