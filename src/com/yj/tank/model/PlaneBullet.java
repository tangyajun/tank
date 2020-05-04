package com.yj.tank.model;

import java.awt.Graphics;
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

	@Override
	public void paint(Graphics graphics) {

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
		if (x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) {
			live=false;
		}
	}

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

	@Override
	public void die() {
		this.live=false;
	}
}
