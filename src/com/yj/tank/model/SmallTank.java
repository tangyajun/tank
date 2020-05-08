package com.yj.tank.model;

import java.awt.Graphics;
import java.util.List;

import com.yj.tank.DefaultTankFire;
import com.yj.tank.Fire;
import com.yj.tank.FireBulletStrategy;
import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
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
	 * @param gameModelManager
	 * @param group 分组
	 */
	public SmallTank(int x,int y, Dir dir, GameModelManager gameModelManager, Group group) {
		this(x,y,WIDTH,HEIGHT,dir,gameModelManager,group);
	}

	public SmallTank(int x,int y, Dir dir, GameModelManager gameModelManager, Group group,FireBulletStrategy fireBulletStrategy) {
		this(x,y,WIDTH,HEIGHT,dir,gameModelManager,group,fireBulletStrategy);
	}

	/**
	 * 坦克构造函数
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param dir 方向
	 * @param gameModelManager
	 * @param group 分组
	 */
	public SmallTank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group) {
		this(x,y,width,height,dir,gameModelManager,group,null);
	}

	public SmallTank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group,
			FireBulletStrategy fireBulletStrategy) {
		this(x,y,width,height,dir,gameModelManager,group,new DefaultTankFire(),fireBulletStrategy);
	}

	public SmallTank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group,
			Fire fire,FireBulletStrategy fireBulletStrategy) {
		super(x,y,width,height,dir,gameModelManager,group,fire,fireBulletStrategy);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			this.gameModelManager.removeGameProp(this);
		}
		/*if (this.gameModelManager.getTank()!= null) {
			if (!this.gameModelManager.getTank().isLive()) {
				this.gameModelManager.setTank(null);
			}
		}*/
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

	/**
	 * 坦克移动
	 */
	public void move() {
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
			 * 子弹发射频率
			 */
			if (random.nextInt(100)>bulletFrequency) {
				if (this.getGroup()==Group.BAD) {
					fire();
				}
			}
			if (this.y>200 && this.group==Group.BAD && random.nextInt(100)>95) {
				randomDir();
			}
			boundsCheck();
			this.rectangle.x=this.x;
			this.rectangle.y=this.y;
		}
	}

	/**
	 * 边界检查
	 */
	public void boundsCheck() {
		if (this.x<20){
			x=20;
			randomDir();
		}
		if (this.y<60) {
			y=60;
			randomDir();
		}
		if (this.x> TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH) {
			this.x=TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH-4;
			randomDir();
		}
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

	@Override
	public void enemyBoundCheck() {
		List<AbstractMilitaryWeapon> enemyTanks=gameModelManager.getEnemyTanks();
		for (int i=0;i<enemyTanks.size();i++) {
			if (enemyTanks.get(i) != this) {
				AbstractMilitaryWeapon weapon=enemyTanks.get(i);
				if (this.x>weapon.getX()) {
					if (this.x - weapon.getX() < 20) {
						if (random.nextInt(100)>95) {
							randomDir();
						}
					}
				}else {
					if (weapon.getX()-this.x < 20 ) {
						if (random.nextInt(100)>95) {
							randomDir();
						}
					}
				}
				if (this.y>weapon.getY()) {
					if (this.y - weapon.getY() < 20) {
						if (random.nextInt(100)>95) {
							randomDir();
						}
					}
				}else {
					if (weapon.getY()-this.y< 20) {
						if (random.nextInt(100)>95) {
							randomDir();
						}
					}
				}
			}
		}
	}
}
