package com.yj.tank.domain;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.yj.tank.MoveBehavior;
import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.Bullet;
import com.yj.tank.domain.Tank;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tang
 *  @create 2020-05-03-16:59
 **/
public abstract class AbstractMilitaryEquipment {
	/**
	 * 设备的x,y坐标
	 */
	protected int x,y;

	/**
	 * 设备的方向
	 */
	protected Dir dir=Dir.DOWN;

	/**
	 * 我方设备的速度
	 */
	protected int speedGood=10;

	/**
	 * 敌方设备的速度
	 */
	protected int speedBad=1;

	/**
	 * 是否移动
	 */
	protected boolean moving=true;

	/**
	 * 所属界面
	 */
	protected TankFrame tankFrame=null;

	/**
	 * 设备的宽度
	 */
	public static final int WIDTH= ResourceManager.goodTankDownImage.getWidth();

	/**
	 * 设备高度
	 */
	public static final int HEIGHT=ResourceManager.goodTankDownImage.getHeight();

	protected Random random=new Random();

	/**
	 * 存活状态
	 */
	protected boolean live=true;

	/**
	 * 所属分组 敌方 bad，我方 good
	 */
	protected Group group=Group.BAD;

	/**
	 * 设备朝左方向的图片
	 */
	protected BufferedImage leftBufferedImage;

	/**
	 * 设备朝右方向的图片
	 */
	protected BufferedImage rightBufferedImage;

	/**
	 * 设备朝上方向的图片
	 */
	protected BufferedImage upBufferedImage;

	/**
	 * 设备朝下方向的图片
	 */
	protected BufferedImage downBufferedImage;

	/**
	 * 移动行为
	 */
	protected MoveBehavior moveBehavior;

	/**
	 * 子弹发射的频率
	 */
	protected int  bulletFrequency=95;

	private Dir[] dirs={Dir.LEFT,Dir.RIGHT,Dir.UP};
	private Dir[] dirs1={Dir.RIGHT,Dir.LEFT,Dir.DOWN};
	private Dir[] dirs2={Dir.UP,Dir.DOWN,Dir.RIGHT};
	private Dir[] dirs3={Dir.UP,Dir.DOWN,Dir.LEFT};

	public AbstractMilitaryEquipment(int x,int y,Dir dir,TankFrame tankFrame,Group group,MoveBehavior moveBehavior) {
		this.x=x;
		this.y=y;
		this.tankFrame=tankFrame;
		this.group=group;
		this.moveBehavior=moveBehavior;
	}

	/**
	 * 绘制设备
	 * @param graphics
	 */
	public abstract void paint(Graphics graphics);

	/**
	 * 设备移动
	 */
	public abstract void move();


	/**
	 *
	 * @param tank
	 */
	public void collideWith(Tank tank) {
		if (this.group==tank.getGroup()) {
			return;
		}
		Rectangle rectangle=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
		Rectangle rectangle1=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
		if (rectangle.intersects(rectangle1)) {
			if (this.group == Group.BAD && tank.getGroup() == Group.GOOD) {
				die();
				tank.die();
			}else if (this.group==Group.GOOD && tank.getGroup()==Group.BAD) {
				die();
				tank.die();
			}
		}

	}

	public void die() {
		this.live=false;
	}

	/**
	 * 边界检测
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
	 * 开火
	 */
	public void fire() {
		if (live) {
			this.tankFrame.getBullets().add(new Bullet(this.x, this.y, dir, this.group, this.tankFrame));
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

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public TankFrame getTankFrame() {
		return tankFrame;
	}

	public void setTankFrame(TankFrame tankFrame) {
		this.tankFrame = tankFrame;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
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

	public BufferedImage getLeftBufferedImage() {
		return leftBufferedImage;
	}

	public void setLeftBufferedImage(BufferedImage leftBufferedImage) {
		this.leftBufferedImage = leftBufferedImage;
	}

	public BufferedImage getRightBufferedImage() {
		return rightBufferedImage;
	}

	public void setRightBufferedImage(BufferedImage rightBufferedImage) {
		this.rightBufferedImage = rightBufferedImage;
	}

	public BufferedImage getUpBufferedImage() {
		return upBufferedImage;
	}

	public void setUpBufferedImage(BufferedImage upBufferedImage) {
		this.upBufferedImage = upBufferedImage;
	}

	public BufferedImage getDownBufferedImage() {
		return downBufferedImage;
	}

	public void setDownBufferedImage(BufferedImage downBufferedImage) {
		this.downBufferedImage = downBufferedImage;
	}

	public MoveBehavior getMoveBehavior() {
		return moveBehavior;
	}

	public void setMoveBehavior(MoveBehavior moveBehavior) {
		this.moveBehavior = moveBehavior;
	}


}
