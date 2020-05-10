package com.yj.tank;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.yj.tank.collider.ColliderChain;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.AbstractWeaponFactory;
import com.yj.tank.factory.EnemySmallTankFactory;
import com.yj.tank.factory.GamersSmallTankFactory;
import com.yj.tank.factory.SmallTankFamilyFactory;
import com.yj.tank.factory.WallFactory;
import com.yj.tank.factory.WeaponFactory;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.Wall;

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

	AbstractWeaponFactory abstractWeaponFactory = SmallTankFamilyFactory.getInstance();

	/**
	 * 敌军坦克工厂
	 */
	WeaponFactory<EnemyTank> enemyWeaponFactory= EnemySmallTankFactory.getInstance();

	/**
	 * 玩家坦克工厂
	 */
	WeaponFactory<GamersTank> gamersWeaponFactory= GamersSmallTankFactory.getInstance();

	WallFactory wallFactory=WallFactory.getInstance();

	private AtomicLong gamePropNum=new AtomicLong(0);

	private static Map<String,Object> gamePropsMap=new ConcurrentHashMap<>();

	private GameModelManager() {

	}

	public static GameModelManager getInstance() {
		return INSTANCE;
	}

	public <T> void addGameProp(T gameProp) {
		StringBuffer gamePropName=new StringBuffer();
		gamePropName.append(gameProp.getClass().getName());
		gamePropName.append(GAME_PROP_CLASS_NAME_SEPARATOR);
		gamePropName.append(gamePropNum.incrementAndGet());
		gamePropsMap.put(gamePropName.toString(),gameProp);
	}

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

	public <T> void removeGameProp(T gameProp) {
		for (Iterator<String> iter=gamePropsMap.keySet().iterator();iter.hasNext();) {
			String key=iter.next();
			if (gamePropsMap.get(key)==gameProp) {
				gamePropsMap.remove(key);
			}
		}
	}

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
		// 初始化敌军坦克
		/*addGameProps(enemyWeaponFactory.createWeapons(GameModelManager.ENEMY_TANK_NUM,this,
				Group.BAD,ENEMY_TANK_DISTANCE, Dir.DOWN));*/
		addGameProp(enemyWeaponFactory.createWeapon(5,200,Dir.RIGHT,this,Group.BAD));
		addGameProp(enemyWeaponFactory.createWeapon(5,500,Dir.RIGHT,this,Group.BAD));

		addGameProp(enemyWeaponFactory.createWeapon(500,20,Dir.DOWN,this,Group.BAD));
		addGameProp(enemyWeaponFactory.createWeapon(700,20,Dir.DOWN,this,Group.BAD));

		addGameProp(enemyWeaponFactory.createWeapon(1300,220,Dir.LEFT,this,Group.BAD));
		addGameProp(enemyWeaponFactory.createWeapon(1300,620,Dir.LEFT,this,Group.BAD));
		//addGameProp(enemyWeaponFactory.createWeapon(810,80,Dir.DOWN,this,Group.BAD));
		//  初始化玩家坦克
		for (int i=0;i<LIFE_NUM;i++) {
			addGameProp(gamersWeaponFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
		}
		getGamersTank().setMoving(false);
		// 初始化墙
		//addGameProp(new Wall(5,39,this));
		addGameProps(wallFactory.createWalls(5,39,this));
		addGameProps(wallFactory.createWalls(810,39,this));

		addGameProps(wallFactory.createWalls(810,730,this));
		addGameProps(wallFactory.createWalls(5,730,this));
	}

	public GamersTank getGamersTank() {
		for (Iterator<String> keyIter=gamePropsMap.keySet().iterator();keyIter.hasNext();) {
			String key=keyIter.next();
			if (key.indexOf(GamersTank.class.getName())!=-1) {
				return (GamersTank)gamePropsMap.get(key);
			}
		}
		return null;
	}

	public TankTask getTankTask() {
		return tankTask;
	}

	public void setTankTask(TankTask tankTask) {
		this.tankTask = tankTask;
	}

	public AbstractWeaponFactory getAbstractWeaponFactory() {
		return abstractWeaponFactory;
	}

	public void setAbstractWeaponFactory(AbstractWeaponFactory abstractWeaponFactory) {
		this.abstractWeaponFactory = abstractWeaponFactory;
	}

	public static int getCurLevelCount() {
		return curLevelCount;
	}

	public static void setCurLevelCount(int curLevelCount) {
		GameModelManager.curLevelCount = curLevelCount;
	}

	public WeaponFactory<EnemyTank> getEnemyWeaponFactory() {
		return enemyWeaponFactory;
	}

	public void setEnemyWeaponFactory(WeaponFactory<EnemyTank> enemyWeaponFactory) {
		this.enemyWeaponFactory = enemyWeaponFactory;
	}

	public WeaponFactory<GamersTank> getGamersWeaponFactory() {
		return gamersWeaponFactory;
	}

	public void setGamersWeaponFactory(WeaponFactory<GamersTank> gamersWeaponFactory) {
		this.gamersWeaponFactory = gamersWeaponFactory;
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
