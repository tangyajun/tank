package com.yj.tank.model;

import java.awt.Graphics;
import java.util.Random;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.view.TankFrame;

/**
 * 风车
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-17:57
 **/
public class Windmill extends Cowry {

	/**
	 * 方向
	 */
	private Dir dir=Dir.DOWN;

	/**
	 * 风车宽度
	 */
	public static final int WIDTH= ResourceManager.windmillImage.getWidth();

	/**
	 * 风车高度
	 */
	public static final int HEIGHT=ResourceManager.windmillImage.getHeight();

	private Random random=new Random();

	protected Dir[] dirs={Dir.LEFT,Dir.RIGHT,Dir.UP};
	protected Dir[] dirs1={Dir.RIGHT,Dir.LEFT,Dir.DOWN};
	protected Dir[] dirs2={Dir.UP,Dir.DOWN,Dir.RIGHT};
	protected Dir[] dirs3={Dir.UP,Dir.DOWN,Dir.LEFT};

	public Windmill(int x,int y, GameModelManager gameModelManager) {
		super(x,y,WIDTH,HEIGHT,gameModelManager);
	}

	public Windmill(int x,int y, Dir dir,GameModelManager gameModelManager) {
		super(x,y,WIDTH,HEIGHT,gameModelManager);
		this.dir=dir;
	}


	@Override
	public void paint(Graphics graphics) {
		if (!live) {
			this.gameModelManager.getEnemyTanks().remove(this);
		}
		if (this.gameModelManager.getTank()!= null) {
			if (!this.gameModelManager.getTank().isLive()) {
				this.gameModelManager.setTank(null);
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
				graphics.drawImage(ResourceManager.windmillImage,x,y,null);
				break;
			case UP:
				graphics.drawImage(ResourceManager.windmillImage,x,y,null);
				break;
			case LEFT:
				graphics.drawImage(ResourceManager.windmillImage,x,y,null);
				break;
			case RIGHT:
				graphics.drawImage(ResourceManager.windmillImage,x,y,null);
				break;
			default:
				break;
		}
	}

	/**
	 * 坦克移动
	 */
	public void move() {
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
			if (random.nextInt(100)>95) {
				randomDir();
			}
			boundsCheck();
			this.rectangle.x=this.x;
			this.rectangle.y=this.y;
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
		if (this.x> TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH) {
			this.x=TankFrame.GAME_WINDOW_WIDTH-Tank.WIDTH-4;
			randomDir();
		}
		if (this.y>TankFrame.GAME_WINDOW_HEIGHT-Tank.HEIGHT) {
			this.y=TankFrame.GAME_WINDOW_HEIGHT-Tank.HEIGHT-4;
			randomDir();
		}
	}

	public void die() {
		this.live=false;
	}
}
