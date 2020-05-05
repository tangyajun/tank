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
import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.GameStatus;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.Plane;
import com.yj.tank.PlayListener;
import com.yj.tank.factory.AbstractWeaponFamilyFactory;
import com.yj.tank.TankTimeTask;
import com.yj.tank.factory.TankFactory;
import com.yj.tank.factory.DefaultTankFamilyFactory;
import com.yj.tank.factory.WeaponFactory;
import com.yj.tank.model.Tank;

/**
 * 坦克游戏主界面
 * @author tangyajun
 *
 */
public class TankFrame extends Frame{

	/**
	 *我方坦克集合
	 */
	//static List<Tank> goodTanks=new ArrayList<>();

	GameModelManager modelManager=GameModelManager.getInstance();
	/**
	 * 坦克
	 *
	 */
	//Tank tank;

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
		graphics.drawString("子弹数量:"+modelManager.getBullets().size(),10,60);
		graphics.drawString("敌军数量:"+modelManager.getEnemyTanks().size(),10,80);
		graphics.drawString("生命数量:"+GameModelManager.tanks.size(),10,100);
		graphics.drawString("当前关数:"+GameModelManager.curLevelCount,10,120);
		graphics.setColor(color);
		AbstractMilitaryWeapon tank=modelManager.getTank();
		TankTimeTask tankTimeTask=modelManager.getTankTimeTask();
		List<AbstractMilitaryWeapon> tanks=modelManager.getEnemyTanks();
		if (tank!= null) {
			tank.paint(graphics);
			if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && GameModelManager.curLevelCount==1) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString(next,300,400);
				graphics.setColor(c);
				next="";
				if (GameModelManager.curLevelCount==1) {
					GameModelManager.curLevelCount++;
				}
				if (GameModelManager.curLevelCount==2) {
					TankTimeTask.setCurTimes(new AtomicInteger(1));
					tankTimeTask.setTotal(5);
					tankTimeTask.setTankNum(6);
					tankTimeTask.setDistance(110);
					tankTimeTask.setBadTankSpeed(2);
					tankTimeTask.setDelay(20000);
					tankTimeTask.start(10000,GameModelManager.curLevelCount);
				}
			}else if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && GameModelManager.curLevelCount==2) {
				if (GameModelManager.curLevelCount==2) {
					GameModelManager.curLevelCount++;
				}
				if (GameModelManager.curLevelCount==3) {
					TankTimeTask.setCurTimes(new AtomicInteger(1));
					tankTimeTask.setTotal(7);
					tankTimeTask.setTankNum(7);
					tankTimeTask.setDistance(100);
					tankTimeTask.setBadTankSpeed(3);
					tankTimeTask.setDelay(20000);
					tankTimeTask.start(10000,GameModelManager.curLevelCount);
				}
			}else if (TankTimeTask.curTimes.intValue()==tankTimeTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && GameModelManager.curLevelCount==3) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("winner",300,400);
				graphics.setColor(c);
			}
		}else {
			if (GameModelManager.tanks.size()>0) {
				GameModelManager.tanks.remove(GameModelManager.tanks.get(0));
				//tank = goodTanks.get(0);
				if (GameModelManager.tanks.size()>0) {
					tank = GameModelManager.tanks.get(0);
				}
			}else if(GameModelManager.tanks.size()<=0 && this.gameStatus== GameStatus.RUNNING){
				this.gameStatus= GameStatus.OVER;
			}else if (GameModelManager.tanks.size()==0 && this.gameStatus== GameStatus.OVER) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("game over",300,400);
				graphics.setColor(c);
				play.setLabel("replay");
				play.setVisible(true);
				modelManager.clear();
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
					if (modelManager.getTank() != null) {
						modelManager.getTank().fire();
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
			AbstractMilitaryWeapon tank=modelManager.getTank();
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
		List<AbstractBullet> bullets=modelManager.getBullets();
		for (int i=0;i<bullets.size();i++) {
			bullets.get(i).paint(graphics);
		}
	}

	/**
	 * 绘制敌军坦克
	 * @param graphics
	 */
	private void paintTanks(Graphics graphics) {
		List<AbstractMilitaryWeapon> tanks=modelManager.getEnemyTanks();
		for (int j=0;j<tanks.size();j++) {
			tanks.get(j).paint(graphics);
		}
	}

	/**
	 * 绘制爆炸效果
	 * @param graphics
	 */
	private void paintExplodes(Graphics graphics) {
		List<AbstractExplode> explodes=modelManager.getExplodes();
		for (int i=0;i<explodes.size();i++) {
			explodes.get(i).paint(graphics);
		}
	}

	/**
	 * 碰撞检测,检测子弹和坦克是否相撞
	 */
	private void collideCheck() {
		List<AbstractBullet> bullets=modelManager.getBullets();
		List<AbstractMilitaryWeapon> tanks=modelManager.getEnemyTanks();
		AbstractMilitaryWeapon tank=modelManager.getTank();
		for (int i=0;i<bullets.size();i++) {
			for (int j=0;j<tanks.size();j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
			if (tank != null) {
				bullets.get(i).collideWith(tank);
			}
		}
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

	public Button getPlay() {
		return play;
	}

	public void setPlay(Button play) {
		this.play = play;
	}
}
