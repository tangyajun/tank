package com.yj.tank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.yj.tank.collider.ColliderChain;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.EnemySmallTankFactory;
import com.yj.tank.factory.GamersSmallTankFactory;
import com.yj.tank.factory.WallFactory;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.FirstGameLevel;
import com.yj.tank.model.GamersTank;

/**
 *  游戏模型管理器
 *  包含游戏中所有的模型添加、删除
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-6:39
 **/
public class GameModelManager {

	public static final String GAME_PROP_CLASS_NAME_SEPARATOR="#";
	public static final int ENEMY_TANK_DISTANCE=200;

	private static GameModelManager INSTANCE=new GameModelManager();
	/**
	 * 玩家生命数量
	 */
	public static final int LIFE_NUM=ConfigProperties.getInstance().getInteger("initLifeNum");

	/**
	 * 敌军坦克数量
	 */
	public static final int ENEMY_TANK_NUM=ConfigProperties.getInstance().getInteger("initEnemyTankNum");

	/**
	 * 总关卡数
	 */
	public static final int TOTAL_LEVEL_COUNT=10;
	/**
	 * 当前关卡数
	 */
	public static int curLevelCount=1;
	/**
	 * 定时任务
	 */
	TankTask tankTask=new TankTask(INSTANCE,120);

	ColliderChain colliderChain=new ColliderChain();

	private AtomicLong gamePropNum=new AtomicLong(0);

	private static Map<String,Object> gamePropsMap=new ConcurrentHashMap<>();

	private GameModelManager() {

	}

	public static GameModelManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 添加游戏道具
	 * @param gameProp
	 * @param <T>
	 */
	public <T> void addGameProp(T gameProp) {
		StringBuffer gamePropName=new StringBuffer();
		gamePropName.append(gameProp.getClass().getName());
		gamePropName.append(GAME_PROP_CLASS_NAME_SEPARATOR);
		gamePropName.append(gamePropNum.incrementAndGet());
		gamePropsMap.put(gamePropName.toString(),gameProp);
	}

	/**
	 * 添加游戏道具
	 * @param collection
	 * @param <T>
	 */
	public <T> void addGameProps(Collection<T> collection) {
		if (collection != null && !collection.isEmpty()) {
			collection.stream().forEach(gameProp -> {
				StringBuffer gamePropName=new StringBuffer();
				gamePropName.append(gameProp.getClass().getName());
				gamePropName.append(GAME_PROP_CLASS_NAME_SEPARATOR);
				gamePropName.append(gamePropNum.incrementAndGet());
				gamePropsMap.put(gamePropName.toString(),gameProp);
			});
		}
	}

	/**
	 * 删除游戏道具
	 * @param gameProp
	 * @param <T>
	 */
	public <T> void removeGameProp(T gameProp) {
		for (Iterator<String> iter=gamePropsMap.keySet().iterator();iter.hasNext();) {
			String key=iter.next();
			if (gamePropsMap.get(key)==gameProp) {
				gamePropsMap.remove(key);
			}
		}
	}

	/**
	 * 获取游戏道具
	 * @param gamePropName
	 * @param <T>
	 * @return
	 */
	public <T> T getGameProp(String gamePropName) {
		return (T)gamePropsMap.get(gamePropName);
	}

	/**
	 * 清除坦克游戏
	 */
	public void clear() {
		TankTask.setCurTimes(new AtomicInteger(1));
	}

	/**
	 * 初始化坦克游戏
	 */
	public void init() {
		initWall();
		// 开始第一关
		FirstGameLevel.getInstance().start();
	}

	/**
	 * 初始化玩家坦克
	 */
	private void initGamersTank() {
		//  初始化玩家坦克
		for (int i=0;i<LIFE_NUM;i++) {
			addGameProp(GamersSmallTankFactory.getInstance().createWeapon(100,400,Dir.DOWN,Group.GOOD));
		}
		getGamersTank().setMoving(false);
	}

	/**
	 * 初始化敌方坦克
	 */
	private void initEnemyTanks() {
		// 初始化敌军坦克
		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(5,200,Dir.RIGHT,Group.BAD));
		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(5,500,Dir.RIGHT,Group.BAD));

		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(500,20,Dir.DOWN,Group.BAD));
		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(700,20,Dir.DOWN,Group.BAD));

		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(1300,220,Dir.LEFT,Group.BAD));
		addGameProp(EnemySmallTankFactory.getInstance().createWeapon(1300,620,Dir.LEFT,Group.BAD));
	}

	private void initWall() {
		/**
		 * 初始化墙
		 */
		//addGameProp(new Wall(5,39,this));
		addGameProps(WallFactory.getInstance().createWalls(5,39));
		addGameProps(WallFactory.getInstance().createWalls(810,39));

		addGameProps(WallFactory.getInstance().createWalls(810,730));
		addGameProps(WallFactory.getInstance().createWalls(5,730));
	}

	/**
	 * 获取玩家坦克
	 * @return
	 */
	public GamersTank getGamersTank() {
		for (Iterator<String> keyIter=gamePropsMap.keySet().iterator();keyIter.hasNext();) {
			String key=keyIter.next();
			if (key.indexOf(GamersTank.class.getName())!=-1) {
				return (GamersTank)gamePropsMap.get(key);
			}
		}
		return null;
	}

	/**
	 * 获取敌方坦克
	 * @return
	 */
	public List<EnemyTank> getEnemyTanks() {
		List<EnemyTank> enemyTanks=new ArrayList<>();
		for (Iterator<String> keyIter=gamePropsMap.keySet().iterator();keyIter.hasNext();) {
			String key=keyIter.next();
			if (key.contains(EnemyTank.class.getName())) {
				enemyTanks.add((EnemyTank)gamePropsMap.get(key));
			}
		}
		return enemyTanks;
	}

	public TankTask getTankTask() {
		return tankTask;
	}

	public void setTankTask(TankTask tankTask) {
		this.tankTask = tankTask;
	}

	public static int getCurLevelCount() {
		return curLevelCount;
	}

	public static void setCurLevelCount(int curLevelCount) {
		GameModelManager.curLevelCount = curLevelCount;
	}

	public static <T extends Object> Map<String, T> getGameModelMap() {
		return (Map<String, T>) gamePropsMap;
	}

	public ColliderChain getColliderChain() {
		return colliderChain;
	}

	public void setColliderChain(ColliderChain colliderChain) {
		this.colliderChain = colliderChain;
	}
}
