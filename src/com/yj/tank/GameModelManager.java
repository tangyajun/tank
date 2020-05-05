package com.yj.tank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.AbstractWeaponFactory;
import com.yj.tank.factory.SmallTankFactory;
import com.yj.tank.factory.SmallTankFamilyFactory;
import com.yj.tank.factory.WeaponFactory;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.GameProp;
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
	 * 生命数量
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
	TankTimeTask tankTimeTask=new TankTimeTask(this,120);

	AbstractWeaponFactory abstractWeaponFactory = SmallTankFamilyFactory.getInstance();

	WeaponFactory weaponFactory= SmallTankFactory.getInstance();

	/**
	 * 我方坦克集合
	 */
	public static List<AbstractMilitaryWeapon> tanks=new ArrayList<>();

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

	private GameModelManager() {

	}

	public static GameModelManager getInstance() {
		return INSTANCE;
	}

	public void addGameProp(GameProp gameProp) {
		gameProps.add(gameProp);
	}

	public void removeGameProp(GameProp gameProp) {
		gameProps.remove(gameProp);
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
		if (tanks.size()>0) {
			tanks.clear();
		}
		if (tanks.size()>0) {
			tanks.clear();
		}
		if (enemyTanks.size()>0) {
			enemyTanks.clear();
		}
		TankTimeTask.setCurTimes(new AtomicInteger(1));
	}

	/**
	 * 初始化坦克游戏
	 */
	public void init() {
		// 初始化敌军坦克
		enemyTanks.addAll(weaponFactory.createWeapons(GameModelManager.ENEMY_TANK_NUM,this, Group.BAD,ENEMY_TANK_DISTANCE, Dir.DOWN));
		//  初始化我方坦克
		for (int i=0;i<LIFE_NUM;i++) {
			tanks.add(abstractWeaponFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
		}
		if (tanks.size()>0) {
			setTank(tanks.get(0));
		}
		tank.setMoving(false);
	}

	public TankTimeTask getTankTimeTask() {
		return tankTimeTask;
	}

	public void setTankTimeTask(TankTimeTask tankTimeTask) {
		this.tankTimeTask = tankTimeTask;
	}

	public AbstractWeaponFactory getAbstractWeaponFactory() {
		return abstractWeaponFactory;
	}

	public void setAbstractWeaponFactory(AbstractWeaponFactory abstractWeaponFactory) {
		this.abstractWeaponFactory = abstractWeaponFactory;
	}

	public static List<AbstractMilitaryWeapon> getTanks() {
		return tanks;
	}

	public static void setTanks(List<AbstractMilitaryWeapon> tanks) {
		GameModelManager.tanks = tanks;
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

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public void setWeaponFactory(WeaponFactory weaponFactory) {
		this.weaponFactory = weaponFactory;
	}
}
