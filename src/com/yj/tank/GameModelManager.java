package com.yj.tank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.AbstractWeaponFactory;
import com.yj.tank.factory.EnemySmallTankFactory;
import com.yj.tank.factory.GamersSmallTankFactory;
import com.yj.tank.factory.SmallTankFactory;
import com.yj.tank.factory.SmallTankFamilyFactory;
import com.yj.tank.factory.WeaponFactory;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.EnemyTank;
import com.yj.tank.model.GameProp;
import com.yj.tank.model.GamersTank;
import com.yj.tank.model.Plane;

/**
 *  游戏模型管理器
 *  包含游戏中所有的模型添加、删除
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-6:39
 **/
public class GameModelManager {

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

	ColliderChain colliderChain=ColliderChain.getInstance();

	AbstractWeaponFactory abstractWeaponFactory = SmallTankFamilyFactory.getInstance();

	/**
	 * 敌军坦克工厂
	 */
	WeaponFactory<EnemyTank> enemyWeaponFactory= EnemySmallTankFactory.getInstance();

	/**
	 * 玩家坦克工厂
	 */
	WeaponFactory<GamersTank> gamersWeaponFactory= GamersSmallTankFactory.getInstance();

	/**
	 * 玩家坦克集合
	 */
	public static List<AbstractMilitaryWeapon> gamersTanks=new ArrayList<>();

	AbstractMilitaryWeapon tank;

	/**
	 * 子弹集合
	 */
	List<AbstractBullet> bullets=new LinkedList<>();

	/**
	 * 敌方坦克
	 */
	List<AbstractMilitaryWeapon> enemyTanks=new LinkedList<>();

	/**
	 * 爆炸
	 */
	List<AbstractExplode> explodes=new LinkedList<>();

	/**
	 * 飞机集合
	 */
	List<Plane> enemyPlanes=new LinkedList<>();

	/**
	 * 飞机
	 */
	AbstractMilitaryWeapon plane;

	List<GameProp> gameProps=new LinkedList<>();

	private AtomicLong gamePropNum=new AtomicLong(0);

	private static Map<String,Object> gameModelMap=new ConcurrentHashMap<>();

	private GameModelManager() {
		//tankTask=new TankTask(INSTANCE,120);
	}

	public static GameModelManager getInstance() {
		return INSTANCE;
	}

	public <T> void addGameProp(T gameProp) {
		StringBuffer gamePropName=new StringBuffer();
		gamePropName.append(gameProp.getClass().getName());
		gamePropName.append("_");
		gamePropName.append(gamePropNum.incrementAndGet());
		gameModelMap.put(gamePropName.toString(),gameProp);
		//gameProps.add(gameProp);
	}

	public <T> void addGameProps(Collection<T> collection) {
		if (collection != null && !collection.isEmpty()) {
			collection.stream().forEach(gameProp -> {
				StringBuffer gamePropName=new StringBuffer();
				gamePropName.append(gameProp.getClass().getName());
				gamePropName.append("_");
				gamePropName.append(gamePropNum.incrementAndGet());
				gameModelMap.put(gamePropName.toString(),gameProp);
			});
		}
	}

	public <T> void removeGameProp(T gameProp) {
		for (Iterator<String> iter=gameModelMap.keySet().iterator();iter.hasNext();) {
			String key=iter.next();
			if (gameModelMap.get(key)==gameProp) {
				gameModelMap.remove(key);
			}
		}
	}

	public <T> T getGameProp(String gamePropName) {
		return (T)gameModelMap.get(gamePropName);
	}

	/**
	 * 清除坦克游戏
	 */
	public void clear() {
		if (this.tank!= null) {
			setTank(null);
		}
		if (this.bullets.size()>0) {
			this.bullets.clear();
		}
		if (gamersTanks.size()>0) {
			gamersTanks.clear();
		}
		if (gamersTanks.size()>0) {
			gamersTanks.clear();
		}
		if (enemyTanks.size()>0) {
			enemyTanks.clear();
		}
		TankTask.setCurTimes(new AtomicInteger(1));
	}

	/**
	 * 初始化坦克游戏
	 */
	public void init() {
		// 初始化敌军坦克
		//enemyTanks.addAll(enemyWeaponFactory.createWeapons(GameModelManager.ENEMY_TANK_NUM,this, Group.BAD,ENEMY_TANK_DISTANCE, Dir.DOWN));
		/*gameProps.addAll(enemyWeaponFactory.createWeapons(GameModelManager.ENEMY_TANK_NUM,this,
				Group.BAD,ENEMY_TANK_DISTANCE, Dir.DOWN));*/
		addGameProps(enemyWeaponFactory.createWeapons(GameModelManager.ENEMY_TANK_NUM,this,
				Group.BAD,ENEMY_TANK_DISTANCE, Dir.DOWN));
		//  初始化玩家坦克
		for (int i=0;i<LIFE_NUM;i++) {
			//gamersTanks.add(gamersWeaponFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
			//gameProps.add(gamersWeaponFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
			addGameProp(gamersWeaponFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
		}

		/*if (gamersTanks.size()>0) {
			setTank(gamersTanks.get(0));
		}
		tank.setMoving(false);*/
		getGamersTank().setMoving(false);
	}

	public GamersTank getGamersTank() {
		for (Iterator<String> keyIter=gameModelMap.keySet().iterator();keyIter.hasNext();) {
			String key=keyIter.next();
			if (key.indexOf(GamersTank.class.getName())!=-1) {
				return (GamersTank)gameModelMap.get(key);
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

	public static List<AbstractMilitaryWeapon> getTanks() {
		return gamersTanks;
	}

	public static void setTanks(List<AbstractMilitaryWeapon> tanks) {
		GameModelManager.gamersTanks = tanks;
	}

	public AbstractMilitaryWeapon getTank() {
		return tank;
	}

	public void setTank(AbstractMilitaryWeapon tank) {
		this.tank = tank;
	}

	public List<AbstractBullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<AbstractBullet> bullets) {
		this.bullets = bullets;
	}

	public static int getCurLevelCount() {
		return curLevelCount;
	}

	public static void setCurLevelCount(int curLevelCount) {
		GameModelManager.curLevelCount = curLevelCount;
	}

	public List<AbstractMilitaryWeapon> getEnemyTanks() {
		return enemyTanks;
	}

	public void setEnemyTanks(List<AbstractMilitaryWeapon> enemyTanks) {
		this.enemyTanks = enemyTanks;
	}

	public List<AbstractExplode> getExplodes() {
		return explodes;
	}

	public void setExplodes(List<AbstractExplode> explodes) {
		this.explodes = explodes;
	}

	public List<Plane> getEnemyPlanes() {
		return enemyPlanes;
	}

	public void setEnemyPlanes(List<Plane> enemyPlanes) {
		this.enemyPlanes = enemyPlanes;
	}

	public AbstractMilitaryWeapon getPlane() {
		return plane;
	}

	public void setPlane(AbstractMilitaryWeapon plane) {
		this.plane = plane;
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

	public static List<AbstractMilitaryWeapon> getGamersTanks() {
		return gamersTanks;
	}

	public static void setGamersTanks(List<AbstractMilitaryWeapon> gamersTanks) {
		GameModelManager.gamersTanks = gamersTanks;
	}

	public List<GameProp> getGameProps() {
		return gameProps;
	}

	public void setGameProps(List<GameProp> gameProps) {
		this.gameProps = gameProps;
	}

	public static <T extends Object> Map<String, T> getGameModelMap() {
		return (Map<String, T>) gameModelMap;
	}

	public ColliderChain getColliderChain() {
		return colliderChain;
	}

	public void setColliderChain(ColliderChain colliderChain) {
		this.colliderChain = colliderChain;
	}
}
