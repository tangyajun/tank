package com.yj.tank.model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.yj.tank.BlastStrategy;
import com.yj.tank.Fire;
import com.yj.tank.FireBulletStrategy;
import com.yj.tank.MoveBehavior;
import com.yj.tank.constant.Constant;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;

/**
 *
 *  @Description TO DO
 *  @author tang
 *  @create 2020-05-03-16:59
 **/
public abstract class AbstractMilitaryWeapon extends GameProp {

	/**
	 * 方向 默认朝下
	 */
	protected Dir dir=Dir.DOWN;

	/**
	 * 速度
	 */
	protected int speedGood=10;

	/**
	 * 敌方的速度
	 */
	protected int speedBad=5;

	/**
	 * 是否移动
	 */
	protected boolean moving=true;

	protected Random random=new Random();

	/**
	 * 存活状态
	 */
	protected boolean live=true;

	/**
	 * 所属分组 敌军 bad，玩家 good
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
	protected int  bulletFrequency= Constant.BULLET_FREQUENCY;

	protected FireBulletStrategy fireBulletStrategy;

	protected BlastStrategy blastStrategy;

	/**
	 *
	 */
	protected Fire fire;

	protected Dir[] dirs={Dir.LEFT,Dir.RIGHT,Dir.UP};
	protected Dir[] dirs1={Dir.RIGHT,Dir.LEFT,Dir.DOWN};
	protected Dir[] dirs2={Dir.UP,Dir.DOWN,Dir.RIGHT};
	protected Dir[] dirs3={Dir.UP,Dir.DOWN,Dir.LEFT};
	protected Dir[] dirs4={Dir.RIGHT,Dir.UP,Dir.DOWN};

	public AbstractMilitaryWeapon(int x,int y,int width,int height,Dir dir,Group group,Fire fire) {
		this(x,y,width,height,dir,group,fire,null);
	}

	public AbstractMilitaryWeapon(int x,int y,int width,int height,Dir dir,
			Group group,Fire fire,FireBulletStrategy fireBulletStrategy) {
		super(x,y,width,height);
		this.group=group;
		this.rectangle.x=this.x;
		this.rectangle.y=this.y;
		this.rectangle.width=width;
		this.rectangle.height=height;
		this.fire=fire;
		this.fireBulletStrategy=fireBulletStrategy;
	}


	public void die() {
		this.live=false;
	}

	/**
	 * 发射子弹
	 */
	public abstract void fire();

	/**
	 * 随机改变坦克方向
	 */
	public void randomDir() {
		/**
		 * 当前方向朝左
		 */
		if (this.dir==Dir.LEFT) {
			//this.dir=dirs4[random.nextInt(3)];
			this.dir=Dir.RIGHT;
		}
		/**
		 * 当前方向朝右
		 */
		else if(this.dir==Dir.RIGHT) {
			//this.dir=dirs3[random.nextInt(3)];
			this.dir=Dir.LEFT;
		}
		/**
		 * 当前方向朝上
		 */
		else if (this.dir==Dir.UP) {
			//this.dir=dirs1[random.nextInt(3)];
			this.dir=Dir.DOWN;
		}
		/**
		 * 当前方向朝下
		 */
		else if (this.dir==Dir.DOWN) {
			//this.dir=dirs[random.nextInt(3)];
			this.dir=Dir.UP;
		}
		/*else {
			this.dir = Dir.values()[random.nextInt(4)];
		}*/
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
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

	@Override
	public Rectangle getRectangle() {
		return rectangle;
	}

	@Override
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getBulletFrequency() {
		return bulletFrequency;
	}

	public void setBulletFrequency(int bulletFrequency) {
		this.bulletFrequency = bulletFrequency;
	}

	public FireBulletStrategy getFireBulletStrategy() {
		return fireBulletStrategy;
	}

	public void setFireBulletStrategy(FireBulletStrategy fireBulletStrategy) {
		this.fireBulletStrategy = fireBulletStrategy;
	}

	public BlastStrategy getBlastStrategy() {
		return blastStrategy;
	}

	public void setBlastStrategy(BlastStrategy blastStrategy) {
		this.blastStrategy = blastStrategy;
	}
}
