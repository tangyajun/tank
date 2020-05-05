package com.yj.tank.model;

import java.awt.Graphics;

import com.yj.tank.Fire;
import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 *  坦克
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-13:18
 **/
public class Tank extends AbstractMilitaryWeapon {
	/*private int x,y;
	private Dir dir=Dir.DOWN;
	public int speedGood=10;
	public int speedBad=1;
	private boolean moving=true;
	TankFrame tankFrame=null;*/
	public static final int WIDTH= ResourceManager.goodTankDownImage.getWidth();
	public static final int HEIGHT=ResourceManager.goodTankDownImage.getHeight();
	/*Random random=new Random();
	boolean live=true;
	private Group group=Group.BAD;
	private Dir[] dirs={Dir.LEFT,Dir.RIGHT,Dir.UP};
	private Dir[] dirs1={Dir.RIGHT,Dir.LEFT,Dir.DOWN};
	private Dir[] dirs2={Dir.UP,Dir.DOWN,Dir.RIGHT};
	private Dir[] dirs3={Dir.UP,Dir.DOWN,Dir.LEFT};*/

	/**
	 * 子弹发射的频率
	 */
	//int  bulletFrequency=95;

	//Rectangle rectangle=new Rectangle();

	/**
	 *
	 */
	//Fire fire=new DefaultTankFire();

	public Tank(int x,int y,Dir dir,GameModelManager gameModelManager,Group group) {
		this(x,y,WIDTH,HEIGHT,dir,gameModelManager,group);
	}

	public Tank(int x,int y,int width,int height,Dir dir, GameModelManager gameModelManager,Group group) {
		super(x,y,width,height,dir,gameModelManager,group);
	}

	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			this.gameModelManager.getEnemyTanks().remove(this);
			//this.tankFrame.explodes.add(new Explode(x,y,tankFrame));
		}
		if (this.gameModelManager.getTank()!= null) {
			if (!this.gameModelManager.getTank().isLive()) {
				this.gameModelManager.setTank(null);
				//this.tankFrame.explodes.add(new Explode(x, y, tankFrame));
			}
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
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodTankDownImage:ResourceManager.badTankDownImage,x,y,null);
				break;
			case UP:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodTankUpImage:ResourceManager.badTankUpImage,x,y,null);
				break;
			case LEFT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodTankLeftImage:ResourceManager.badTankLeftImage,x,y,null);
				break;
			case RIGHT:
				graphics.drawImage(this.group==Group.GOOD?ResourceManager.goodTankRightImage:ResourceManager.badTankRightImage,x,y,null);
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
			if (this.y>160 && this.group==Group.BAD && random.nextInt(100)>95) {
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

	/**
	 * 随机改变方向
	 */
	public void randomDir() {
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

	/**
	 * 发射子弹
	 */

	@Override
	public void fire() {
		if (live) {
			fire.fire(this);
			/*int x=this.x+(Tank.WIDTH/2)-(Bullet.WIDTH/2);
			int y=this.y+(Tank.HEIGHT/2)-(Bullet.HEIGHT/2);
			tankFrame.getBullets().add(new Bullet(x,y, this.dir, this.group, this.tankFrame));
			if (this.group==Group.GOOD) {
				new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
			}*/
		}
	}


	public Fire getFire() {
		return fire;
	}

	public void setFire(Fire fire) {
		this.fire = fire;
	}
}
