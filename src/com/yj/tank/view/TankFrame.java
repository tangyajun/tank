package com.yj.tank.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.ConfigProperties;
import com.yj.tank.TankFireFourBullet;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.GameStatus;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.Plane;
import com.yj.tank.PlayListener;
import com.yj.tank.TankFactory;
import com.yj.tank.TankTimeTask;
import com.yj.tank.domain.Bullet;
import com.yj.tank.domain.Explode;
import com.yj.tank.domain.Tank;

/**
 * 坦克游戏主界面
 * @author tangyajun
 *
 */
public class TankFrame extends Frame{
	/**
	 * 定时任务
	 */
	TankTimeTask tankTimeTask=new TankTimeTask(this,120);

	/**
	 *我方坦克集合
	 */
	static List<Tank> goodTanks=new ArrayList<>();

	/**
	 * 坦克
	 *
	 */
	Tank tank;

	/**
	 * 子弹集合
	 */
	List<Bullet> bullets=new LinkedList<>();

	/**
	 * 窗口宽度
	 */
	public static final int GAME_WIDTH= ConfigProperties.getInstance().getInteger("game_window_width");

	/**
	 * 窗口高度
	 */
	public static final int GAME_HEIGHT=ConfigProperties.getInstance().getInteger("game_window_height");

	/**
	 * 敌军坦克数量
	 */
	public static final int TANK_NUM=ConfigProperties.getInstance().getInteger("initEnemyTankNum");

	/**
	 * 总关卡数
	 */
	public static int totalLevelCount=10;
	/**
	 * 当前关卡数
	 */
	public static int curLevelCount=1;

	/**
	 * 敌方坦克集合
	 */
	List<Tank> tanks=new LinkedList<>();

	/**
	 * 爆炸
	 */
	List<Explode> explodes=new LinkedList<>();

	/**
	 * 飞机集合
	 */
	List<Plane> planes=new LinkedList<>();
	/**
	 * 生命数量
	 */
	public static final int lifeNum=ConfigProperties.getInstance().getInteger("initLifeNum");

	/**
	 * 敌军坦克数量
	 */
	public static final int enemyTankNum=ConfigProperties.getInstance().getInteger("initEnemyTankNum");

	Button play=new Button("PLAY");
	Container playContainer=new com.yj.tank.ButtonContainer(300,400,play);
	GameStatus gameStatus= GameStatus.LOADING;
	String next="NEXT";

	public TankFrame() {
		/**
		 * 设置获取焦点(不过不设置，游戏运行时键盘事件失效)
		 */
		setFocusable(true);
		setVisible(true);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setTitle("tank bar");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(new TankPaintListener());
		play.addMouseListener(new PlayListener(this));
		add(playContainer);
	}

	private Image offScreenImage=null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen=offScreenImage.getGraphics();
		Color color=gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage,0,0,null);
	}

	@Override
	public void paint(Graphics graphics) {
		Color color=graphics.getColor();
		graphics.setColor(Color.white);
		graphics.drawString("子弹数量:"+bullets.size(),10,60);
		graphics.drawString("敌军数量:"+tanks.size(),10,80);
		graphics.drawString("生命数量:"+goodTanks.size(),10,100);
		graphics.drawString("当前关数:"+curLevelCount,10,120);
		graphics.setColor(color);
		if (tank!= null) {
			tank.paint(graphics);
			if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && curLevelCount==1) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString(next,300,400);
				graphics.setColor(c);
				next="";
				if (curLevelCount==1) {
					curLevelCount++;
				}
				if (curLevelCount==2) {
					TankTimeTask.setCurTimes(new AtomicInteger(1));
					tankTimeTask.setTotal(5);
					tankTimeTask.setTankNum(6);
					tankTimeTask.setDistance(110);
					tankTimeTask.setBadTankSpeed(2);
					tankTimeTask.setDelay(20000);
					tankTimeTask.start(10000,curLevelCount);
				}
			}else if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && curLevelCount==2) {
				if (curLevelCount==2) {
					curLevelCount++;
				}
				if (curLevelCount==3) {
					TankTimeTask.setCurTimes(new AtomicInteger(1));
					tankTimeTask.setTotal(7);
					tankTimeTask.setTankNum(7);
					tankTimeTask.setDistance(100);
					tankTimeTask.setBadTankSpeed(3);
					tankTimeTask.setDelay(20000);
					tankTimeTask.start(10000,curLevelCount);
				}
			}else if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && curLevelCount==3) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("winner",300,400);
				graphics.setColor(c);
			}
		}else {
			if (goodTanks.size()>0) {
				goodTanks.remove(goodTanks.get(0));
				tank = goodTanks.get(0);
			}else if(goodTanks.size()<=0 && this.gameStatus== GameStatus.RUNNING){
				this.gameStatus= GameStatus.OVER;
			}else if (goodTanks.size()==0 && this.gameStatus== GameStatus.OVER) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("game over",300,400);
				graphics.setColor(c);
				play.setLabel("replay");
				play.setVisible(true);
				clear();
			}
		}
		paintBullets(graphics);
		paintTanks(graphics);
		paintExplodes(graphics);
		collideCheck();
	}

	public class TankPaintListener extends KeyAdapter {
		boolean kl=false;
		boolean kr=false;
		boolean kd=false;
		boolean ku=false;
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			switch (keyCode) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					kl=true;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					kr=true;
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					kd=true;
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					ku=true;
					break;
				case KeyEvent.VK_SPACE:
					/*if (tank != null) {
						tank.fire();
					}*/
					break;
				default:
					break;
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode=e.getKeyCode();
			switch (keyCode) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					kl=false;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					kr=false;
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					kd=false;
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					ku=false;
					break;
				case KeyEvent.VK_SPACE:
					if (tank != null) {
						tank.fire();
					}
					break;
				default:
					break;
			}
			setMainTankDir();
		}

		/**
		 * 设置坦克方向
		 */
		private void setMainTankDir() {
			if (tank != null) {
				if (!kl && !kr && !kd && !ku) {
					tank.setMoving(false);
				}else {
					tank.setMoving(true);
					if (kl) {
						tank.setDir(Dir.LEFT);
					}
					if (kr) {
						tank.setDir(Dir.RIGHT);
					}
					if (kd) {
						tank.setDir(Dir.DOWN);
					}
					if (ku) {
						tank.setDir(Dir.UP);
					}
				}
			}
		}
	}

	/**
	 * 绘制子弹
	 * @param graphics
	 */
	private void paintBullets(Graphics graphics) {
		for (int i=0;i<bullets.size();i++) {
			bullets.get(i).paint(graphics);
		}
	}

	/**
	 * 绘制敌军坦克
	 * @param graphics
	 */
	private void paintTanks(Graphics graphics) {
		for (int j=0;j<tanks.size();j++) {
			tanks.get(j).paint(graphics);
		}
	}

	/**
	 * 绘制爆炸效果
	 * @param graphics
	 */
	private void paintExplodes(Graphics graphics) {
		for (int i=0;i<explodes.size();i++) {
			explodes.get(i).paint(graphics);
		}
	}

	/**
	 * 碰撞检测,检测子弹和坦克是否相撞
	 */
	private void collideCheck() {
		for (int i=0;i<bullets.size();i++) {
			for (int j=0;j<tanks.size();j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
			if (tank != null) {
				bullets.get(i).collideWith(tank);
			}
		}
	}

	/**
	 * 初始化坦克
	 */
	public void init() {
		this.tanks.addAll(TankFactory.createTanks(TankFrame.TANK_NUM,this, Group.BAD,120,Dir.DOWN));
		for (int i=0;i<lifeNum;i++) {
			goodTanks.add(new Tank(100,400,Dir.DOWN,this,Group.GOOD));
		}
		tank=goodTanks.get(0);
		tank.setMoving(false);
	}

	/**
	 * 清楚坦克
	 */
	public void clear() {
		if (this.tank!= null) {
			this.tank=null;
		}
		if (this.bullets.size()>0) {
			this.bullets.clear();
		}
		if (this.tanks.size()>0) {
			this.tanks.clear();
		}
		if (goodTanks.size()>0) {
			goodTanks.clear();
		}
		TankTimeTask.setCurTimes(new AtomicInteger(1));
	}

	public TankTimeTask getTankTimeTask() {
		return tankTimeTask;
	}

	public void setTankTimeTask(TankTimeTask tankTimeTask) {
		this.tankTimeTask = tankTimeTask;
	}

	public static List<Tank> getGoodTanks() {
		return goodTanks;
	}

	public static void setGoodTanks(List<Tank> goodTanks) {
		TankFrame.goodTanks = goodTanks;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}

	public static int getTotalLevelCount() {
		return totalLevelCount;
	}

	public static void setTotalLevelCount(int totalLevelCount) {
		TankFrame.totalLevelCount = totalLevelCount;
	}

	public static int getCurLevelCount() {
		return curLevelCount;
	}

	public static void setCurLevelCount(int curLevelCount) {
		TankFrame.curLevelCount = curLevelCount;
	}

	public List<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

	public List<Explode> getExplodes() {
		return explodes;
	}

	public void setExplodes(List<Explode> explodes) {
		this.explodes = explodes;
	}

	public List<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public static int getLifeNum() {
		return lifeNum;
	}

	public static int getEnemyTankNum() {
		return enemyTankNum;
	}

	public Button getPlay() {
		return play;
	}

	public void setPlay(Button play) {
		this.play = play;
	}

	public Container getPlayContainer() {
		return playContainer;
	}

	public void setPlayContainer(Container playContainer) {
		this.playContainer = playContainer;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Image getOffScreenImage() {
		return offScreenImage;
	}

	public void setOffScreenImage(Image offScreenImage) {
		this.offScreenImage = offScreenImage;
	}
}
