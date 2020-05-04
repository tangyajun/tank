package com.yj.tank.model;

import java.awt.Graphics;

import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 * 飞机
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-03-22:36
 **/
public class Plane extends AbstractMilitaryWeapon {
	public static final int WIDTH= ResourceManager.goodTankDownImage.getWidth();
	public static final int HEIGHT=ResourceManager.goodTankDownImage.getHeight();
	/**
	 *
	 * @param x x坐标
	 * @param y y坐标
	 * @param dir 方向
	 * @param tankFrame 所属主界面
	 * @param group 所属的group
	 *
	 */
	public Plane(int x,int y, Dir dir, TankFrame tankFrame, Group group) {
		this(x,y,WIDTH,HEIGHT,dir,tankFrame,group);
	}

	public Plane(int x,int y,int width,int height,Dir dir, TankFrame tankFrame, Group group) {
		super(x,y,width,height,dir,tankFrame,group);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			this.tankFrame.getPlanes().remove(this);
		}
		switch (dir) {
			case DOWN:
				graphics.drawImage(this.group==Group.GOOD? ResourceManager.goodPlaneDownImage:ResourceManager.badPlaneDownImage,x,y,null);
				break;
			case UP:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodPlaneUpImage:ResourceManager.badPlaneUpImage,x,y,null);
				break;
			case RIGHT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodPlaneRightImage:ResourceManager.badPlaneRightImage,x,y,null);
				break;
			case LEFT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodPlaneLeftImage:ResourceManager.badPlaneLeftImage,x,y,null);
				break;
			default:
				break;
		}
		move();
	}

	private void move() {
		if (this.moving) {
			switch (dir) {
				case LEFT:
					if (this.group==Group.BAD) {
						x -= speedBad;
					}else if (this.group==Group.GOOD) {
						x -= speedGood;
					}
					break;
				case RIGHT:
					if (this.group==Group.BAD) {
						x += speedBad;
					}else {
						x += speedGood;
					}
					break;
				case UP:
					if (this.group==Group.BAD) {
						y -= speedBad;
					}else {
						y -= speedGood;
					}
					break;
				case DOWN:
					if (this.group==Group.BAD) {
						y += speedBad;
					}else {
						y += speedGood;
					}
					break;
				default:
					break;
			}
			if (random.nextInt(100)>bulletFrequency) {
				if (this.getGroup()==Group.BAD) {
					fire();
				}
			}
			if (this.y>160 && this.group==Group.BAD && random.nextInt(100)>95) {
				randomDir();
			}
			boundsCheck();
		}
	}

	@Override
	public void fire() {
		if (live) {
			//if (this instanceof Plane) {
			this.tankFrame.getBullets().add(tankFrame.getAbstractWeaponFamilyFactory().createBullet(this.x,this.y,0,0,dir,1,tankFrame,this.group,null));
			//}
			//this.tankFrame.getBullets().add(new Bullet(this.x, this.y, dir, this.group, this.tankFrame));
		}
	}

	private void randomDir() {
		if (this.dir==Dir.LEFT) {
			this.dir=dirs[random.nextInt(3)];
		}
		if(this.dir==Dir.RIGHT) {
			this.dir=dirs3[random.nextInt(3)];
		}
		if (this.dir==Dir.UP) {
			this.dir=dirs1[random.nextInt(3)];
		}
		if (this.dir==Dir.DOWN) {
			this.dir=dirs2[random.nextInt(3)];
		}
		this.dir=Dir.values()[random.nextInt(4)];
	}

	public void boundsCheck() {
		if (this.x<20){
			x=20;
			randomDir();
		}
		if (this.y<40) {
			y=40;
			randomDir();
		}
		if (this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) {
			this.x=TankFrame.GAME_WIDTH-Tank.WIDTH-4;
			randomDir();
		}
		if (this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) {
			this.y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-4;
			randomDir();
		}
	}
}
