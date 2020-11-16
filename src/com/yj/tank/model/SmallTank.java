package com.yj.tank.model;

import java.awt.Graphics;
import java.util.List;

import com.yj.tank.DefaultTankFire;
import com.yj.tank.Fire;
import com.yj.tank.FireBulletStrategy;
import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Constant;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-11:23
 **/
public class SmallTank extends AbstractMilitaryWeapon {
	private int preX;
	private int preY;
	/**
	 * 坦克宽度
	 */
	public static final int WIDTH= ResourceManager.enemySmallTankD.getWidth();

	/**
	 * 坦克高度
	 */
	public static final int HEIGHT=ResourceManager.enemySmallTankD.getHeight();

	/**
	 * 坦克构造函数
	 * @param x x坐标
	 * @param y y坐标
	 * @param dir 方向
	 * @param
	 * @param group 分组
	 */
	public SmallTank(int x,int y, Dir dir, Group group) {
		this(x,y,WIDTH,HEIGHT,dir,group);
	}

	public SmallTank(int x,int y, Dir dir, Group group,FireBulletStrategy fireBulletStrategy) {
		this(x,y,WIDTH,HEIGHT,dir,group,fireBulletStrategy);
	}

	/**
	 * 坦克构造函数
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param dir 方向
	 * @param
	 * @param group 分组
	 */
	public SmallTank(int x,int y,int width,int height,Dir dir, Group group) {
		this(x,y,width,height,dir,group,null);
	}

	public SmallTank(int x,int y,int width,int height,Dir dir, Group group,
			FireBulletStrategy fireBulletStrategy) {
		this(x,y,width,height,dir,group,new DefaultTankFire(),fireBulletStrategy);
	}

	public SmallTank(int x,int y,int width,int height,Dir dir, Group group,
			Fire fire,FireBulletStrategy fireBulletStrategy) {
		super(x,y,width,height,dir,group,fire,fireBulletStrategy);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			GameModelManager.getInstance().removeGameProp(this);
		}
		paintImage(graphics);
		move();
	}

	/**
	 * 绘制图片
	 * @param graphics 画笔
	 */
	private void paintImage(Graphics graphics) {
		switch (dir) {
			case DOWN:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.smallTankD:ResourceManager.enemySmallTankD,x,y,null);
				break;
			case UP:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.smallTankU:ResourceManager.enemySmallTankU,x,y,null);
				break;
			case LEFT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.smallTankL:ResourceManager.enemySmallTankL,x,y,null);
				break;
			case RIGHT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.smallTankR:ResourceManager.enemySmallTankR,x,y,null);
				break;
			default:
				break;
		}
	}

	public void back() {
		this.x=preX;
		this.y=preY;
	}

	/**
	 * 坦克移动
	 */
	public void move() {
		this.preX=this.x;
		this.preY=this.y;
		if (moving) {
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

			/**
			 * 如果子弹发射频率大于95
			 */
			if (random.nextInt(Constant.BULLET_FREQUENCY_RANDOM_RANGE)>bulletFrequency) {
				if (this.getGroup()==Group.BAD) {
					fire();
				}
			}
			/**
			 * 如果坦克的y坐标大于200
			 */
			if (this.y>random.nextInt(800) && this.group==Group.BAD) {
				//randomDir();
			}
			// 坦克边界检查
			boundsCheck();
			this.rectangle.x=this.x;
			this.rectangle.y=this.y;
		}
	}

	/**
	 * 边界检查
	 */
	public void boundsCheck() {
		if (this.x<Constant.TANK_X_COORDINATE_MIN){
			x=Constant.TANK_X_COORDINATE_MIN;
			randomDir();
		}
		if (this.y<Constant.TANK_Y_COORDINATE_MIN) {
			y=Constant.TANK_Y_COORDINATE_MIN;
			randomDir();
		}
		/**
		 * 如果坦克的x坐标超出
		 */
		if (this.x> TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH) {
			this.x=TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH-4;
			randomDir();
		}
		/**
		 * 如果坦克的y坐标超出
		 */
		if (this.y>TankFrame.GAME_WINDOW_HEIGHT-Tank.HEIGHT) {
			this.y=TankFrame.GAME_WINDOW_HEIGHT-Tank.HEIGHT-4;
			randomDir();
		}
	}



	/**
	 * 发射子弹
	 */

	@Override
	public void fire() {
		if (live) {
			fire.fire(this);
		}
	}

	public int getPreX() {
		return preX;
	}

	public void setPreX(int preX) {
		this.preX = preX;
	}

	public int getPreY() {
		return preY;
	}

	public void setPreY(int preY) {
		this.preY = preY;
	}
}
