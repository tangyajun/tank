package com.yj.tank.domain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.yj.tank.Audio;
import com.yj.tank.Fire;
import com.yj.tank.DefaultTankFire;
import com.yj.tank.TankFireFourBullet;
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
public class Tank {
	private int x,y;
	private Dir dir=Dir.DOWN;
	public int speedGood=10;
	public int speedBad=1;
	private boolean moving=true;
	TankFrame tankFrame=null;
	public static final int WIDTH= ResourceManager.goodTankDownImage.getWidth();
	public static final int HEIGHT=ResourceManager.goodTankDownImage.getHeight();
	Random random=new Random();
	boolean live=true;
	private Group group=Group.BAD;
	private Dir[] dirs={Dir.LEFT,Dir.RIGHT,Dir.UP};
	private Dir[] dirs1={Dir.RIGHT,Dir.LEFT,Dir.DOWN};
	private Dir[] dirs2={Dir.UP,Dir.DOWN,Dir.RIGHT};
	private Dir[] dirs3={Dir.UP,Dir.DOWN,Dir.LEFT};

	/**
	 * 子弹发射的频率
	 */
	int  bulletFrequency=95;

	Rectangle rectangle=new Rectangle();

	/**
	 *
	 */
	Fire fire=new DefaultTankFire();

	public Tank(int x,int y,Dir dir,TankFrame tankFrame,Group group) {
		super();
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.tankFrame=tankFrame;
		this.group=group;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=WIDTH;
		this.rectangle.height=HEIGHT;
	}

	public void paint(Graphics graphics) {
		if (!live) {
			this.tankFrame.getTanks().remove(this);
			//this.tankFrame.explodes.add(new Explode(x,y,tankFrame));
		}
		if (this.tankFrame.getTank()!= null) {
			if (!this.tankFrame.getTank().isLive()) {
				this.tankFrame.setTank(null);
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
	private void move() {
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
	private void boundsCheck() {
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

	/**
	 * 发射子弹
	 */
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

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void die() {
		this.live=false;
	}

	public TankFrame getTankFrame() {
		return tankFrame;
	}

	public void setTankFrame(TankFrame tankFrame) {
		this.tankFrame = tankFrame;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getSpeedGood() {
		return speedGood;
	}

	public void setSpeedGood(int speedGood) {
		this.speedGood = speedGood;
	}

	public int getSpeedBad() {
		return speedBad;
	}

	public void setSpeedBad(int speedBad) {
		this.speedBad = speedBad;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Fire getFire() {
		return fire;
	}

	public void setFire(Fire fire) {
		this.fire = fire;
	}
}
