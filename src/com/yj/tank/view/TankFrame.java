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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yj.tank.ConfigProperties;
import com.yj.tank.GameModelManager;
import com.yj.tank.PlayListener;
import com.yj.tank.TankTask;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.GameStatus;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;

/**
 * 坦克游戏主界面
 * @author tangyajun
 *
 */
public class TankFrame extends Frame {
	/**
	 * 窗口宽度
	 */
	public static final int GAME_WINDOW_WIDTH= ConfigProperties.getInstance().getInteger("game_window_width");

	/**
	 * 窗口高度
	 */
	public static final int GAME_WINDOW_HEIGHT=ConfigProperties.getInstance().getInteger("game_window_height");
	/**
	 * 开始按钮
	 */
	Button playButton=new Button("PLAY");

	/**
	 *  按钮容器
	 */
	Container playContainer=new ButtonContainer(300,400,playButton);

	/**
	 * 游戏状态为加载中
	 */
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
		 * 设置获取焦点(不设置，游戏运行时键盘事件失效)
		 */
		setFocusable(true);
		add(playContainer);
		setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		setTitle("坦克大战");
		/**
		 * 添加窗口监听器
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		/**
		 * 添加按键监听器
		 */
		addKeyListener(new TankPaintListener());
		playButton.addMouseListener(new PlayListener(this));
		setVisible(true);
		setResizable(false);
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
		graphics.setColor(color);
		TankTask tankTask =GameModelManager.getInstance().getTankTask();
		// 绘制游戏道具图形
		Map<String,GameProp> gamePropMap=GameModelManager.getGameModelMap();
		for (Iterator<String> iter=gamePropMap.keySet().iterator();iter.hasNext();) {
			String key=iter.next();
			gamePropMap.get(key).paint(graphics);
		}
		collideCheck();
	}

	/**
	 * 坦克绘制监听器
	 */
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
			GamersTank gamersTank=GameModelManager.getInstance().getGamersTank();
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
			GamersTank tank=GameModelManager.getInstance().getGamersTank();
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
	 * 碰撞检测,检测子弹和坦克是否相撞
	 */
	private void collideCheck() {
		Map<String,GameProp> gamePropsMap=GameModelManager.getGameModelMap();

		Collection<GameProp> gameProps=gamePropsMap.values();
		List<GameProp> gamePropList=new ArrayList<>(gameProps);
		gamePropsMap.values().stream().forEach(gameProp -> {
			gamePropsMap.values().stream().forEach(gameProp1 -> {
				if (gameProp!=gameProp1) {
					GameModelManager.getInstance().getColliderChain().collide(gameProp, gameProp1);
				}
			});
		});
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

}
