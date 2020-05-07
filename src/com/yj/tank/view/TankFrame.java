package com.yj.tank.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.ColliderChain;
import com.yj.tank.ConfigProperties;
import com.yj.tank.GameModelManager;
import com.yj.tank.PlayListener;
import com.yj.tank.TankTask;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.GameStatus;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;

/**
 * 坦克游戏主界面
 * @author tangyajun
 *
 */
public class TankFrame extends Frame{

	/**
	 *玩家坦克集合
	 */
	//static List<Tank> goodTanks=new ArrayList<>();

	GameModelManager modelManager=GameModelManager.getInstance();

	/**
	 *
	 */
	ColliderChain colliderChain=ColliderChain.getInstance();
	/**
	 * 坦克
	 *
	 */
	//Tank tank;

	/**
	 * 窗口宽度
	 */
	public static final int GAME_WINDOW_WIDTH= ConfigProperties.getInstance().getInteger("game_window_width");

	/**
	 * 窗口高度
	 */
	public static final int GAME_WINDOW_HEIGHT=ConfigProperties.getInstance().getInteger("game_window_height");

	/**
	 * 敌军坦克数量
	 */
	//public static final int TANK_NUM=ConfigProperties.getInstance().getInteger("initEnemyTankNum");

	Button play=new Button("PLAY");
	Container playContainer=new ButtonContainer(300,400,play);
	GameStatus gameStatus= GameStatus.LOADING;
	String next="NEXT";

	/**
	 * 屏幕宽度
	 */
	public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

	/**
	 * 屏幕高度
	 */
	public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public TankFrame() {
		this.setBounds((SCREEN_WIDTH-GAME_WINDOW_WIDTH)/2,(SCREEN_HEIGHT-GAME_WINDOW_HEIGHT)/2,GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT);
		/**
		 * 设置获取焦点(不过不设置，游戏运行时键盘事件失效)
		 */
		setFocusable(true);
		setVisible(true);
		setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		setTitle("坦克大战");
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
			offScreenImage=this.createImage(GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT);
		}
		Graphics gOffScreen=offScreenImage.getGraphics();
		Color color=gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0,0,GAME_WINDOW_WIDTH,GAME_WINDOW_HEIGHT);
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
		graphics.drawString("生命数量:"+GameModelManager.gamersTanks.size(),10,100);
		graphics.drawString("当前关数:"+GameModelManager.curLevelCount,10,120);
		graphics.setColor(color);
		AbstractMilitaryWeapon tank=modelManager.getTank();
		TankTask tankTask =modelManager.getTankTask();
		List<AbstractMilitaryWeapon> tanks=modelManager.getEnemyTanks();
		/*if (tank!= null) {
			tank.paint(graphics);
			if (TankTask.curTimes.intValue()== tankTask.total && tanks.size()<=0 &&
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
					TankTask.setCurTimes(new AtomicInteger(1));
					tankTask.setTotal(5);
					tankTask.setTankNum(6);
					tankTask.setDistance(110);
					tankTask.setBadTankSpeed(2);
					tankTask.setDelay(20000);
					tankTask.start(10000,GameModelManager.curLevelCount);
					System.out.println("111111111111111111111111111111111111111");
				}
			}else if (TankTask.curTimes.intValue()== tankTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && GameModelManager.curLevelCount==2) {
				if (GameModelManager.curLevelCount==2) {
					GameModelManager.curLevelCount++;
				}
				if (GameModelManager.curLevelCount==3) {
					TankTask.setCurTimes(new AtomicInteger(1));
					tankTask.setTotal(7);
					tankTask.setTankNum(7);
					tankTask.setDistance(100);
					tankTask.setBadTankSpeed(3);
					tankTask.setDelay(20000);
					tankTask.start(10000,GameModelManager.curLevelCount);
					System.out.println("2222222222222222222222222222222222222222222");
				}
			}else if (TankTask.curTimes.intValue()== tankTask.total && tanks.size()<=0 &&
					this.gameStatus==GameStatus.RUNNING && GameModelManager.curLevelCount==3) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("winner",300,400);
				graphics.setColor(c);
			}
		}else {
			if (GameModelManager.gamersTanks.size()>0) {
				GameModelManager.gamersTanks.remove(GameModelManager.gamersTanks.get(0));
				if (GameModelManager.gamersTanks.size()>0) {
					tank = GameModelManager.gamersTanks.get(0);
				}
			}else if(GameModelManager.gamersTanks.size()<=0 && this.gameStatus== GameStatus.RUNNING){
				this.gameStatus= GameStatus.OVER;
			}else if (GameModelManager.gamersTanks.size()==0 && this.gameStatus== GameStatus.OVER) {
				Color c=graphics.getColor();
				graphics.setColor(Color.WHITE);
				graphics.drawString("game over",300,400);
				graphics.setColor(c);
				play.setLabel("replay");
				play.setVisible(true);
				modelManager.clear();
			}
		}*/
		// 绘制游戏道具图形
		List<GameProp> gameProps=modelManager.getGameProps();
		Map<String,GameProp> gamePropMap=GameModelManager.getGameModelMap();

		for (Iterator<String> iter=gamePropMap.keySet().iterator();iter.hasNext();) {
			String key=iter.next();
			gamePropMap.get(key).paint(graphics);
		}
		/*gameProps.stream().forEach(gameProp -> {
			gameProp.paint(graphics);
		});*/
		/*for (int i=0;i<gameProps.size();i++) {
			gameProps.get(i).paint(graphics);
		}*/

		/*paintBullets(graphics);
		paintTanks(graphics);
		paintExplodes(graphics);*/
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
					break;
				default:
					break;
			}
			changeMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			GamersTank gamersTank=modelManager.getGamersTank();
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
					if (gamersTank != null) {
						gamersTank.fire();
					}
					break;
				default:
					break;
			}
			changeMainTankDir();
		}

		/**
		 * 改变坦克方向
		 */
		private void changeMainTankDir() {
			GamersTank tank=modelManager.getGamersTank();
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
		List<GameProp> gameProps=modelManager.getGameProps();
		for (int i=0;i<gameProps.size();i++) {
			for (int j=0;j<gameProps.size();j++) {
				colliderChain.chain(gameProps.get(i),gameProps.get(j));
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
