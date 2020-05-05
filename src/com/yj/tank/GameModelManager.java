package com.yj.tank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.factory.AbstractWeaponFamilyFactory;
import com.yj.tank.factory.DefaultTankFamilyFactory;
import com.yj.tank.factory.TankFactory;
import com.yj.tank.factory.WeaponFactory;
import com.yj.tank.model.AbstractBullet;
import com.yj.tank.model.AbstractExplode;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.model.Plane;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-6:39
 **/
public class GameModelManager {

	private static GameModelManager INSTANCE=new GameModelManager();
	/**
	 * 生命数量
	 */
	public static final int lifeNum=ConfigProperties.getInstance().getInteger("initLifeNum");

	/**
	 * 敌军坦克数量
	 */
	public static final int enemyTankNum=ConfigProperties.getInstance().getInteger("initEnemyTankNum");
	/**
	 * 定时任务
	 */
	TankTimeTask tankTimeTask=new TankTimeTask(this,120);

	AbstractWeaponFamilyFactory abstractWeaponFamilyFactory = DefaultTankFamilyFactory.getInstance();

	WeaponFactory weaponFactory= TankFactory.getInstance();

	/**
	 * 我方坦克集合
	 */
	public static List<AbstractMilitaryWeapon> tanks=new ArrayList<>();

	AbstractMilitaryWeapon tank;

	/**
	 * 子弹集合
	 */
	//List<Bullet> bullets=new LinkedList<>();
	List<AbstractBullet> bullets=new LinkedList<>();

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
	//List<Tank> tanks=new LinkedList<>();
	List<AbstractMilitaryWeapon> enemyTanks=new LinkedList<>();

	/**
	 * 爆炸
	 */
	//List<Explode> explodes=new LinkedList<>();
	List<AbstractExplode> explodes=new LinkedList<>();

	/**
	 * 飞机集合
	 */
	List<Plane> planes=new LinkedList<>();

	private GameModelManager() {

	}

	public static GameModelManager getInstance() {
		return INSTANCE;
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
		TankTimeTask.setCurTimes(new AtomicInteger(1));
	}

	/**
	 * 初始化坦克游戏
	 */
	public void init() {
		tanks.addAll(weaponFactory.createWeapons(TankFrame.TANK_NUM,this, Group.BAD,120, Dir.DOWN));
		for (int i=0;i<lifeNum;i++) {
			//goodTanks.add(new Tank(100,400,Dir.DOWN,this,Group.GOOD));
			tanks.add(abstractWeaponFamilyFactory.createWeapon(100,400,Dir.DOWN,this,Group.GOOD));
		}
		this.tank=tanks.get(0);
		tank.setMoving(false);
	}

	public TankTimeTask getTankTimeTask() {
		return tankTimeTask;
	}

	public void setTankTimeTask(TankTimeTask tankTimeTask) {
		this.tankTimeTask = tankTimeTask;
	}

	public AbstractWeaponFamilyFactory getAbstractWeaponFamilyFactory() {
		return abstractWeaponFamilyFactory;
	}

	public void setAbstractWeaponFamilyFactory(AbstractWeaponFamilyFactory abstractWeaponFamilyFactory) {
		this.abstractWeaponFamilyFactory = abstractWeaponFamilyFactory;
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

	public static int getTotalLevelCount() {
		return totalLevelCount;
	}

	public static void setTotalLevelCount(int totalLevelCount) {
		GameModelManager.totalLevelCount = totalLevelCount;
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

	public List<Plane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public void setWeaponFactory(WeaponFactory weaponFactory) {
		this.weaponFactory = weaponFactory;
	}
}
